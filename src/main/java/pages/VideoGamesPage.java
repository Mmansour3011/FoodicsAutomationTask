package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class VideoGamesPage {
    private WebDriver driver;
    private static final Logger logger = Logger.getLogger(VideoGamesPage.class.getName());


    public VideoGamesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void filterFreeShippingNewItems() {
        By freeShippingFilter = By.xpath("//span[@dir='auto'][text()='Free Shipping']");
        By newFilter = By.xpath("//span[text()='New']");

        logger.info("Applying 'Free Shipping' filter.");
        Utils.clickElementWithScrollDown(driver, freeShippingFilter);
        logger.info("'Free Shipping' filter applied.");

        logger.info("Applying 'New' filter.");
        Utils.clickElementWithScrollDown(driver, newFilter);
        logger.info("'New' filter applied.");
    }

    public void sortItemsHighToLow() {
        By sortButton = By.cssSelector("#s-result-sort-select");
        logger.info("Sorting items from high to low.");
        Utils.selectFromDropDownListByIndex(driver, sortButton, 2);
        logger.info("Items sorted from high to low.");
    }

    public List<WebElement> filterItemsBelowPrice(int maxPrice) throws InterruptedException {
        List<WebElement> targetItems = new ArrayList<>();
        By nextPageButton = By.xpath("(//li[contains(@class, 's-list-item-margin-right-adjustment')])[last()]");

        boolean itemFound = false;

        do {
            logger.info("Fetching items on the current page.");
            List<WebElement> items = driver.findElements(By.xpath("(//div[contains(@class,'puisg-col-4-of-24')]/div[@class='puisg-col-inner'])[position() mod 2 = 0]"));
            logger.info("Number of items found: " + items.size());

            for (WebElement item : items) {
                try {
                    WebElement priceElement = item.findElement(By.xpath(".//div[@data-cy=\"price-recipe\"]//div//div[1]//a//span//span//span[@class=\"a-price-whole\"]"));
                    By priceFractionTag = By.xpath(".//div[@data-cy=\"price-recipe\"]//div//div[1]//a//span//span//span[@class=\"a-price-fraction\"]");

                    String fraction = item.findElement(priceFractionTag).getText();
                    double price = Double.parseDouble(priceElement.getText().replace(",", "") + "." + fraction);

                    if (price < maxPrice) {
                        logger.info("Item found below price: " + price);
                        targetItems.add(item);
                        itemFound = true;
                    }
                } catch (NoSuchElementException e) {
                    logger.warning("Price or fraction missing for an item, skipping.");
                }
            }

            if (!itemFound) {
                logger.info("Number of items below price: " + targetItems.size());
                Utils.clickElementWithScrollDown(driver, nextPageButton);
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.presenceOfElementLocated(nextPageButton));

            }
        } while (!itemFound);

        logger.info("Number of items below price: " + targetItems.size());
        return targetItems;
    }


    public List<WebElement> addItemsToCart(List<WebElement> targetItems) throws InterruptedException {
        List<WebElement> cartItems = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        for (WebElement item : targetItems) {
            By priceFractionTag = By.xpath(".//div[@data-cy=\"price-recipe\"]//div//div[1]//a//span//span//span[@class=\"a-price-fraction\"]");
            WebElement priceElement = item.findElement(By.xpath(".//div[@data-cy=\"price-recipe\"]//div//div[1]//a//span//span//span[@class=\"a-price-whole\"]"));
            String fraction = item.findElement(priceFractionTag).getText();
            double price = Double.parseDouble(priceElement.getText().replace(",", "") + "." + fraction);

            try {
                WebElement button = item.findElement(By.xpath(".//div//div[@class='puis-atcb-container']//button"));
                Thread.sleep(1500);
                if (button.isDisplayed()) {
                    wait.until(ExpectedConditions.elementToBeClickable(button)).click();
                    logger.info("Item added to cart.");
                    cartItems.add(item);
                } else {
                    logger.warning("Add to Cart button not visible for this item.");
                }
            } catch (Exception e) {
                logger.warning("Add to Cart button not found for an item, skipping.");
            }
        }
        logger.info("Total items successfully added to the cart: " + cartItems.size());
        return cartItems;
    }

    public int getExpectedcartItemsCount(List<WebElement> cartItems) {
        logger.info("Expected cart items count: " + cartItems.size());
        return cartItems.size();
    }

    public double getExpectedcartItemsTotalCost(List<WebElement> cartItems) {
        double totalSum = 0;
        for (WebElement item : cartItems) {
            try {
                WebElement priceElement = item.findElement(By.xpath(".//div[@data-cy=\"price-recipe\"]//div//div[1]//a//span//span//span[@class=\"a-price-whole\"]"));
                By priceFractionTag = By.xpath(".//div[@data-cy=\"price-recipe\"]//div//div[1]//a//span//span//span[@class=\"a-price-fraction\"]");

                String fraction = item.findElement(priceFractionTag).getText();
                double price = Double.parseDouble(priceElement.getText().replace(",", "") + "." + fraction);
                totalSum += price;
            } catch (NoSuchElementException e) {
                logger.warning("Price or fraction missing for a cart item, skipping.");
            }
        }
        logger.info("Expected cart items total cost: " + totalSum);
        return totalSum;
    }

    public CartPage goToCart() {
        By goToCart = By.xpath("//a[contains(text(), 'Go to basket')]");
        logger.info("Navigating to cart.");
        Utils.clickOnElement(driver, goToCart);
        return new CartPage(driver);
    }


}
