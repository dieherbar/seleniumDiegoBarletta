package Practico14.FakersTest;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TestingErrors {
    WebDriver driver;
    public static Faker faker = new Faker();

    @BeforeMethod
    public void inicializa(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://login.salesforce.com/?locale=eu");
        driver.manage().window().maximize();
    }
    @Test
    public void TestingErrorMessages(){
        System.out.println("estamos en la pagina --> " + driver.getTitle());

        driver.findElement(By.id("signup_link")).click();

        String urlEsperada = "https://www.salesforce.com/eu/form/signup/freetrial-sales-pe/";
        String urlActual = driver.getCurrentUrl();
        Assert.assertEquals(urlActual,urlEsperada);

        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        driver.findElement(By.name("Start my free trial")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("error-msg"));

        Boolean nameError = false;
        Boolean lastNameError = false;
        Boolean titleError = false;
        Boolean emailError = false;

        for (WebElement elementoWeb : listaErrores){
            System.out.println(elementoWeb.getText());
            if(elementoWeb.getText().equals("Enter your first name")){
                nameError = true;
            }else if (elementoWeb.getText().equals("Enter your last name")){
                lastNameError = true;
            }else if (elementoWeb.getText().equals("Enter your title")) {
                titleError = true;
            }else if (elementoWeb.getText().equals("Enter a valid email address")) {
                emailError = true;
            }
        }
        Assert.assertTrue(nameError);
        Assert.assertTrue(lastNameError);
        Assert.assertTrue(titleError);
        Assert.assertTrue(emailError);

        String nombre = faker.name().firstName();
        String apellido = faker.name().lastName();
        String titulo = faker.company().profession();
        String correo = faker.internet().emailAddress();

        emailError = false;

        driver.findElement(By.name("UserFirstName")).sendKeys(nombre);
        driver.findElement(By.name("UserLastName")).sendKeys(apellido);
        driver.findElement(By.name("UserTitle")).sendKeys(titulo);
        driver.findElement(By.name("UserEmail")).sendKeys(correo);
        driver.findElement(By.name("UserPhone")).click();

        listaErrores = driver.findElements(By.className("error-msg"));
        for (WebElement elementoWeb : listaErrores){
            if (elementoWeb.getText().equals("Enter a valid email address")) {
                emailError = true;
            }
        }
        Assert.assertFalse(emailError);
    }
}
