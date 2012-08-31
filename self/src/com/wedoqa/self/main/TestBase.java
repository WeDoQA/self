package com.wedoqa.self.main;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wedoqa.self.rules.ScreenshotTestRule;



/**
 * The base 
 * @author Tihomir Turzai
 *
 */
public abstract class TestBase {

	 
	 	protected final static Logger logger = LoggerFactory.getLogger(TestBase.class);
		protected static WebDriver driver;
  
	    @Rule
	    public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule();
	 
	    @Rule 
	    public TestName name = new TestName();

	    @Rule public MethodRule watchman = new TestWatchman() {
	        public void starting(FrameworkMethod method) {
	          logger.info("Starting: {}", method.getName());
	        }
	      };

	      
	    @BeforeClass
	    public static void beforeClass() {
	    	  driver = new FirefoxDriver();	      
		    }
	 
	    @AfterClass
	    public static void afterClass() {	      
	       driver.quit();
	       
	    }
	 
	   
		public static WebDriver getDriver() {
			return driver;
		}

		public static void setDriver(WebDriver driver) {
			TestBase.driver = driver;
		}

	    
}
