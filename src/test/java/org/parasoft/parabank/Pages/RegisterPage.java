package org.parasoft.parabank.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.parasoft.parabank.PageObject;
import org.testng.Reporter;

public class RegisterPage extends PageObject {

    private static String username;

    public RegisterPage(WebDriver browser) {
        super(browser);
    }

    public void fillTheRegisterForm(String firstName, String lastName, String street, String city, String state,
                                    String zipCode, String phoneNumber, String ssn, String username, String password) {
        RegisterPage.username = username;
        this.browser.findElement(By.id("customer.firstName")).sendKeys(firstName);
        this.browser.findElement(By.id("customer.lastName")).sendKeys(lastName);
        this.browser.findElement(By.id("customer.address.street")).sendKeys(street);
        this.browser.findElement(By.id("customer.address.city")).sendKeys(city);
        this.browser.findElement(By.id("customer.address.state")).sendKeys(state);
        this.browser.findElement(By.id("customer.address.zipCode")).sendKeys(zipCode);
        this.browser.findElement(By.id("customer.phoneNumber")).sendKeys(phoneNumber);
        this.browser.findElement(By.id("customer.ssn")).sendKeys(ssn);
        this.browser.findElement(By.id("customer.username")).sendKeys(RegisterPage.username);
        this.browser.findElement(By.id("customer.password")).sendKeys(password);
        this.browser.findElement(By.id("repeatedPassword")).sendKeys(password);
    }

    public void registerAccount() {
        this.browser.findElement(By.xpath("//input[contains(@value,'Register')]")).click();
    }

    public boolean isRegisteredTitleDisplayed() {
        return this.browser.findElement(By.xpath("//h1[@class='title'][contains(.,'Welcome "
                + RegisterPage.username + "')]")).isDisplayed();
    }

    public boolean isRegisteredMessageDisplayed() {
        return this.browser.findElement(By.xpath("//p[contains(.,'Your account was created" +
                " successfully. You are now logged in.')]")).isDisplayed();
    }

    public boolean isLogOutButtonDisplayed() {
        return this.browser.findElement(By.xpath("//a[contains(.,'Log Out')]")).isDisplayed();
    }

    public boolean isDisplayingErrorMessages() {
        return this.browser.findElement(By.id("customer.firstName.errors")).isDisplayed()
                && this.browser.findElement(By.id("customer.lastName.errors")).isDisplayed()
                && this.browser.findElement(By.id("customer.address.street.errors")).isDisplayed()
                && this.browser.findElement(By.id("customer.address.city.errors")).isDisplayed()
                && this.browser.findElement(By.id("customer.address.state.errors")).isDisplayed()
                && this.browser.findElement(By.id("customer.address.zipCode.errors")).isDisplayed()
                && this.browser.findElement(By.id("customer.ssn.errors")).isDisplayed()
                && this.browser.findElement(By.id("customer.username.errors")).isDisplayed()
                && this.browser.findElement(By.id("customer.password.errors")).isDisplayed()
                && this.browser.findElement(By.id("repeatedPassword.errors")).isDisplayed();
    }

}
