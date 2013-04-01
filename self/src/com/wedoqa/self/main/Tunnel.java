package com.wedoqa.self.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

/**
 * This app create a tunnel between the localhost and the Database machine
 * Need to be running before testing using db testing
 * @author Tihomir Turzai @ WeDoQA
 *
 */
public class Tunnel {

	Session session;
	final static Logger logger = LoggerFactory.getLogger(Tunnel.class);

	public static Tunnel startTunnel(){
		Tunnel t=new Tunnel();
		try{
			t.go();
		} catch(Exception ex){
		}
		return t;
	}

	public static void stopTunnel(Tunnel tunnel){
		tunnel.stop();
	}
	public static void main(String[] args){
		Tunnel t=new Tunnel();
		try{
			t.go();
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void stop(){
		try{
			session.disconnect();
			logger.info("Remote connection closed");

		}catch (Exception  ex){
		}


	}
	public void go() throws Exception{
		// TODO put the connection info to a property file
		String host="vt156.crimsonhexagon.com";
		String user="prod";
		String password="chexagon!";
		int port=22;

		int tunnelLocalPort=1212;
		String tunnelRemoteHost="172.16.23.223";
		int tunnelRemotePort=5432;
		
		JSch jsch=new JSch();
		session=jsch.getSession(user, host, port);
		session.setPassword(password);
		localUserInfo lui=new localUserInfo();
		session.setUserInfo(lui);
		session.connect();
		session.setPortForwardingL(tunnelLocalPort,tunnelRemoteHost,tunnelRemotePort);
		logger.info("Machine is connected to: {}",host);

	}

	class localUserInfo implements UserInfo{
		String passwd;
		public String getPassword(){ return passwd; }
		public boolean promptYesNo(String str){return true;}
		public String getPassphrase(){ return null; }
		public boolean promptPassphrase(String message){return true; }
		public boolean promptPassword(String message){return true;}
		public void showMessage(String message){}
	}     
} 


