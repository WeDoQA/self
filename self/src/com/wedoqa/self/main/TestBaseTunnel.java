package com.wedoqa.self.main;

import java.net.MalformedURLException;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import test.java.com.ch.db.TagQueries;
import test.java.com.ch.db.Tunnel;

public class TestBaseTunnel extends TestBase{

	static Tunnel tunnel;
	private String username;
	private String password;
	private String userId;
	
	public TestBaseTunnel(String browser) throws MalformedURLException {
		super(browser);
		username = UUID.randomUUID().toString().replace("-", "").substring(0, 11);
		password = username;
		PasswordEncryptionHelper.createUser(username);
		userId = TagQueries.getUserIdByUsername(username);
		System.out.println(username);
		System.out.println(password);
	
	}


	@BeforeClass
	public static void beforeClass() {
		tunnel = Tunnel.startTunnel();
		}

	@AfterClass
	public static void destroytunnel() {
		Tunnel.stopTunnel(tunnel);		
	}


	public  String getUsername() {
		return username;
	}


	public  void setUsername(String username) {
		this.username = username;
	}


	public  String getPassword() {
		return password;
	}


	public  void setPassword(String password) {
		this.password = password;
	}


	public  String getUserId() {
		return userId;
	}


	public  void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
