package org.parasoft.parabank.TestCases;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.parasoft.parabank.Pages.HomePage;
import org.parasoft.parabank.Pages.RegisterPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParabankTest {

    private static String URL_HOME;
    private static String browser;
    private RegisterPage registerPage;
    private static String firstname;
    private static String lastname;
    private static String street;
    private static String city;
    private static String state;
    private static String zipCode;
    private static String phoneNumber;
    private static String ssn;
    private static String username;
    private static String password;

    @BeforeTest
    public static void getData() throws IOException, ParseException {

        JSONParser jsonParser= new JSONParser();
        Object obj = jsonParser.parse(new FileReader("src/test/resources/testDataFiles/CustomerInfo.json"));
        JSONObject jobject = (JSONObject)  obj;
        firstname = (String) jobject.get("customer_firstName");
        lastname = (String) jobject.get("customer_lastName");
        street = (String) jobject.get("customer_address_street");
        city = (String) jobject.get("customer_address_city");
        state = (String) jobject.get("customer_address_state");
        zipCode = (String) jobject.get("customer_address_zipCode");
        phoneNumber = (String) jobject.get("customer_phoneNumber");
        ssn = (String) jobject.get("customer_ssn");
        String currentDateAndTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyHHmmss"));
        username = firstname + currentDateAndTime;
        password = (String) jobject.get("customer_password");
        Object obj1 = jsonParser.parse(new FileReader("src/test/resources/testDataFiles/Login.json"));
        JSONObject jobject1 = (JSONObject)  obj1;
        browser = (String) jobject1.get("browser");
        URL_HOME = (String) jobject1.get("URL");
    }

    @Test
    public void shouldRegisterAccountWithSuccess() throws IOException, ParseException {
        HomePage homePage = new HomePage(null, URL_HOME);
        this.registerPage = homePage.navigateToRegisterPage();
        this.registerPage.fillTheRegisterForm(firstname, lastname, street, city, state, zipCode, phoneNumber, ssn,
                username, password);
        this.registerPage.registerAccount();
        Assertions.assertTrue(this.registerPage.isRegisteredTitleDisplayed());
        Assertions.assertTrue(this.registerPage.isRegisteredMessageDisplayed());
        Assertions.assertTrue(this.registerPage.isLogOutButtonDisplayed());
        homePage.closeBrowser();
    }

    @Test
    public void verifyLogin() throws IOException {
        HomePage homePage = new HomePage(null, URL_HOME);
        homePage.login(username,password);
        homePage.verifyLoginScenario();
        homePage.closeBrowser();
    }

}
