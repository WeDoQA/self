package com.wedoqa.self.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

/*
 * Copyright 2013 WeDoQA
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

/**
 * This app create a tunnel between the localhost and the Database machine
 * Need to be running before testing using db testing
 *
 */
public class Tunnel {

	Session session;
	final static Logger logger = LoggerFactory.getLogger(Tunnel.class);
	String host;
	String user;
	String password;
	String tunnelRemoteHost;
	public Tunnel(String host2, String user2, String password2,
			String tunnelRemoteHost2) {
		this.host = host2;
		this.user = user2;
		this.password = password2;
		this.tunnelRemoteHost = tunnelRemoteHost2;
	}

	public static Tunnel startTunnel(String host, String user, String password, String tunnelRemoteHost){
		Tunnel t=new Tunnel( host,  user,  password,  tunnelRemoteHost);

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
//		Tunnel t=new Tunnel();
//		try{
//			t.go();
//		} catch(Exception ex){
//			ex.printStackTrace();
//		}
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
		//		String host="test.com";
		//		String user="test";
		//		String password="test!";
		int port=22;

		int tunnelLocalPort=1212;
		//	String tunnelRemoteHost="192.168.0.1";
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


