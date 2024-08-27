import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsByTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Установка ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Настройки браузера
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Открытие сайта
        driver.get("https://mts.by");
    }

    @AfterEach
    public void tearDown() {
        // Закрытие браузера после каждого теста
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    public void testBlockTitle() {
        // Поиск элемента заголовка блока
        WebElement blockTitle = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/div/h2)]"));

        // Проверка текста заголовка
        assertEquals("Онлайн пополнение без комиссии", blockTitle.getText(), "Название блока не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void testPaymentSystemLogos() {
        // Поиск логотипов платежных систем в блоке
        List<WebElement> paymentLogos = driver.findElements(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/div/div[2]/ul/li[1]"));

        // Проверка наличия хотя бы одного логотипа
        assertTrue(paymentLogos.size() > 0, "Логотипы платежных систем отсутствуют в блоке");
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testServiceDetailsLink() {
        // Поиск ссылки "Подробнее о сервисе"
        WebElement detailsLink = driver.findElement(By.linkText("Подробнее о сервисе"));

        // Клик по ссылке
        detailsLink.click();

        // Проверка, что произошел переход на нужную страницу
        assertTrue(driver.getCurrentUrl().contains("https://mts.by/about/service-details"), "Переход по ссылке 'Подробнее о сервисе' не произошел");
    }

    @Test
    @DisplayName("Проверка работы формы и кнопки 'Продолжить' для услуги связи")
    public void testServiceFormSubmission() {
        // Поиск и заполнение поля с номером телефона
        WebElement phoneNumberInput = driver.findElement(By.id("phone-number"));
        phoneNumberInput.sendKeys("297777777");

        // Поиск и выбор услуги связи
        WebElement serviceType = driver.findElement(By.id("service-type"));
        serviceType.click();

        // Поиск и клик по кнопке "Продолжить"
        WebElement continueButton = driver.findElement(By.xpath("//button[contains(text(), 'Продолжить')]"));
        continueButton.click();

        // Проверка на успешную обработку формы
        WebElement successMessage = driver.findElement(By.id("success-message")); // пример, нужно заменить на правильный локатор
        assertTrue(successMessage.isDisplayed(), "Сообщение об успешной обработке формы не отображается");
    }
}