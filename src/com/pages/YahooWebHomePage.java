package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class YahooWebHomePage {

    WebDriver driver;
    private int LONG_WAIT_TIME = 10;
    private int SHORT_WAIT_TIME = 1;

    // Locators
    private By yahooSearchBox = By.id("ybar-sbq");
    private By yahooAutoSuggestionInstrumentsData = By.xpath("//ul[@role='listbox']/li[@data-test='srch-sym']");
    private By teslaStockPrice = By.xpath("//span[@data-testid='qsp-price']");
    private By previousCloseStockPrice = By.xpath("//fin-streamer[@data-field='regularMarketPreviousClose']");
    private By volumePrice = By.xpath("//fin-streamer[@data-field='regularMarketVolume']");

    // Constructor
    public YahooWebHomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void clickAndSendTextToSearchBox(String input) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(LONG_WAIT_TIME));
        wait.until(d -> driver.findElement(yahooSearchBox).isDisplayed());
        driver.findElement(yahooSearchBox).sendKeys(input);
    }

    public List<WebElement> getAutoSuggestionsData() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(SHORT_WAIT_TIME));
        List<WebElement> list = driver.findElements(yahooAutoSuggestionInstrumentsData);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(d -> list.get(0).isDisplayed());
        return list;
    }

    public int getStockPrice() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(LONG_WAIT_TIME));
        String stockPriceValue = driver.findElement(teslaStockPrice).getText();
        return (int) Float.parseFloat(stockPriceValue);
    }

    public float getPreviousCloseStockPrice() {
        WebElement item = driver.findElement(previousCloseStockPrice);
        return Float.parseFloat(item.getText());
    }

    public String getVolumePrice() {
        return driver.findElement(volumePrice).getText();
    }
}
