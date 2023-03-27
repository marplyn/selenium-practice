package scenarios;

import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.HomePage;
import page.SearchPage;

public class SearchTest {
    protected WebDriver driver;
    private HomePage homePage;
    private SearchPage search;

    @Before
    public void driverSetup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().fullscreen();
        homePage = new HomePage(driver);
        search = new SearchPage(driver);
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
        // 2. В строку поиска ввести “Лонгслив White&Green
        search.enterQuery("Лонгслив White&Green")
                // 3. Нажать на иконку “Поиск”
                .clickSearch();
        // 4. Найден 1 товар
        String expectedNumber = "1";
        search.numberShouldBe(softAssertions, expectedNumber);
        // 5. Название товара “Лонгслив White&Green”
        String expectedName = "Лонгслив White&Green";
        search.nameShouldBe(softAssertions, expectedName);
        // 6. Стоимость товара “2800”
        String expectedPrice = "2800";
        search.priceShouldBe(softAssertions, expectedPrice);
    }
}
