package Practico14.FakersTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FormWithFakers {
    WebDriver driver;

    @BeforeMethod
    public void inicializa(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://login.salesforce.com/?locale=eu");
        driver.manage().window().maximize();
    }
    @Test
    public void fillFormWithFakersTest() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        System.out.println("estamos en la pagina --> " + driver.getTitle());

        driver.findElement(By.id("signup_link")).click();

        String urlEsperada = "https://www.salesforce.com/eu/form/signup/freetrial-sales-pe/";
        String urlActual = driver.getCurrentUrl();
        Assert.assertEquals(urlActual, urlEsperada);

        driver.findElement(By.id("onetrust-accept-btn-handler")).click();

        Select seleccion = new Select(driver.findElement(By.name("CompanyEmployees")));
        seleccion.selectByValue("50");

        driver.findElement(By.name("UserFirstName")).sendKeys(DataFaker.getFirstName());
        driver.findElement(By.name("UserLastName")).sendKeys(DataFaker.getLastName());
        driver.findElement(By.name("UserTitle")).sendKeys(DataFaker.getJob());
        driver.findElement(By.name("UserEmail")).sendKeys(DataFaker.getEmail());
        driver.findElement(By.name("UserPhone")).sendKeys(DataFaker.getPhone());
        driver.findElement(By.name("CompanyName")).sendKeys("Test org");

        driver.findElement(By.name("Start my free trial")).click();
//lo de abajo no entendi la consigna
        WebDriverWait espera = new WebDriverWait(driver,20);

        WebElement mensajesError;
        mensajesError = espera.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='SubscriptionAgreement']"))));
        System.out.println(mensajesError.getText());
    }

}
