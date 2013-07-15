package com.wedoqa.self.main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

/**
 * 
 * 
 *
 */
public class PageUtils {

	WebDriver driver;


	public PageUtils(WebDriver driver) {
		super();
		this.driver = driver;
	}

	/**
	 * Waits for a given time
	 * @param milis
	 */
	public void waitFor(Integer milis){
		try{
			Thread.sleep(milis);
		} catch (InterruptedException e1) {
		}
	}
	/**
	 * Waits for the  load indicator selected with ".ajaxworking" to disappear 
	 */
	//TODO check this condition
	public void waitForLoadToDisappear() {
		try{
			Thread.sleep(500);
		} catch (InterruptedException e1) {
		}
		try{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ajaxWorking"))));
		}catch (TimeoutException e1) {

		}
	}

	/**
	 * Waits for the post load indicator selected with "form#more img" to disappear
	 */
	public void waitForPostLoadToDisappear() {
		try{
			Thread.sleep(500);
		} catch (InterruptedException e1) {
		}

		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form#more img"))));

	}

	/**
	 * Waits for the post load indicator selected with "#loading" to disappear
	 */
	public void waitForGraphLoadToDisappear() {
		try{
			Thread.sleep(500);
		} catch (InterruptedException e1) {
		}
		try{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#loading"))));
		}catch (TimeoutException e1) {
			e1.printStackTrace();  
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
		WebDriverWait wait = new WebDriverWait(driver, 100);
		int count = 0; 
		while (count < 4){
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
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
		WebDriverWait wait = new WebDriverWait(driver, 40);
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

	@SuppressWarnings("unused")
	public void waitForElementToDisappear(final By xpath) {
		WebDriverWait wdw = new WebDriverWait(driver, 40);
		ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				try{
					WebElement element = d.findElement(xpath);
					return false;
				}catch(NoSuchElementException ex){
					return true;
				}     
			}
		};
		wdw.until(condition);

	}
	
	public WebElement waitForElement(final By by) {
        WebElement elementToWaitFor = null;
        int waitTime = 10; // time provided in seconds
        int count = 0; 
		while (count < 2){
			try {
	            WebElement myDynamicElement = (new WebDriverWait(driver, waitTime)).until(new ExpectedCondition<WebElement>() {
	                public WebElement apply(WebDriver d) {
	                    return d.findElement(by);
	                }
	            });
	            elementToWaitFor = myDynamicElement;
	            break;
			}catch (StaleElementReferenceException e){
				e.toString();
				System.out.println("Trying to recover from a stale element :" + e.getMessage());
				count = count+1;
			}
		}
		return elementToWaitFor;
	}
	
	public WebElement waitForElement(WebElement element) {
        WebElement elementToWaitFor = null;
        int waitTime = 10; // time provided in seconds
        int count = 0; 
		while (count < 2){
			try {				
				elementToWaitFor = (new WebDriverWait(driver, waitTime)).until(ExpectedConditions.visibilityOf(element));	            
	            break;
			}catch (StaleElementReferenceException e){
				e.toString();
				System.out.println("Trying to recover from a stale element :" + e.getMessage());
				count = count+1;
			}
		}
		return elementToWaitFor;
	}
	
	public boolean isElementPresent(By by) {
        try {
        	driver.findElement(by).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
