package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class YahooFinTest {
    WebDriver driver;
    YahooWebHomePage homePage;
    WebElement ele = null;
    Properties prop = new Properties();

    @BeforeClass
    public void setUp() {
        loadProperties();
        if (prop.getProperty("browserName").equals("chrome")) {
            System.setProperty(prop.getProperty("chromeDriverProperty"), prop.getProperty("chromeDriverPath"));
            driver = new ChromeDriver();
            driver.get(prop.getProperty("URL"));
            driver.manage().window().maximize();
            homePage = new YahooWebHomePage(driver);
        }else{
            System.out.println("Pls input proper browser name/driver configuration in local.");
        }
    }

    @Test(priority = 1)
    public void verifyAutoSuggestValue() {
        homePage.clickAndSendTextToSearchBox(prop.getProperty("inputSearchKeywordInstrument"));
        List<WebElement> list = homePage.getAutoSuggestionsData();
        ele = list.get(0);
        String txt = ele.getDomAttribute("title");
        Assert.assertEquals(txt, prop.getProperty("expectedInstrumentTitle"), "Details mismatch. Hence Failed in Test - Step2");
    }

    @Test(priority = 2)
    public void clickFirstAutoSuggestAndValidateData() {
        String eleTxt = ele.getText();
        ele.click();
    }

    @Test(priority = 3)
    public void verifyStockPrice() {
        int val = homePage.getStockPrice();
        System.out.println(val + " --> is the present stock price");
        assertTrue("Stock price value as (" + val + ") should NOT be greater than 200.", val > 200);
    }

    @Test(priority = 4)
    public void verifyPreviousCloseVolumeData() {
        float val = homePage.getPreviousCloseStockPrice();
        System.out.println(val + " --> is the getPreviousCloseStockPrice ");

        String value = homePage.getVolumePrice();
        System.out.println(value + " --> is the getVolumePrice ");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void loadProperties() {
        try (InputStream input = YahooFinTest.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            //load a properties file from class path, inside static method
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
