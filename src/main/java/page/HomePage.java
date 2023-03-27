package page;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private final String baseUrl = "https://homebrandofficial.ru/wear";
    private final WebDriver driver;

    @FindBy(xpath = "//*[text()='Футболка Оversize']")
    private WebElement productBtn;
    @FindBy(css = "[class*=\"carticon\"]")
    private WebElement cartBtn;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openHomePage() {
        driver.get(baseUrl);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void titleShouldBe(SoftAssertions softAssert, String expectedTitle) {
        softAssert.assertThat(getPageTitle()).as("Неверное название страницы")
                .isEqualTo(expectedTitle);
    }

    public HomePage selectProduct() {
        productBtn.click();
        return this;
    }

    public HomePage clickCartBtn() {
        cartBtn.click();
        return this;
    }
}
