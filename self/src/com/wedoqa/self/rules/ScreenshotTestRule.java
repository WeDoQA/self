package com.wedoqa.self.rules;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.wedoqa.self.main.TestBase;

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

public class ScreenshotTestRule implements MethodRule {
	public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				try {
					statement.evaluate();
				} catch (Throwable t) {

					captureScreenshot(frameworkMethod.getName(), (TestBase) o);
					throw t; // rethrow to allow the failure to be reported to JUnit
				}
				((TestBase) o).getDriver().quit();
			}

			public void captureScreenshot(String fileName, TestBase testBase) {
				try {
					File screenshot;
					if (testBase.getDriver() instanceof RemoteWebDriver){
						WebDriver webdriver = new Augmenter().augment(testBase.getDriver()); 
						 screenshot =  ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);						
					}else{
						 screenshot =  ((TakesScreenshot)testBase.getDriver()).getScreenshotAs(OutputType.FILE);
					}
					FileUtils.copyFile(screenshot, new File("./screenshots/screenshot-"+fileName+".png"));
				} catch (Exception e) {
					
					//e.printStackTrace();
					// No need to crash the tests if the screenshot fails
				}
			}
		};
	}
}
