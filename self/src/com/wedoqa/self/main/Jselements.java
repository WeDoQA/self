package com.wedoqa.self.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

public class Jselements {

	
	
	
	 /**
     * 
     * @param driver
     * @param month
     * @param year
     * @param day
     */
//    public static void javasriptDatePicker(WebDriver driver, String month, String year, String day) {
//    	waitForElementToAppear(driver, By.cssSelector("select.ui-datepicker-month"));
//    	Util.selectUsingCSSSelector(driver, "select.ui-datepicker-month", month);
//    	waitForElementToAppear(driver,  By.cssSelector("select.ui-datepicker-year"));
//    	Util.selectUsingCSSSelector(driver, "select.ui-datepicker-year", year);
//    	waitForElementToAppear(driver, By.linkText(day));
//    	driver.findElement(By.linkText(day)).click();
//    }
    
    
    public static void waitForElementToAppear(WebDriver driver,By selector) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(selector));
	}
    
    
    
}
