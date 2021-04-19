import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest {

    @Test
    public void zipCodeShouldExcept5Digits() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
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
    public void NotSuccessfullSignUp_FirstNameShouldBeNotNul() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
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
    public void NotSuccessfullSignUp_FirstNameShouldNotExceptCyrillicCharacters() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
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
    public void NotSuccessfullSignUp_FirstNameShouldNotExceptHyphen() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
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
    public void NotSuccessfullSignUp_EmailShouldBeNotNul() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
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
    public void NotSuccessfullSignUp_LoginInEmailShouldNotExceptMoreThan64Symbols() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
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
                "The server encountered an internal error or\n" +
                        "misconfiguration and was unable to complete\n" +
                        "your request.",
                "В Email общая длина логина может быть не более 64 символов");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void NotSuccessfullSignUp_PasswordShouldNotExceptLessThan4Symbols() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
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
    public void NotSuccessfullSignUp_ConfirmPasswordShouldBeNotNul() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
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
    public void NotSuccessfullSignUp_ConfirmPasswordAndPasswordShouldBeEqual() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
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

    @Test
    public void successfullLogin() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Открыть страницу https://www.sharelane.com/cgi-bin/main.py
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        //ввести Email автоматически сгенерированный  после  регистрации
        // на странице Account is created! (например zhi_chen@242.59.sharelane.com)
        driver.findElement(By.name("email")).sendKeys("zhi_chen@242.59.sharelane.com");
//ввести password 1111
        driver.findElement(By.name("password")).sendKeys("1111");
        //нажать кнопку Register
        driver.findElement(By.cssSelector("[value=Login]")).click();
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void NotsuccessfullLogin_EmailisNull() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Открыть страницу https://www.sharelane.com/cgi-bin/main.py
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        //Email оставить пустым
        driver.findElement(By.name("email")).sendKeys("1@gmail.com");
//ввести password 1111
        driver.findElement(By.name("password")).sendKeys("1111");
        //нажать кнопку Register
        driver.findElement(By.cssSelector("[value=Login]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error,
                "Oops, error. Email and/or password don't match our records",
                "ввести Email автоматически сгенерированный  после  регистрации " +
                        "на странице Account is created!");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void NotsuccessfullLogin_WrongEmail() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Открыть страницу https://www.sharelane.com/cgi-bin/main.py
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        //ввести Email указанный при регистрации(например 1@gmail.com)
        driver.findElement(By.name("email")).sendKeys("1@gmail.com");
//ввести password 1111
        driver.findElement(By.name("password")).sendKeys("1111");
        //нажать кнопку Register
        driver.findElement(By.cssSelector("[value=Login]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error,
                "Oops, error. Email and/or password don't match our records",
                "ввести Email автоматически сгенерированный  после  регистрации " +
                        "на странице Account is created!");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void NotsuccessfullLogin_WrongPassword() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Открыть страницу https://www.sharelane.com/cgi-bin/main.py
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        //ввести Email автоматически сгенерированный  после  регистрации
        // на странице Account is created! (например zhi_chen@242.59.sharelane.com)
        driver.findElement(By.name("email")).sendKeys("zhi_chen@242.59.sharelane.com");
//ввести password неравный 1111 (например львыжаокуешщ1)
        driver.findElement(By.name("password")).sendKeys("львыжаокуешщ1");
        //нажать кнопку Register
        driver.findElement(By.cssSelector("[value=Login]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error,
                "Oops, error. Email and/or password don't match our records",
                "ввести 1111");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void NotsuccessfullLogin_PasswordisNull() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Открыть страницу https://www.sharelane.com/cgi-bin/main.py
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        //ввести Email автоматически сгенерированный  после  регистрации
        // на странице Account is created! (например zhi_chen@242.59.sharelane.com)
        driver.findElement(By.name("email")).sendKeys("zhi_chen@242.59.sharelane.com");
//password оставить пустым
        //нажать кнопку Register
        driver.findElement(By.cssSelector("[value=Login]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error,
                "Oops, error. Email and/or password don't match our records",
                "ввести 1111");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void Search_Authors() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Открыть страницу https://www.sharelane.com/cgi-bin/main.py
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        //в поле Search ввести  Имя и Фамилию автора книги (например Lev Tolstoy)
        driver.findElement(By.name("keyword")).sendKeys("Lev Tolstoy");
        //нажать кнопку Search
        driver.findElement(By.cssSelector("[value=Search]")).click();
        String error = driver.findElement(By.cssSelector("[class=confirmation_message]")).getText();
        assertEquals(error,
                "Nothing is found :(",
                "Поиск по автору не предусмотрен");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void Search_AssignedAuthors() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Открыть страницу https://www.sharelane.com/cgi-bin/main.py
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        //в поле Search ввести  Фамилию автора книги, указанной на сайте
        //(например Mark Twain)
        driver.findElement(By.name("keyword")).sendKeys("Mark Twain");
        //нажать кнопку Search
        driver.findElement(By.cssSelector("[value=Search]")).click();
        String error = driver.findElement(By.cssSelector("[class=confirmation_message]")).getText();
        assertEquals(error,
                "Nothing is found :(",
                "Поиск по автору не предусмотрен");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void Search_book() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Открыть страницу https://www.sharelane.com/cgi-bin/main.py
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        //в поле Search ввести  люое известное наименование книги, неуказанной на сайте
        //(например Little Red Riding Hood)
        driver.findElement(By.name("keyword")).sendKeys("Little Red Riding Hood");
        //нажать кнопку Search
        driver.findElement(By.cssSelector("[value=Search]")).click();
        String error = driver.findElement(By.cssSelector("[class=confirmation_message]")).getText();
        assertEquals(error,
                "Nothing is found :(",
                "Поиск может выполняться только по наименованиям книг, указанных на сайте");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void Search_AssignedBook() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Открыть страницу https://www.sharelane.com/cgi-bin/main.py
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        //в поле Search ввести  наименование книги, указанной на сайте
        //(например The Adventures of Tom Sawyer)
        driver.findElement(By.name("keyword")).sendKeys("The Adventures of Tom Sawyer");
        //нажать кнопку Search
        driver.findElement(By.cssSelector("[value=Search]")).click();
        String message = driver.findElement(By.cssSelector("[class=grey_bg]")).getText();
        assertEquals(message,
                "Search",
                "Книга найдена");
        //закрыть браузер
        driver.quit();
    }

    @Test
    public void ShoppingCart_CheckQuantity() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Открыть страницу https://www.sharelane.com/cgi-bin/shopping_cart.py
        driver.get("https://www.sharelane.com/cgi-bin/shopping_cart.py");
        //в поле quantity ввести  количество приобретаемых книг больше 1 штуки
        //(например 2)
        driver.findElement(By.name("value")).sendKeys("2");
        //нажать кнопку Update
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String message = driver.findElement(By.cssSelector("[class=confirmation_message]")).getText();
        assertEquals(message,
                "Cart Updated",
                "Количество экземпляров приобретаемых книг обновлено");
        //закрыть браузер
        driver.quit();
    }

}