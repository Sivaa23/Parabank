package org.parasoft.parabank.Pages;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.parasoft.parabank.PageObject;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;

@Slf4j
public class HomePage extends PageObject {

    public HomePage(WebDriver browser, String URL) {
        super(browser);
        this.browser.navigate().to(URL);
        Reporter.log("Logged into the application successfully");
    }

    public RegisterPage navigateToRegisterPage() {
        Reporter.log("Navigating to the Register page");
        browser.findElement(By.xpath("//a[contains(.,'Register')]")).click();
        return new RegisterPage(browser);
    }

    public void login(String username, String password) {
        this.browser.findElement(By.xpath("//input[contains(@type,'text')]")).sendKeys(username);
        this.browser.findElement(By.xpath("//input[contains(@type,'password')]")).sendKeys(password);
        this.browser.findElement(By.xpath("//input[contains(@type,'submit')]")).click();
        Reporter.log("Login to the application");
    }

    public void verifyLoginScenario() throws IOException {
        if ((this.browser.findElement(By.xpath("//h1[@class='title'][contains(.,'Accounts Overview')]")).isDisplayed())){
            Reporter.log("Login is successful with the provided user credentials");
//            WebElement tableRow = this.browser.findElement(By.cssSelector("#accountTable tbody tr"));
//            WebElement balanceColumn = tableRow.findElement(By.cssSelector("td:nth-child(2)"));
//            Assertions.assertEquals("$10000.00", balanceColumn.getText());
            this.browser.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();

        }else{
            Assertions.assertTrue(this.browser.findElement(By.xpath("//p[contains(.,'The username and password could not be verified.')]")).isDisplayed());
            Reporter.log("Login is unsuccessful and error screenshot captured");
            takeScreenshot("loginError");
        }
    }

    public void takeScreenshot(String fileName) throws IOException {
        try{
            File scrFile = (File)((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
            String currentDir = "./AutomationReports/";
            FileUtils.copyFile(scrFile, new File(currentDir + fileName + System.currentTimeMillis() + ".png"));
        }catch (Exception ex){
            Reporter.log("Screenshot not taken");
        }
    }

    public void closeBrowser(){
        this.browser.close();
    }

}
