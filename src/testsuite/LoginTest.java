/**
 * login on herokuapp and verify login credential. has to done with Basetest class and Logintest class( extended BaseTest)
 * also check error messages after successfully login and failed login.
 */

package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {

        String baseURL = "http://the-internet.herokuapp.com/login"; // set base url

        @Before
        public void setUp(){openBrowser(baseURL);}

        @Test
        public void userShouldLoginSuccessfullyWithValidCredentials() throws InterruptedException{
            driver.findElement(By.id("username")).sendKeys("tomsmith");// enter username
            driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");//enter password
            driver.findElement(By.xpath("//i[contains(text(),'Login')]")).click();//click login button
            //verify actual and expected message.
            String expected = "Secure Area";
            String actual = driver.findElement(By.xpath("//h2")).getText();//get text from selected xpath.
            Assert.assertEquals("Correct Message not Displayed",expected,actual);
            Thread.sleep(2000);
        }

        @Test
        public void verifyTheUsernameErrorMessage() throws InterruptedException{
            driver.findElement(By.id("username")).sendKeys("xxxx");
            driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
            driver.findElement(By.xpath("//i[contains(text(),'Login')]")).click();
            String expected = "Your username is invalid!\n" + "×";
            String actual = driver.findElement(By.xpath("//div[@class = 'flash error']")).getText();
            Assert.assertEquals("Correct Message not Displayed",expected,actual);
            Thread.sleep(2000);
        }

        @Test
        public void verifyThePasswordErrorMessage()throws InterruptedException{
            driver.findElement(By.id("username")).sendKeys("tomsmith");
            driver.findElement(By.id("password")).sendKeys("xxxx");
            driver.findElement(By.xpath("//i[contains(text(),'Login')]")).click();
            String expected = "Your password is invalid!\n" + "×";
            String actual = driver.findElement(By.xpath("//div[@class = 'flash error']")).getText();
            Assert.assertEquals("Correct Message not Displayed",expected,actual);
            Thread.sleep(2000);
        }

        @After
        public void tearDown(){closeBrowser();}
    }

