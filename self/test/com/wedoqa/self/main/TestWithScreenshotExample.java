package com.wedoqa.self.main;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.wedoqa.self.main.TestBase;
import com.wedoqa.self.rules.ScreenshotTestRule;


//This is an example for the usage of ScreenshotTestRule
public class TestWithScreenshotExample extends TestBase {
	 @Test
	   
	    public void testThatSucceeds() {
	        driver.get("http://www.google.com");
	        
	        System.out.println("After class");
	        
	        // Find the text input element by its name
	           WebElement element = driver.findElement(By.name("q"));

	           // Enter something to search for
	           element.sendKeys("Cheese!");

	           // Now submit the form. WebDriver will find the form for us from the element
	           element.submit();

	           // Check the title of the page
	           
	           System.out.println("Page title is: " + driver.getTitle());
	        System.out.println("Test");
	        
	     }
	 
	    @Test
	    public void testThatFails() {
	    	 driver.get("http://www.google.com");
		      
	    	 String text = "Text not found!";
	    	 String bodyText = driver.findElement(By.tagName("body")).getText();
	    	 Assert.assertTrue("Text not found!", bodyText.contains(text));
	    	 
	    }

    
}
