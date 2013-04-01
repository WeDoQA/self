package com.wedoqa.self.main;

import java.net.MalformedURLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * 
 * @author Tihomir Turzai @ WeDoQA
 *
 */
public class TestBaseTunnel extends TestBase {

	public TestBaseTunnel(String browser) throws MalformedURLException {
		super(browser);
	}

	static Tunnel tunnel;


	@BeforeClass
	public static void beforeClass() {
		tunnel = Tunnel.startTunnel();
		}

	@AfterClass
	public static void destroytunnel() {
		Tunnel.stopTunnel(tunnel);		
	}


	
	
}
