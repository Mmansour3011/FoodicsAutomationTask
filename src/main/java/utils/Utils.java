package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;
import java.util.logging.Logger;

public class Utils {
    private static final Logger logger = Logger.getLogger(Utils.class.getName());


    public static void insertData(WebDriver driver, By webElement, String data) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
        element.clear();
        element.sendKeys(data);
        logger.info("Data inserted into element: " + webElement + "is: " + data);

    }

    public static void clickOnElement(WebDriver driver, By webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
        element.click();
        logger.info("Clicked on element: " + webElement);
    }

    public static void clickNthElement(WebDriver driver, By webElement, int order) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(webElement, order - 1));
        WebElement element = driver.findElements(webElement).get(order - 1);
        element.click();
        logger.info("Clicked on the " + order + "th element: " + webElement);
    }

    public static void hoverOnElement(WebDriver driver, By webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(webElement));
        logger.info("Hovered over element: " + webElement);
    }

    public static void clickElementWithScrollDown(WebDriver driver, By webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
            element.click();
            logger.info("Clicked on element with scroll down: " + webElement);
        } catch (NoSuchElementException e) {
            logger.warning("Element not found, scrolling down to locate: " + webElement);
            scrollAndClick(driver, webElement, 500);
        }
    }


    public static void clickElementWithScrollUp(WebDriver driver, By webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
            element.click();
            logger.info("Clicked on element with scroll up: " + webElement);
        } catch (NoSuchElementException e) {
            logger.warning("Element not found, scrolling up to locate: " + webElement);
            scrollAndClick(driver, webElement, -500);
        }
    }

    private static void scrollAndClick(WebDriver driver, By webElement, int scrollAmount) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean elementClicked = false;

        while (!elementClicked) {
            js.executeScript("window.scrollBy(0, " + scrollAmount + ")");
            try {
                WebElement element = driver.findElement(webElement);
                element.click();
                elementClicked = true;
                logger.info("Clicked on element after scrolling: " + webElement);
            } catch (NoSuchElementException ex) {
                logger.warning("Element still not found, continuing to scroll...");
            }
        }
    }


    public static void selectFromDropDownList(WebDriver driver, By webElement, String data) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
        Select select = new Select(element);
        select.selectByVisibleText(data);
        logger.info("Selected dropdown option: " + data);

    }

    public static void selectFromDropDownListByIndex(WebDriver driver, By webElement, int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
        Select select = new Select(element);
        select.selectByIndex(index);
        logger.info("Selected dropdown option by index: " + index);
    }

    public static String generateRandomString() {
        String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(LETTERS.length());
            sb.append(LETTERS.charAt(index));
        }
        String randomString = sb.toString();
        logger.info("Generated random string: " + randomString);
        return randomString;
    }

    public static boolean isSubtotalEqualToTarget(String subtotal, int target) {
        String regex = "\\((\\d+) items\\)";
        if (subtotal.matches(".*" + regex + ".*")) {
            String numberStr = subtotal.replaceAll(".*\\((\\d+) items\\).*", "$1");
            int number = Integer.parseInt(numberStr);
            boolean isEqual = number == target;
            logger.info("Subtotal check: " + number + " == " + target + " -> " + isEqual);
            return isEqual;
        }
        logger.warning("Subtotal does not match the expected pattern.");
        return false;
    }

    public static double roundToTwoDecimalPlaces(double value) {
        double roundedValue = Double.parseDouble(String.format("%.2f", value));
        logger.info("Rounded value to two decimal places: " + roundedValue);
        return roundedValue;
    }
}
