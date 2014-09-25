package com.wedoqa.self.main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wedoqa.self.rules.ScreenshotTestRule;

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

@RunWith(Parallelized.class)
public class TestBase {

	static String server = "http://localhost:4444/wd/hub";
	protected final static Logger logger = LoggerFactory.getLogger(TestBase.class);
	public static String browser;
	public static TestMode mode;

	@Rule 
	public TestName name = new TestName();

	@Rule
	public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule();

	private ThreadLocal<RemoteWebDriver> threaddriver = null;

	public TestBase(String browser) throws MalformedURLException{
		mode = decodeTestMode(System.getProperty("mode"));		
		createDrivers(browser, mode);
	}
	/**
	 * Constructor which sends out the command for browser creation
	 * Every browser will be send out to grid for 
	 * @param browser
	 * @throws MalformedURLException
	 */
	//	public TestBase() throws MalformedURLException{
	//		createDrivers("chrome", "Local");
	//	}

	public void createDrivers(String browser, TestMode testMode) throws MalformedURLException{

		logger.info("Current Browser : " +  browser);
		threaddriver = new ThreadLocal<RemoteWebDriver>();
		switch (testMode){
		case QA :
			threaddriver.set(getQADriver(browser));
			break;
		case SAUCE :
			threaddriver.set(getSauceDriver());
			break;
		case LOCAL :			
		default:
			threaddriver.set(getLocalDriver(browser));
			break;
		}		
	}

	private RemoteWebDriver getLocalDriver(String browser) {
		RemoteWebDriver result = null;
		DesiredCapabilities dc = new DesiredCapabilities();		

		if (browser.equalsIgnoreCase("firefox")) {
			FirefoxProfile fp = new FirefoxProfile();
			dc.setCapability(FirefoxDriver.PROFILE, fp);
			dc.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
			result = new FirefoxDriver(dc);
		}else
			if (browser.equalsIgnoreCase("iexplorer8")) {
				dc.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
				dc.setVersion("8");		
				result = new InternetExplorerDriver(dc);
			}else
				if (browser.equalsIgnoreCase("iexplorer9")) {
					dc.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
					dc.setVersion("9");		
					result = new InternetExplorerDriver(dc);
				}else
					if (browser.equalsIgnoreCase("iexplorer10")) {						
						dc.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
						dc.setVersion("10");
						result = new InternetExplorerDriver(dc);
					}else
						if (browser.equalsIgnoreCase("chrome")) {
							dc.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
							result = new ChromeDriver( dc);							
						}  else
							if (browser.equalsIgnoreCase("safari")) {
								dc.setBrowserName(DesiredCapabilities.safari().getBrowserName());
								result = new SafariDriver(dc);
							}	
		return result;
	}
	private static TestMode decodeTestMode(String testMode) {
		TestMode result = TestMode.LOCAL;
		try{
			result = TestMode.valueOf(testMode);
		}catch (NullPointerException ex)	{

		}
		return result;
	}


	@Parameters(name = "Browser: {0}")
	public static Collection<String[]> generateData(){

		String browsersParam = System.getProperty("browsers");
		mode = decodeTestMode(System.getProperty("mode"));

		//logger.info("Reading config file : " + browsersParam);
		//	String browsersParam = "iexplorer9,iexplorer10";

		//	String browsersParam = "firefox,chrome,iexplorer9,iexplorer10";

		// iexplorer9,iexplorer8,chrome,
		Collection<String[]> b = new ArrayList<String[]>();
		if (browsersParam != null) {
			String[] browserParamList = browsersParam.split(","); 
			//Object[][] data = new Object[][] { { 1 }, { 2 }, { 3 }, { 4 },{5},{} };

			for (int i=0;i<browserParamList.length;i++){			
				switch (browserParamList[i]){
				case "iexplorer10": b.add(new String[]{"iexplorer10"}); break;
				case "iexplorer9": b.add(new String[]{"iexplorer9"}); break;
				case "iexplorer8": b.add(new String[]{"iexplorer8"}); break;
				case "safari": b.add(new String[]{"safari"}); break;
				case "chrome": b.add(new String[]{"chrome"}); break;
				case "firefox":b.add(new String[]{"firefox"}); break;
				default: 
					logger.info("Unrecognized browser, the test will be executed in a firefox browser");
					b.add(new String[]{"firefox"}); break;			
				}
				//logger.info("Current : " +  browserParamList[i]);

			}
			return b;
		}else
			b.add(new String[]{"chrome"});
		return  b;
		// return browserList;
	}

	public RemoteWebDriver getSauceDriver() throws MalformedURLException{
		RemoteWebDriver result = null;

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setBrowserName(System.getenv("SELENIUM_BROWSER"));
		desiredCapabilities.setVersion(System.getenv("SELENIUM_VERSION"));
		desiredCapabilities.setCapability(CapabilityType.PLATFORM, System.getenv("SELENIUM_PLATFORM"));	 
		//desiredCapabilities.setCapability("name", name.getMethodName());

		String username =  System.getenv("SAUCE_USER_NAME");
		String accessKey = System.getenv("SAUCE_API_KEY");
		
		result = new RemoteWebDriver(
				new URL("http://"+username+":"+accessKey+"@ondemand.saucelabs.com:80/wd/hub"),
				desiredCapabilities);
		return result;
	}

	public RemoteWebDriver getQADriver(String browser) throws MalformedURLException{

		RemoteWebDriver result = null;
		DesiredCapabilities dc = new DesiredCapabilities();		
		if (browser.equalsIgnoreCase("firefox")) {
			FirefoxProfile fp = new FirefoxProfile();
			dc.setCapability(FirefoxDriver.PROFILE, fp);
			dc.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
			result = new RemoteWebDriver(new URL(server), dc);
		}else
			if (browser.equalsIgnoreCase("iexplorer8")) {
				dc.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
				dc.setVersion("8");
				result =  new RemoteWebDriver(new URL(server), dc);
			}else
				if (browser.equalsIgnoreCase("iexplorer9")) {
					dc.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
					dc.setVersion("9");		
					result =  new RemoteWebDriver(new URL(server), dc);					
				}else
					if (browser.equalsIgnoreCase("iexplorer10")) {						
						dc.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
						dc.setVersion("10");
						result =  new RemoteWebDriver(new URL(server), dc);
					}else
						if (browser.equalsIgnoreCase("chrome")) {
							dc.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
							result =  new RemoteWebDriver(new URL(server), dc);							
						}  else
							if (browser.equalsIgnoreCase("safari")) {
								dc.setBrowserName(DesiredCapabilities.safari().getBrowserName());
								result =  new RemoteWebDriver(new URL(server), dc);
							}	
		return result;
	}


	public RemoteWebDriver getDriver() {
		return threaddriver.get();
	}

	public static String getServer() {
		return server;
	}
	public static void setServer(String server) {
		TestBase.server = server;
	}
	public static String getBrowser() {
		return browser;
	}
	public static void setBrowser(String browser) {
		TestBase.browser = browser;
	}

}
