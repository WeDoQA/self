package com.wedoqa.self.rules;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.wedoqa.self.main.TestBase;


// THis is something like AOP in JUnit
// we can define a rule which will be used by JUnit
public class ScreenshotTestRule implements MethodRule {
    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (Throwable t) {
                	
                    captureScreenshot(frameworkMethod.getName());
                    throw t; // rethrow to allow the failure to be reported to JUnit
                }
            }
 
            public void captureScreenshot(String fileName) {
                try {
               File screenshot =  ((TakesScreenshot)TestBase.getDriver()).getScreenshotAs(OutputType.FILE);
               FileUtils.copyFile(screenshot, new File("./screenshots/screenshot-"+fileName+".png"));

                } catch (Exception e) {
                    // No need to crash the tests if the screenshot fails
                }
            }
        };
    }
}
