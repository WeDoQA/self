package com.wedoqa.self.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Jselements {

	
	
	
	 /**
     * 
     * @param driver
     * @param month
     * @param year
     * @param day
     */
    public static void javasriptDatePicker(WebDriver driver, String month, String year, String day) {
    	waitForElementToAppear(driver, By.cssSelector("select.ui-datepicker-month"));
    	Util.selectUsingCSSSelector(driver, "select.ui-datepicker-month", month);
    	waitForElementToAppear(driver,  By.cssSelector("select.ui-datepicker-year"));
    	Util.selectUsingCSSSelector(driver, "select.ui-datepicker-year", year);
    	waitForElementToAppear(driver, By.linkText(day));
    	driver.findElement(By.linkText(day)).click();
    }
    
    
    public static void waitForElementToAppear(WebDriver driver,By selector) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(selector));
	}
    
    
    
}
