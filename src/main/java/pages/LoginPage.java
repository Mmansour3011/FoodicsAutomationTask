package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Utils;

import java.util.logging.Logger;

public class LoginPage {
    private WebDriver driver;
    private static final Logger logger = Logger.getLogger(LoginPage.class.getName());

    private By usernameField = By.cssSelector("#ap_email");
    private By continueButton = By.cssSelector("input#continue");
    private By passwordField = By.cssSelector("#ap_password");
    private By signInButton = By.cssSelector("#signInSubmit");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage login(String username, String password) {
        logger.info("Entering username.");
        Utils.insertData(driver, usernameField, username);

        logger.info("Clicking the 'Continue' button.");
        Utils.clickOnElement(driver, continueButton);

        logger.info("Entering password.");
        Utils.insertData(driver, passwordField, password);

        logger.info("Clicking the 'Sign In' button.");
        Utils.clickOnElement(driver, signInButton);

        logger.info("Login successful, navigating to HomePage.");
        return new HomePage(driver);
    }
}
