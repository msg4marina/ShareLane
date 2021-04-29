import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class Test2 {

    @Test
    public void test2SignUp2GetLogin3GetBook4GoToSHoppingCart5AssertTotalPrice() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.name("first_name")).sendKeys("Ivan");
        driver.findElement(By.name("last_name")).sendKeys("Ivanov");
        driver.findElement(By.name("email")).sendKeys("1@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("ivan");
        driver.findElement(By.name("password2")).sendKeys("ivan");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String message = driver.findElement(By.cssSelector("[class=confirmation_message]")).getText();
        assertEquals(message,
                "Account is created!",
                "Аккаунт не был создан");
        String email = driver.findElement(By.xpath("(//b)[2]")).getText();
        driver.findElement(By.xpath("//a[contains(@href,'main.py')]")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("1111");
        driver.findElement(By.cssSelector("[value=Login]")).click();
        driver.findElement(By.xpath("//a[contains(@href,'book_id')]")).click();
        driver.findElement(By.xpath("//a[contains(@href,'add_to_cart')]")).click();
        message = driver.findElement(By.cssSelector("[class=confirmation_message]")).getText();
        assertEquals(message,
                "Book was added to the Shopping Cart",
                "Логин неверный");
        driver.get("https://www.sharelane.com/cgi-bin/shopping_cart.py");
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("11");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String price = driver.findElement(By.xpath("(//td)[19]")).getText();
        //condition of discount calculation (example) price: 0-10, discount=0%, 11-29, discount=1%, 30-59,discount=2%.....
        String discount1 = driver.findElement(By.xpath("(//td)[21]")).getText();
        String discount2 = driver.findElement(By.xpath("(//td)[22]")).getText();
        assertEquals(discount2, "1", "Сумма скидки рассчитана неверно");
        String total = driver.findElement(By.xpath("(//td)[23]")).getText();
        assertEquals(total, "102.3", "Сумма рассчитана неверно");
        driver.quit();
    }
}
