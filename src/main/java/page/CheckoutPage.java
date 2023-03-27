package page;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    WebDriver driver;

    @FindBy(css = "input[id=input_1496239431201]")
    private WebElement nameInput;
    @FindBy(css = "[data-input-lid=\"1496239478607\"] input[type=tel]")
    private WebElement phoneNumberInput;
    @FindBy(css = "input[id=input_1627385047591]")
    private WebElement regionMapInput;
    @FindBy(css = "[id=\"input_1630305196291\"]")
    private WebElement deliveryAddressInput;
    @FindBy(css = "label[data-service-id=\"1642331752\"] [class=\"t-radio__indicator\"]")
    private WebElement deliveryRadioBtn;
    @FindBy(xpath = "//button[@type='submit' and contains(., 'ОФОРМИТЬ')]")
    private WebElement submitBtn;
    @FindBy(css = "[data-input-lid='1496239478607'] .t-input-error")
    private WebElement phoneErrorText;
    @FindBy(css = "[class*=\"t706__cartpage-form t-col t-col_6\"] [class=\"t-form__errorbox-item js-rule-error js-rule-error-phone\"]")
    private WebElement phoneErrorBanner;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage enterName(String name) {
        nameInput.sendKeys(name);
        return this;
    }

    public CheckoutPage enterPhone(String phoneNum) {
        phoneNumberInput.sendKeys(phoneNum);
        return this;
    }

    public CheckoutPage enterRegionMap(String regionMap) {
        regionMapInput.sendKeys(regionMap);
        return this;
    }

    public CheckoutPage enterDeliveryAddr(String deliveryAddr) {
        deliveryAddressInput.sendKeys(deliveryAddr);
        return this;
    }

    public CheckoutPage clickDeliveryRadioBtn() {
        deliveryRadioBtn.click();
        return this;
    }

    public CheckoutPage clickSubmitBtn() {
        submitBtn.click();
        return this;
    }

    public void phoneErrorMessage(SoftAssertions softAssert, String message) {
        softAssert.assertThat(phoneErrorText.getText()).as("Отсутствует сообщение " +
                        "об ошибке под заполняемым полем")
                .isEqualTo(message);
    }

    public void phoneErrorBanner(SoftAssertions softAssert, String message) {
        softAssert.assertThat(phoneErrorBanner.getText()).as("Отсутствует сообщение " +
                        "об ошибке внизу страницы")
                .isEqualTo(message);
    }
}
