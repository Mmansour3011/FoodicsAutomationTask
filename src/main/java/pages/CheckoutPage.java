package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.logging.Logger;

public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final Logger logger = Logger.getLogger(CheckoutPage.class.getName());


    public void skipPrime() {
        By skipPrime = By.cssSelector("#prime-interstitial-nothanks-button");
        try {
            Utils.clickOnElement(driver, skipPrime);
            logger.info("Skipped Amazon Prime.");

        } catch (Exception e) {
            logger.warning("Skip Prime button not found or not visible.");
        }
    }

    public void changeAddress(String fullName, String phoneNum, String streetName, String buildingNo, String city, String district, String governorate) throws InterruptedException, AWTException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By oneAddress = By.xpath("//a[@data-pipeline-link-to-page='shipaddressselect']");
        By changeAddress = By.xpath("(//a[@class='a-link-normal expand-panel-button celwidget'])[1]");
        By addAddress = By.cssSelector("a#add-new-address-popover-link");
        By addAddress2 = By.cssSelector("a#add-new-address-desktop-sasp-tango-link");
        By fullNameF = By.cssSelector("input#address-ui-widgets-enterAddressFullName");
        By phoneNumF = By.cssSelector("input#address-ui-widgets-enterAddressPhoneNumber");
        By streetNameF = By.cssSelector("input#address-ui-widgets-enterAddressLine1");
        By buildingNoF = By.cssSelector("input#address-ui-widgets-enter-building-name-or-number");
        By cityF = By.cssSelector("input#address-ui-widgets-enterAddressCity");
        By districtF = By.cssSelector("input#address-ui-widgets-enterAddressDistrictOrCounty");
        By governorateF = By.cssSelector("input#address-ui-widgets-enterAddressStateOrRegion");
        By submit = By.xpath("//*[@id=\"address-ui-widgets-form-submit-button\"]/span/input");
        By submit2 = By.xpath("(//*[@id=\"checkout-primary-continue-button-id\"]/span/input)[2]");
        Robot robot = new Robot();
        try {
            WebElement oneAddressB = wait.until(ExpectedConditions.visibilityOfElementLocated(oneAddress));
            oneAddressB.click();
            logger.info("Navigated to address selection page.");
        } catch (Exception e) {
            logger.warning("One address button not visible.");
        }

        try {

            WebElement changeAddressB = wait.until(ExpectedConditions.visibilityOfElementLocated(changeAddress));
            changeAddressB.click();
            logger.info("Clicked to change address.");
        } catch (Exception e) {
            logger.warning("Change address button not visible.");
        }

        try {
            Utils.clickOnElement(driver, addAddress);
            logger.info("Adding a new address.");
        } catch (Exception e) {
            Utils.clickOnElement(driver, addAddress2);
            logger.info("Adding a new address.");
        }

        Utils.insertData(driver, fullNameF, fullName);
        logger.info("Full name entered.");

        Thread.sleep(1000);
        Utils.insertData(driver, phoneNumF, phoneNum);
        logger.info("Phone number entered.");

        Thread.sleep(1000);
        Utils.insertData(driver, streetNameF, streetName);
        logger.info("Street name entered.");

        Thread.sleep(1000);
        Utils.insertData(driver, buildingNoF, buildingNo);
        logger.info("Building number entered.");


        Thread.sleep(1000);
        Utils.insertData(driver, cityF, city);
        logger.info("City name entered.");
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);


        Thread.sleep(1000);
        Utils.insertData(driver, districtF, district);
        logger.info("District name entered.");
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);

        Thread.sleep(1000);
        try {
            Utils.clickElementWithScrollDown(driver, submit);
        } catch (Exception e) {
            Utils.clickElementWithScrollDown(driver, submit2);
        }
        logger.info("Submitted the address.");
    }


    public void changePaymentMethod() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust timeout as necessary
        try {
            WebElement cash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#pp-NrMLi7-245")));
            WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-csa-c-slot-id=\"checkout-secondary-continue-payselect\"]")));
            cash.click();
            logger.info("Selected cash payment method.");
            wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
            logger.info("Payment method updated.");
        } catch (Exception e) {
            logger.warning("Payment method couldn't be changed.");
        }
    }
}
