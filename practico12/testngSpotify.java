package practico12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class testngSpotify {
    /*public WebDriver explorador(String URL){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        return driver;
    }*/
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.spotify.com");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    @Test
    public void verifySpotifyTitle(){
        String esperado = "Escuchar es todo - Spotify";
        String real = driver.getTitle();
        Assert.assertEquals(real, esperado);
    }
    @Test
    public void verifySignupUrl(){
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[@data-ga-action='sign-up']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("signup"));
    }
    @Test
    public void invalidEmailTest() {
        driver.findElement(By.xpath("//button[@aria-expanded='false']")).click();
        driver.findElement(By.xpath("(//a[@data-ga-action='sign-up'])[2]")).click();//org.openqa.selenium.ElementNotInteractableException: element not interactable
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("test.mail.com");
        driver.findElement(By.xpath("//input[@id='confirm']")).sendKeys(" ");
        String texto = driver.findElement(By.xpath("//span[@class='LinkContainer-sc-1t58wcv-0 hYhKOQ']")).getText();
        Assert.assertTrue(texto.contains("@email.com"));
    }
    @Test
    public void validateExistingEmail(){
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[@data-ga-action='sign-up']")).click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("test@test.com");
        driver.findElement(By.xpath("//input[@id='confirm']")).sendKeys(" ");
        String existente = driver.findElement(By.xpath("//span[@class='LinkContainer-sc-1t58wcv-0 hYhKOQ']")).getText();
        Assert.assertTrue(existente.contains("conectado a una cuenta"));
    }
    @Test
    public void checkEqualEmailsError(){}
    @Test
    public void checkErrorMessages(){}

    @AfterTest
    public void cerar(){
        driver.close();//*[@id="mh-mobile-navigation"]/ul/li[5]/a
    }
}

