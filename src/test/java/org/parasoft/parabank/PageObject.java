package org.parasoft.parabank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;

public class PageObject {

    protected WebDriver browser;

    public PageObject(WebDriver browser) {
        new ChromeDriverService.Builder()
                .usingDriverExecutable( new File("drivers/chromedriver"))
                .build();

        if (browser == null) {
            this.browser = new ChromeDriver();
        } else {
            this.browser = browser;
        }
    }

    private WebElement findElement(By by) {
        return this.browser.findElement(by);
    }

}
