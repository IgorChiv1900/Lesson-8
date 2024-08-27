import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TopUpTest extends BaseTest {

    @Test
    public void testBlockTitle() {
        TopUpPage topUpPage = new TopUpPage(driver);
        String title = topUpPage.getBlockTitleText();
        assertEquals("Онлайн пополнение без комиссии", title, "Название блока не соответствует ожидаемому");
    }

    @Test
    public void testPaymentSystemLogos() {
        TopUpPage topUpPage = new TopUpPage(driver);
        List<WebElement> logos = topUpPage.getPaymentLogos();
        assertTrue(logos.size() > 0, "Логотипы платежных систем отсутствуют в блоке");
    }

    @Test
    public void testServiceDetailsLink() {
        TopUpPage topUpPage = new TopUpPage(driver);
        topUpPage.clickDetailsLink();
        assertTrue(driver.getCurrentUrl().contains("about/service-details"), "Переход по ссылке 'Подробнее о сервисе' не произошел");
    }

    @Test
    public void testFormPlaceholders() {
        TopUpPage topUpPage = new TopUpPage(driver);

        // Локаторы для полей формы
        By phoneInputLocator = By.id("phone-number");
        By amountInputLocator = By.id("amount");
        By creditCardInputLocator = By.id("credit-card-number");

        // Проверка placeholder для каждого поля
        assertEquals("Введите номер телефона", topUpPage.getPlaceholderText(phoneInputLocator), "Неверный текст в placeholder для поля ввода номера телефона");
        assertEquals("Введите сумму", topUpPage.getPlaceholderText(amountInputLocator), "Неверный текст в placeholder для поля ввода суммы");
        assertEquals("Введите номер карты", topUpPage.getPlaceholderText(creditCardInputLocator), "Неверный текст в placeholder для поля ввода реквизитов карты");
    }

    @Test
    public void testServiceFormSubmission() {
        TopUpPage topUpPage = new TopUpPage(driver);

        // Заполнение полей и отправка формы
        topUpPage.enterPhoneNumber("297777777");
        topUpPage.selectServiceType();
        topUpPage.clickContinueButton();

        // Проверка успешного сообщения и элементов в диалоговом окне
        assertTrue(topUpPage.isAmountInputDisplayed(), "Поле ввода суммы не отображается");
        assertTrue(topUpPage.isCreditCardInputDisplayed(), "Поле ввода карты не отображается");

        // Проверка правильности суммы и номера телефона
        assertEquals("297777777", driver.findElement(By.id("summary-phone-number")).getText(), "Неверный номер телефона в диалоговом окне");
        assertEquals("10 BYN", driver.findElement(By.id("summary-amount")).getText(), "Неверная сумма в диалоговом окне");

        // Проверка наличия иконок платежных систем в окне
        List<WebElement> paymentIcons = topUpPage.getPaymentIcons();
        assertTrue(paymentIcons.size() > 0, "Иконки платёжных систем отсутствуют в диалоговом окне");
    }
}

