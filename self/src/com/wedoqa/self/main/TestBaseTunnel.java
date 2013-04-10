package com.wedoqa.self.main;

import java.net.MalformedURLException;

import org.junit.AfterClass;

/**
 * 
 * @author Tihomir Turzai @ WeDoQA
 *
 */
public class TestBaseTunnel extends TestBase {

	public TestBaseTunnel(String browser,String server,String host, String user, String password, String tunnelRemoteHost) throws MalformedURLException {
		super(browser);
		tunnel = Tunnel.startTunnel(host,  user,  password,  tunnelRemoteHost);
	}

	static Tunnel tunnel;
	

	@AfterClass
	public static void destroytunnel() {
		Tunnel.stopTunnel(tunnel);		
	}
	
	
}
