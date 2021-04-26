import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class Test2 {

        @Test
        public void test2SignUp2GetLogin3GetBook4GoToSHoppingCart5AssertTotalPrice () {
            //установка переменной среды
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            //Вставить неявное ожидание
            driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
            //Открыть страницу https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
            driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
            //ввести любое имя  (например Ivan)
            driver.findElement(By.name("first_name")).sendKeys("Ivan");
            //ввести любую фамилию (например Ivanov)
            driver.findElement(By.name("last_name")).sendKeys("Ivanov");
            //ввести любой email (например 1@gmail.com)
            driver.findElement(By.name("email")).sendKeys("1@gmail.com");
            //ввести любой password (например ivan)
            driver.findElement(By.name("password1")).sendKeys("ivan");
            //ввести второй раз password (например ivan)
            driver.findElement(By.name("password2")).sendKeys("ivan");
            //нажать кнопку Register
            driver.findElement(By.cssSelector("[value=Register]")).click();
            String message = driver.findElement(By.cssSelector("[class=confirmation_message]")).getText();
            assertEquals(message,
                    "Account is created!",
                    "Аккаунт не был создан");
            //забрать email
            //получить логин со страницы (локатор By.xpath("(//b)[2]")
            driver.findElement(By.xpath("(//b)[2]")).getText();
            //залогиниться, нажав на кнопку here
            driver.findElement(By.xpath("//a[contains(@href,'main.py')]")).click();
            //ввести Email любой, взятый с https://sharelane.com/users.html (например olga_gupta@414.52.sharelane.com	)
            driver.findElement(By.name("email")).sendKeys("olga_gupta@414.52.sharelane.com\t");
            //ввести Password
            driver.findElement(By.name("password")).sendKeys("1111");
            //нажать кнопку Login
            driver.findElement(By.cssSelector("[value=Login]")).click();
            //выбрать книгу
            driver.findElement(By.xpath("//a[contains(@href,'book_id')]")).click();
            //добавить товар в корзину (локатор By.xpath("//a[contains(@href, 'add_to_cart')]") )
            driver.findElement(By.xpath("//a[contains(@href,'add_to_cart')]")).click();
            message = driver.findElement(By.cssSelector("[class=confirmation_message]")).getText();
            assertEquals(message,
                    "Book was added to the Shopping Cart",
                    "Логин неверный");
            //открыть SHopping Cart https://www.sharelane.com/cgi-bin/shopping_cart.py
            driver.get("https://www.sharelane.com/cgi-bin/shopping_cart.py");
            //в поле quantity необходимо очистить поле
            driver.findElement(By.name("q")).clear();
            //нажать кнопку Update
            driver.findElement(By.cssSelector("[value=Update]")).click();
            //в поле quantity ввести  количество (например 11)
            driver.findElement(By.name("q")).sendKeys("1");
            //нажать кнопку Update
            driver.findElement(By.cssSelector("[value=Update]")).click();
            assertEquals(117.70, 102.30, "Total price рассчитан неверно");
            //закрыть браузер
            driver.quit();
        }
    }
