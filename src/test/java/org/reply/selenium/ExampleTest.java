package org.reply.selenium;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExampleTest {

    private final static String SELENIUM_URL = System.getProperty("selenium.url", "http://localhost:4444/wd/hub");
    private final static String SELENIUM_BROWSER = System.getProperty("selenium.browser", "chrome");
    private final static int SLEEP = Integer.parseInt(System.getProperty("sleep", "3000"));

    protected WebDriver driver;

    @Before
    public void setUp() throws Exception {
    	//System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
    	//driver = new ChromeDriver();
    	
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
    public void Stage1_Verify_Polling_page() throws Exception {
    	driver.get("http://52.166.107.201/pocPolling");
    	System.out.println(driver.findElement(By.className("badge-primary")).getText());
    	Thread.sleep(SLEEP);
    	Assert.assertEquals("Polling",driver.findElement(By.className("badge-primary")).getText());  
        }
    
    @Test
    public void Stage2_Verify_WebSocket_page() throws Exception {
    	driver.get("http://52.166.107.201/");
    	System.out.println(driver.findElement(By.className("badge-primary")).getText());
    	Thread.sleep(SLEEP);
    	Assert.assertEquals("Web Socket",driver.findElement(By.className("badge-primary")).getText());  
    }
    
    @Test
    public void Stage3_Verify_FormAction_page() throws Exception {
    	driver.get("http://52.166.107.201/");
      	Thread.sleep(SLEEP);
      	System.out.println("FormAction");
    	
      	driver.findElement(By.id("TestAutoButton")).click();
      	System.out.println("step 1");
    	Thread.sleep(SLEEP);
      	driver.findElement(By.name("firstname")).sendKeys("Marco");
      	System.out.println("step 2");
    	driver.findElement(By.name("lastname")).sendKeys("Giuliani");
    	System.out.println("step 3");
    	Select dropdown = new Select(driver.findElement(By.id("country")));
    	dropdown.selectByVisibleText("Canada");
    	System.out.println("step 4");
    	driver.findElement(By.id("subject")).sendKeys("Questo è un esempio");
    	System.out.println("step 5");
    	Thread.sleep(SLEEP);
    	
      	driver.findElement(By.id("testAutoSubmit")).click();
      	
    	Thread.sleep(SLEEP);
    	
    	}
    
    /*@Test
    public void Stage0_Login() throws Exception {
      
    	System.out.println("Login Test");
    	driver.get("http://52.174.68.188:8080/login");
  
        Thread.sleep(SLEEP);
        driver.findElement(By.name("j_username")).sendKeys("ElgUsr");
    	driver.findElement(By.name("j_password")).sendKeys("ElgUsr");
    	driver.findElement(By.name("Submit")).click();
    	
    	Assert.assertEquals("Dashboard [Jenkins]",driver.getTitle());
    	
    	
    	
    
   	  	
   	  	
    }
*/
   /* @Test
    public void Stage1_NewElement() throws Exception {
    	System.out.println("New Item Test");
    	
    	driver.get("http://52.174.68.188:8080/login");
   
        Thread.sleep(SLEEP);
        driver.findElement(By.name("j_username")).sendKeys("ElgUsr");
    	driver.findElement(By.name("j_password")).sendKeys("ElgUsr");
    	driver.findElement(By.name("Submit")).click();
    	
    	Assert.assertEquals("Dashboard [Jenkins]",driver.getTitle());
    	
    
    	Thread.sleep(SLEEP);
    	driver.findElement(By.linkText("New Item")).click();
    	Assert.assertEquals("New Item [Jenkins]",driver.getTitle());
    	Thread.sleep(SLEEP);
    	  
    	driver.findElement(By.name("name")).sendKeys("TestAutomation");
  	  	driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
   	  	
   	  	driver.findElement(By.id("ok-button")).click();
   	 
   	  	
    } */
    
    /*@Test
    public void Stage2_DeleteElement() throws Exception {
    	System.out.println("Delete Item Test");
    	
    	driver.get("http://52.174.68.188:8080/login");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        Thread.sleep(SLEEP);
        driver.findElement(By.name("j_username")).sendKeys("ElgUsr");
    	driver.findElement(By.name("j_password")).sendKeys("ElgUsr");
    	driver.findElement(By.name("Submit")).click();

    	
    	Thread.sleep(SLEEP);
        String jobName="TestAutomation";
   	  	
   	  	driver.findElement(By.linkText(jobName)).click();
   	  	Thread.sleep(SLEEP);
     
   	  	driver.findElement(By.linkText("Delete Pipeline")).click();
   	  	Thread.sleep(SLEEP);
     
   	  	driver.switchTo().alert().accept();
   	  	System.out.println("New Element delete End");
   	 
   	  	
    }*/
    
    @After
    public void tearDown() throws Exception {
        if (driver != null)
            driver.quit();
    }
}