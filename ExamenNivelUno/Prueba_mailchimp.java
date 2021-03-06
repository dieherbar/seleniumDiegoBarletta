package ExamenNivelUno;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Prueba_mailchimp {
    WebDriver driver;
    Faker faker = new Faker();
    private String correo;
    private String pass;

    public Prueba_mailchimp(String email,String password){
        this.correo = email;
        this.pass =password;
    }
    public Prueba_mailchimp(){
        this.correo ="abc@mail.com";
        this.pass = "pass123";
    }

    @BeforeMethod
    public void inicializa(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://login.mailchimp.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
    }
    @Test(priority = 5)
    public void validarTituloTest(){
        String tituloPagina = driver.getTitle();
        Assert.assertEquals(tituloPagina,"Login | Mailchimp");
    }
    @Test(priority = 4)
    public void iniciarSesionPageTest(){
        String textoLogin = driver.findElement(By.xpath("//*[@class='text-align--center !margin-bottom--lv3']")).getText();
        Assert.assertTrue(textoLogin.equals("Log In"));
        String needAccount = driver.findElement(By.xpath("//p[@class='flex align-items--baseline flex-wrap--wrap']/span")).getText();
        Assert.assertTrue(needAccount.contains("Mailchimp account"));
    }
    @Test(priority = 3)
    public void loginErrorTest(){
        driver.findElement(By.id("username")).sendKeys("xxxxx@gmail.com");
        driver.findElement(By.xpath("//button[@value='log in']")).click();
        String textoErrorLogin = driver.findElement(By.xpath("//div[@class='media-body']")).getText();
        Assert.assertTrue(textoErrorLogin.contains("forgot your password"));
    }
    @Test(priority = 2)
    public void fakeEmailTest() throws InterruptedException {
        driver.navigate().to("https://login.mailchimp.com/signup/");//-->Con esta linea me daba error hasta que puse el if

        List<WebElement>listaBtn = driver.findElements(By.tagName("button"));
        for (WebElement textBtn : listaBtn){
            if (textBtn.getText().contains("Accept all cookies")){
                driver.findElement(By.id("onetrust-accept-btn-handler")).click();
            }

        }

        Thread.sleep(2000);
        WebElement mailRegsitro = driver.findElement(By.id("email"));
        String fakeMail = faker.internet().emailAddress();
        mailRegsitro.sendKeys(fakeMail);

        Assert.assertTrue(driver.getCurrentUrl().contains("signup"));
    }
    @Test(priority = 1,dataProvider = "mailchimpProvider",dataProviderClass = DataProviderMailchimp.class )
    public void dataProviderEmailTest(String correo, String pass){
        driver.findElement(By.id("username")).sendKeys(correo);
        driver.findElement(By.id("password")).sendKeys(pass);
        driver.findElement(By.xpath("//button[@value='log in']")).click();

        String mensajeErrorLogin = driver.findElement(By.xpath("//div[@class='c-mediaBody--centered']")).getText();

        Assert.assertTrue(mensajeErrorLogin.contains("Can we help you recover your username?"));
        //me aparecio una validacion de "I'm not a robot" y no pude pasar los assert
    }

    @AfterMethod
    public void cerrar(){
        driver.close();
    }
}
