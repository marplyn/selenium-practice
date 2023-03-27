package scenarios;

import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.HomePage;
import page.ProductPage;
import page.CartPage;
import page.CheckoutPage;

public class OrderTest {
    protected WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Before
    public void driverSetup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().fullscreen();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void testScenario() {
        SoftAssertions softAssertions = new SoftAssertions();
        // 1. Открыть ссылку https://homebrandofficial.ru/wear
        homePage.openHomePage();
        String expectedTitle = "Одежда для женщин и мужчин";
        homePage.titleShouldBe(softAssertions, expectedTitle);
        // 2. Нажать на товар с названием “Футболка Оversize”
        homePage.selectProduct();
        // 3. Нажать на кнопку “Добавить в корзину”
        productPage.clickAddToCartBtn();
        // 4. Нажать на иконку "Корзина"
        homePage.clickCartBtn();
        // 5. Нажать "Оформить заказ"
        cartPage.clickCheckoutBtn();
        // 6. Заполнить все поля рандомными данными (!телефон +7 (000) 000-00-00)
        checkoutPage.enterName("Иванов Иван Иванович");
        checkoutPage.enterPhone("0000000000");
        checkoutPage.enterRegionMap("Москва");
        checkoutPage.enterDeliveryAddr("Москва, Лесная, 9");
        checkoutPage.clickDeliveryRadioBtn();
        // 7. Нажать кнопку “Оформить заказ”
        checkoutPage.clickSubmitBtn();
        // 8. Проверить, что отображается текст “Укажите, пожалуйста, корректный номер телефона”
        // около поля "Телефон" и внизу страницы
        String expectedErrorMessage = "Укажите, пожалуйста, корректный номер телефона";
        checkoutPage.phoneErrorMessage(softAssertions, expectedErrorMessage);
        checkoutPage.phoneErrorBanner(softAssertions, expectedErrorMessage);
    }
}
