package page;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    WebDriver driver;

    @FindBy(css = "input[name=\"query\"]")
    private WebElement queryInput;
    @FindBy(css = "[class*=\"search-btn\"]")
    private WebElement searchBtn;
    @FindBy(css = "span[class*=\"prodsnumber\"]")
    private WebElement productsNumber;
    @FindBy(css = "[class*=prod-name]")
    private WebElement productName;
    @FindBy(css = "[class*=product-price]")
    private WebElement productPrice;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SearchPage enterQuery(String query) {
        queryInput.sendKeys(query);
        return this;
    }

    public SearchPage clickSearch() {
        searchBtn.click();
        return this;
    }

    public String getProductsNumber() {
        return productsNumber.getText();
    }

    public void numberShouldBe(SoftAssertions softAssert, String expectedNumber) {
        softAssert.assertThat(getProductsNumber()).as("Неверное количество товаров")
                .isEqualTo(expectedNumber);
    }

    public String getProductsName() {
        return productName.getText();
    }

    public void nameShouldBe(SoftAssertions softAssert, String expectedName) {
        softAssert.assertThat(getProductsName()).as("Неверное название товара")
                .isEqualTo(expectedName);
    }

    public String getProductsPrice() {
        return productPrice.getText();
    }

    public void priceShouldBe(SoftAssertions softAssert, String expectedPrice) {
        softAssert.assertThat(getProductsPrice()).as("Неверная цена товара")
                .isEqualTo(expectedPrice);
    }
}
