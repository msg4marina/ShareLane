import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest {

    @Test
    public void zipCodeShouldExcept5Digits() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Вставить неявное ожидание
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        //ввести любые 5 цифр (например 12345)
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        //нажать кнопку Continue
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        //убедиться, что мы на странице Sign Up
        boolean isOpened = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        assertTrue(isOpened, "страница регистрации не открылась");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void zipCodeShouldNotExcept6Digits() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Вставить неявное ожидание
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        //ввести любые 5 цифр (например 123456)
        driver.findElement(By.name("zip_code")).sendKeys("123456");
        //нажать кнопку Continue
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error,
                "Oops, error on page. ZIP code should have 5 digits",
                "сообщение об ошибке некорректно");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void zipCodeShouldNotExcept4Digits() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Вставить неявное ожидание
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        //ввести любые 5 цифр (например 1234)
        driver.findElement(By.name("zip_code")).sendKeys("1234");
        //нажать кнопку Continue
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error,
                "Oops, error on page. ZIP code should have 5 digits",
                "сообщение об ошибке некорректно");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void successfullSignUp() {
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
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void notSuccessfullSignUpFirstNameShouldBeNotNul() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Вставить неявное ожидание
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        //оставить поле Firs Name незаполненным
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
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error,
                "Oops, error on page. Some of your fields have invalid data or email was previously used",
                "First Name должно быть заполнено");
        //закрыть браузер
        driver.quit();
    }
    @Test
    public void notSuccessfullSignUpFirstNameShouldNotExceptCyrillicCharacters() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Вставить неявное ожидание
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        //ввести любое имя  (например Маруся)
        driver.findElement(By.name("first_name")).sendKeys("Маруся");
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
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error,
                "Oops, error on page. Some of your fields have invalid data or email was previously used",
                "В First Name использование кириллицы недопустимо");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void notSuccessfullSignUpFirstNameShouldNotExceptHyphen() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Вставить неявное ожидание
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        //Открыть страницу https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        //ввести любое имя  (например Anna-Maria)
        driver.findElement(By.name("first_name")).sendKeys("Anna-Maria");
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
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error,
                "Oops, error on page. Some of your fields have invalid data or email was previously used",
                "В First Name использование символа - (черточка) недопустимо");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void notSuccessfullSignUpEmailShouldBeNotNul() {
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
        //оставить поле Email незаполненным
        //ввести любой password (например ivan)
        driver.findElement(By.name("password1")).sendKeys("ivan");
        //ввести второй раз password (например ivan)
        driver.findElement(By.name("password2")).sendKeys("ivan");
        //нажать кнопку Register
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error,
                "Oops, error on page. Some of your fields have invalid data or email was previously used",
                "Email должен быть заполнен");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void notSuccessfullSignUpLoginInEmailShouldNotExceptMoreThan64Symbols() {
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
        //ввести любой email (например O'Bender00078987611111111111111111111111111111111111111111111111@gmail.com)
        driver.findElement(By.name("email")).sendKeys("O'Bender00078987611111111111111111111111111111111111111111111111@gmail.com");
        //ввести любой password (например ivan)
        driver.findElement(By.name("password1")).sendKeys("ivan");
        //ввести второй раз password (например ivan)
        driver.findElement(By.name("password2")).sendKeys("ivan)");
        //нажать кнопку Register
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.name("Internal Server Error")).getText();
        assertEquals(error,
                "The server encountered an internal error",
                "В Email общая длина логина может быть не более 64 символов");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void notSuccessfullSignUpPasswordShouldNotExceptLessThan4Symbols() {
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
        //оставить поле Email незаполненным
        //ввести любой password (например ivan)
        driver.findElement(By.name("password1")).sendKeys("123");
        //ввести второй раз password (например ivan)
        driver.findElement(By.name("password2")).sendKeys("123)");
        //нажать кнопку Register
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error,
                "Oops, error on page. Some of your fields have invalid data or email was previously used",
                "Email должен быть заполнен");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void notSuccessfullSignUpConfirmPasswordShouldBeNotNul() {
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
        //поле Confirm Password* оставить пустым
        //нажать кнопку Register
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error,
                "Oops, error on page. Some of your fields have invalid data or email was previously used",
                "Confirm Password должно быть заполнено");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void notSuccessfullSignUpConfirmPasswordAndPasswordShouldBeEqual() {
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
        //поле Confirm Password заполнить любыми символами отличными
        // от введенного password (например 4879риавывлоиыл)_
        driver.findElement(By.name("password2")).sendKeys("4879риавывлоиыл)");
        //нажать кнопку Register
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error,
                "Oops, error on page. Some of your fields have invalid data or email was previously used",
                "Некорректное сообщение об ошибке");
        //закрыть браузер
        driver.quit();
    }
}