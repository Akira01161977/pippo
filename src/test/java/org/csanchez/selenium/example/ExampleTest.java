package org.csanchez.selenium.example;

import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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

    
    
    @Test
    public void Stage0_Login() throws Exception {
      
    	/************ LOGIN TEST **************/
    	driver.get("http://13.93.116.170:8080/login");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        Thread.sleep(SLEEP);
        System.out.println("Login");
        System.out.println(driver.getTitle());
        driver.findElement(By.name("j_username")).sendKeys("admin");
    	driver.findElement(By.name("j_password")).sendKeys("7UuCMjJYGZ");
    	driver.findElement(By.name("Submit")).click();
    	System.out.println(driver.getTitle());
        
    	Assert.assertEquals("Dashboard [Jenkins]",driver.getTitle());
    	System.out.println("Login OK");
        
        /**************************************/
    	
    	Thread.sleep(5000);
    	
    
   	  	
   	  	
    }

    @Test
    public void Stage1_NewElement() throws Exception {
    	/************ Create New Job **************/
    	System.out.println("New Element");
    	System.out.println(driver.getTitle());
        
    	driver.findElement(By.linkText("New Element")).click();
    	Thread.sleep(5000);
    	System.out.println(driver.getTitle());
        
    	Assert.assertEquals("New Element [Jenkins]",driver.getTitle());
  	  
    	driver.findElement(By.name("name")).sendKeys("TestAutomation");
   	  	driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
   	  	driver.findElement(By.id("ok-button")).click();
   	  	System.out.println("New Element End");
     
   	  	/******************************************/
   	  	Thread.sleep(5000);
 	
    }
    
    @Test
    public void Stage3_DeleteElement() throws Exception {
      	/**************** Delete Job ***************/
   	  	System.out.println("New Element delete");

    	String jobName="TestAutomation";
   	  	
   	  	driver.findElement(By.linkText(jobName)).click();
   	  	driver.findElement(By.linkText("Elimina Pipeline")).click();
   	  	Thread.sleep(5000);
 	
   	  	driver.switchTo().alert().accept();
   	  	System.out.println("New Element delete End");
     
   	  	/*******************************************/
   	
    }
    
    @After
    public void tearDown() throws Exception {
        if (driver != null)
            driver.quit();
    }
}