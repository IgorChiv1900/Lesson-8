import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TopUpPage {
    private WebDriver driver;

    // Локаторы для элементов страницы
    private By blockTitle = By.xpath("//h3[contains(text(), 'Онлайн пополнение без комиссии')]");
    private By paymentLogos = By.xpath("//div[contains(@class, 'payment-logos')]//img");
    private By detailsLink = By.linkText("Подробнее о сервисе");
    private By phoneNumberInput = By.id("phone-number");
    private By serviceTypeOption = By.id("service-type");
    private By continueButton = By.xpath("//button[contains(text(), 'Продолжить')]");
    private By successMessage = By.id("success-message");
    private By amountInput = By.id("amount");  // Предположительный локатор для поля ввода суммы
    private By creditCardInput = By.id("credit-card-number");  // Предположительный локатор для ввода карты
    private By paymentIcons = By.xpath("//div[contains(@class, 'card-icons')]//img");

    public TopUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getBlockTitleText() {
        return driver.findElement(blockTitle).getText();
    }

    public List<WebElement> getPaymentLogos() {
        return driver.findElements(paymentLogos);
    }

    public void clickDetailsLink() {
        driver.findElement(detailsLink).click();
    }

    public void enterPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    public void selectServiceType() {
        driver.findElement(serviceTypeOption).click();
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    public String getSuccessMessageText() {
        return driver.findElement(successMessage).getText();
    }

    public boolean isAmountInputDisplayed() {
        return driver.findElement(amountInput).isDisplayed();
    }

    public boolean isCreditCardInputDisplayed() {
        return driver.findElement(creditCardInput).isDisplayed();
    }

    public List<WebElement> getPaymentIcons() {
        return driver.findElements(paymentIcons);
    }

    // Метод для проверки наличия текста-заполнителя (placeholder) в полях
    public String getPlaceholderText(By locator) {
        return driver.findElement(locator).getAttribute("placeholder");
    }
}
