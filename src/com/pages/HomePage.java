package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage {
    WebDriver driver;

    // Locators
    private By todoInput = By.className("new-todo");
    private By todoListItems = By.cssSelector(".todo-list li");
    private By deleteButton = By.cssSelector(".destroy");
    private By toggleComplete = By.cssSelector(".toggle");
    private By filterAll = By.linkText("All");
    private By filterActive = By.linkText("Active");
    private By filterCompleted = By.linkText("Completed");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void addTodoItem(String item) {
        driver.findElement(todoInput).sendKeys(item + "\n");
    }

    public List<WebElement> getTodoItems() {
        return driver.findElements(todoListItems);
    }

    public void deleteTodoItem(int index) {
        WebElement item = getTodoItems().get(index);
        item.findElement(deleteButton).click();
    }

    public void markTodoComplete(int index) {
        WebElement item = getTodoItems().get(index);
        item.findElement(toggleComplete).click();
    }

    public void filterByAll() {
        driver.findElement(filterAll).click();
    }

    public void filterByActive() {
        driver.findElement(filterActive).click();
    }

    public void filterByCompleted() {
        driver.findElement(filterCompleted).click();
    }
}
