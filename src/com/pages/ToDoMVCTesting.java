package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.time.Duration;

public class ToDoMVCTesting {
    WebDriver driver;
    HomePage homePage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        wait(3);
        driver.get("https://todomvc.com/examples/react/dist/");
        driver.manage().window().maximize();
        wait(2);
        homePage = new HomePage(driver);
    }

    @Test(priority = 1)
    public void addTodoItems() {
        homePage.addTodoItem("Task 1 - Office work delivery");
        wait(2);
        homePage.addTodoItem("Task 2 - Personnel work");
        wait(2);
        Assert.assertEquals(homePage.getTodoItems().size(), 2, "Todo items count mismatch.");
    }

    @Test(priority = 2)
    public void markTodoAsComplete() {
        homePage.markTodoComplete(0);
        wait(2);
        WebElement completedTask = homePage.getTodoItems().get(0);
        wait(2);
        Assert.assertTrue(completedTask.getAttribute("class").contains("completed"), "Task not marked as complete.");
    }

    @Test(priority = 3)
    public void deleteTodoItem() {
        homePage.deleteTodoItem(0);
        wait(5);
        Assert.assertEquals(homePage.getTodoItems().size(), 1, "Todo item not deleted.");
    }

    @Test(priority = 4)
    public void markTodoAsCompleteSecond() {
        homePage.markTodoComplete(0);
        wait(2);
        WebElement completedTask = homePage.getTodoItems().get(0);
        wait(2);
        Assert.assertTrue(completedTask.getAttribute("class").contains("completed"), "Task not marked as complete.");
    }

    @Test(priority = 5)
    public void deleteTodoItemSecond() {
        homePage.deleteTodoItem(0);
        wait(5);
        Assert.assertEquals(homePage.getTodoItems().size(), 0, "Todo item not deleted.");
    }

    @AfterClass
    public void tearDown() {
        wait(5);
        driver.quit();
    }

    public void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
