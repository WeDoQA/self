package com.wedoqa.self.main;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageUtils {

	WebDriver driver;


	public PageUtils(WebDriver driver) {
		super();
		this.driver = driver;
	}


	public void waitForLoadToDisappear() {
		try{
			Thread.sleep(500);
		} catch (InterruptedException e1) {
		}
		try{
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ajaxWorking"))));
		}catch (TimeoutException e1) {
		}
	}
	
	public void waitForElementToAppear(String cssLocator) {
		waitForElementToAppear(By.cssSelector(cssLocator));
		
	}
	
	public void waitForElementToAppear(By locator) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 20);
		int count = 0; 
		while (count < 4){
			try {
				wait.until(ExpectedConditions.elementToBeClickable(locator));
				return;
			} catch (StaleElementReferenceException e){
				e.toString();
				System.out.println("Trying to recover from a stale element :" + e.getMessage());
				count = count+1;
			}
			count = count+4;
		}
	}
	
	public void waitForElementWithTextToAppear(String cssLocator) {
		waitForElementWithTextToAppear(By.cssSelector(cssLocator));
		
	}
	public String waitForElementWithTextToAppear(By selector) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = null;
		int count = 0; 
		while (count < 4){
			try {
				element = wait.until(ExpectedConditions.elementToBeClickable(selector));
				return element.getText();
			} catch (StaleElementReferenceException e){
				e.toString();
				System.out.println("Trying to recover from a stale element :" + e.getMessage());
				count = count+1;
			}
			count = count+4;
		}
		return element.getText();
	}
}
