package com.wedoqa.self.main;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
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
public class TestBase {

	 static WebDriver driver;
	 //   static Selenium selenium;
	 
	 protected final static Logger logger = LoggerFactory.getLogger(TestBase.class);
	  
	    @Rule
	    public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule();
	 
	    @Rule 
	    public TestName name = new TestName();

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

	    
}
