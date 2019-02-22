package org.csanchez.selenium.example;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ExampleTest {

    private final static String SELENIUM_URL = System.getProperty("selenium.url", "http://localhost:4444/wd/hub");
    private final static String SELENIUM_BROWSER = System.getProperty("selenium.browser", "chrome");
    private final static int SLEEP = Integer.parseInt(System.getProperty("sleep", "10000"));

    protected WebDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities(SELENIUM_BROWSER, "", Platform.ANY);
        // Retry connecting
        WebDriverException ex = null;
        for (int i = 0; i < 10; i++) {
            try {
                this.driver = new RemoteWebDriver(new URL(SELENIUM_URL), capabilities);
                return;
            } catch (WebDriverException e) {
                ex = e;
                System.out.println(String.format("Error connecting to %s: %s. Retrying", SELENIUM_URL, e));
                Thread.sleep(1000);
            }
        }
        throw ex;
    }

    @Test(priority=0)
    public void openUrl() {
  	driver.get("http://13.93.116.170:8080/login");
  	String title = driver.getTitle();
  	String page=driver.getPageSource();
  	System.out.println(page);
  	Assert.assertTrue(title.contains("Sign in [Jenkins]"));
    }
    
    @Test(priority=1)
    public void Login() {
  	  driver.findElement(By.name("j_username")).sendKeys("admin");
    	  driver.findElement(By.name("j_password")).sendKeys("7UuCMjJYGZ");
    	  try {
  			Thread.sleep(1000);
  		} catch (InterruptedException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	  
  	  driver.findElement(By.name("Submit")).click();
  	  
  	  Assert.assertEquals("Cruscotto [Jenkins]",driver.getTitle());
    }
    
    @Test(priority=2)
    public void NewElement() {
  	  try {
  			Thread.sleep(1000);
  		} catch (InterruptedException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	  driver.findElement(By.linkText("Nuovo Elemento")).click();
  	  Assert.assertEquals("Nuovo Elemento [Jenkins]",driver.getTitle());
  	  try {
  			Thread.sleep(1000);
  		} catch (InterruptedException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	  String jobName="TestAutomation";
  	  driver.findElement(By.name("name")).sendKeys("TestAutomation");
    	  try {
  			Thread.sleep(1000);
  		} catch (InterruptedException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	 
  	  driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
  	  driver.findElement(By.id("ok-button")).click();
  	  try {
  			Thread.sleep(1000);
  		} catch (InterruptedException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}	  
  	  Assert.assertEquals(jobName+" Config [Jenkins]",driver.getTitle());
  	  
    }
    
    @Test(priority=3)
    public void DeleteElement() {
  	  String jobName="TestAutomation";
  	  driver.findElement(By.linkText(jobName)).click();
  	  Assert.assertEquals(jobName+" [Jenkins]",driver.getTitle());
  	  driver.findElement(By.linkText("Elimina Pipeline")).click();
  	  
  	  	  try {
  			Thread.sleep(2000);
  		} catch (InterruptedException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	  
  	  	driver.switchTo().alert().accept();
  	  	try {
  			Thread.sleep(2000);
  		} catch (InterruptedException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	  
  	  	  
  			  
    }
    @After
    public void tearDown() throws Exception {
        if (driver != null)
            driver.quit();
    }
}