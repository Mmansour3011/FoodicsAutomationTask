package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

public class CartPage {
    private WebDriver driver;
    private static final Logger logger = Logger.getLogger(CartPage.class.getName());


    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    public boolean verifyCart(int expectedCount, double expectedTotal) {
        By cartCountLocator = By.cssSelector("#sc-subtotal-label-activecart");
        By cartTotalLocator = By.cssSelector("#sc-subtotal-amount-activecart");

        boolean countMatches = Utils.isSubtotalEqualToTarget(driver.findElement(cartCountLocator).getText(), expectedCount);
        logger.info("Cart count match is: " + countMatches);
        String totalString = driver.findElement(cartTotalLocator).getText()
                .replaceAll(".*\\((\\d+) items\\).*", "$1")
                .replaceAll("[^0-9.,]", "")
                .replace(",", "");

        double actualTotal = Utils.roundToTwoDecimalPlaces(Double.parseDouble(totalString));
        expectedTotal = Utils.roundToTwoDecimalPlaces(expectedTotal);

       boolean totalMatches = Math.abs(expectedTotal - actualTotal) < 1;

       logger.info("current total is " + actualTotal);
        logger.info("expected total is " + expectedTotal);
        logger.info("Total check is " + totalMatches);

        return countMatches && totalMatches;
    }

    public CheckoutPage proceedToBuy() {
        By proceedToBuy = By.name("proceedToRetailCheckout");
        Utils.clickElementWithScrollDown(driver, proceedToBuy);
        return new CheckoutPage(driver);

    }

    public void removeItemsFromCart() throws InterruptedException {
        driver.get("https://www.amazon.eg/-/en/cart?ref_=ewc_gtc");
        By deleteButtonLocator = By.xpath("//button[@aria-label=\"Decrease quantity by one\"]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10) );

        while (true) {
            List<WebElement> deleteButtons  = driver.findElements(deleteButtonLocator);

            if (deleteButtons .isEmpty()) {
                logger.info("No more items in the cart.");
                break;
            }

            logger.info("Number of items remaining in cart: " + deleteButtons .size());
            deleteButtons .get(0).click();
            Thread.sleep(2000);
            driver.navigate().refresh();
        }
    }

    public String checkCartStatus(){
        By cartStatusLocator = By.cssSelector("div.a-row h2.a-size-extra-large");
        String cartStatus = driver.findElement(cartStatusLocator).getText();
        return cartStatus;
    }
}
