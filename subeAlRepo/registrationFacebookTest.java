package EjerciciosSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class registrationFacebookTest {
    public WebDriver getDriver(String URL){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        return driver;
    }
    @Test
    public void fullRegistrationTest() throws InterruptedException {
        WebDriver driver = getDriver("https://www.facebook.com/");

        driver.findElement(By.xpath("//*[@id=\"u_0_2\"]")).click();
        Thread.sleep(2000);

        driver.findElement(By.name("firstname")).sendKeys("John");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Smith");
        driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("55555555");
        driver.findElement(By.xpath("//input[@name='reg_passwd__']")).sendKeys("123456789");

        WebElement meses = driver.findElement(By.xpath("//select[@id='month']"));
        Select mes = new Select(meses);
        mes.selectByVisibleText("Jun");

        WebElement dias = driver.findElement(By.xpath("//select[@title='Day']"));
        Select dia = new Select(dias);
        dia.selectByValue("26");

        WebElement anios = driver.findElement(By.xpath("//select[@name='birthday_year']"));
        Select anio = new Select(anios);
        anio.selectByValue("1980");

        driver.findElement(By.xpath("//input[@value='2']")).click();
    }
}
