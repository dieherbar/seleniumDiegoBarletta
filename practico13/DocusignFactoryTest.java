package practico13;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DocusignFactoryTest {
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://go.docusign.com/o/trial/");
    }
    @Test
    public void verifyTitle(){
        String esperado = "DocuSign Free Trial";
        String real = driver.getTitle();
        Assert.assertEquals(real, esperado);
    }
    @AfterMethod
    public void cerrar() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }
}
