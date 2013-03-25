package test.java.com.ch.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The base 
 * @author Tihomir Turzai @ WeDoQA
 *
 */
@RunWith(Parallelized.class)
public class TestBase {


	protected final static Logger logger = LoggerFactory.getLogger(TestBase.class);

	// @Rule
	//  public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule();

	@Rule 
	public TestName name = new TestName();



	private ThreadLocal<RemoteWebDriver> threaddriver = null;


	public TestBase(String browser) throws MalformedURLException{
		logger.info("Current Browser : " +  browser);

		String server = "http://192.168.231.159:4444/wd/hub";
		DesiredCapabilities dc = new DesiredCapabilities();
		//dc.setPlatform(Platform.WINDOWS);		
		threaddriver = new ThreadLocal<RemoteWebDriver>();
		if (browser.equalsIgnoreCase("firefox")) {
			FirefoxProfile fp = new FirefoxProfile();
			dc.setCapability(FirefoxDriver.PROFILE, fp);
			//dc.setPlatform(Platform.WINDOWS);
			dc.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
			threaddriver.set(new RemoteWebDriver(new URL(server), dc));
		//	threaddriver.set(new FirefoxDriver());
		}else
			if (browser.equalsIgnoreCase("iexplorer8")) {
				dc.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
				dc.setVersion("8");
				//threaddriver.set(new InternetExplorerDriver(dc));

					threaddriver.set(new RemoteWebDriver(new URL(server), dc));
			}else
				if (browser.equalsIgnoreCase("iexplorer9")) {
					//			threaddriver.set(new InternetExplorerDriver());
					dc.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
					dc.setVersion("9");		
					threaddriver.set(new InternetExplorerDriver(dc));

					//threaddriver.set(new RemoteWebDriver(new URL(server), dc));
				}else
					if (browser.equalsIgnoreCase("iexplorer10")) {						
						dc.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
						dc.setVersion("10");
						threaddriver.set(new RemoteWebDriver(new URL(server), dc));
					}else
						if (browser.equalsIgnoreCase("chrome")) {
							dc.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
							threaddriver.set(new ChromeDriver( dc));
						//	threaddriver.set(new RemoteWebDriver(new URL(server), dc));
						}  else
							if (browser.equalsIgnoreCase("safari")) {
								dc.setBrowserName(DesiredCapabilities.safari().getBrowserName());
								//threaddriver.set(new SafariDriver(dc));
								threaddriver.set(new RemoteWebDriver(new URL(server), dc));
							}
	}


	@Parameters(name = "Browser: {0}")
	public static Collection<String[]> generateData(){
	//		String browsersParam = System.getProperty("browsers");
		//logger.info("Reading config file : " + browsersParam);
		//	String browsersParam = "iexplorer9,iexplorer10";

		//	String browsersParam = "firefox,chrome,iexplorer9,iexplorer10";
		String browsersParam = "chrome";

		//iexplorer9,iexplorer8,chrome,
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

	@BeforeClass
	public static void beforeClass() {
		// driver = new Firefoxdriver();	      
	}

	@After
	public void afterClass() {	      
		threaddriver.get().quit();

	}


	public RemoteWebDriver getDriver() {
		return threaddriver.get();
	}



}
