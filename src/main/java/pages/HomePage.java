package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;
import java.util.logging.Logger;

public class HomePage {
    private WebDriver driver;
    private static final Logger logger = Logger.getLogger(HomePage.class.getName());
    private By allMenu = By.cssSelector("#nav-hamburger-menu");
    private By seeAllButton = By.xpath("//div[text()='See all']");
    private By videoGamesButton = By.xpath("//div[text()='Video Games']");
    private By allVideoGamesButton = By.linkText("All Video Games");
    private By cartButton = By.cssSelector("#nav-cart");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public VideoGamesPage goToVideoGamesPage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Utils.clickOnElement(driver, allMenu);
        logger.info("Clicked on 'All' menu.");
//        Allure.step("Clicked on 'All' menu.");
//        Thread.sleep(2000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(seeAllButton));
        Utils.clickElementWithScrollDown(driver, seeAllButton);
        logger.info("Clicked on 'See All' button.");
//        Thread.sleep(3000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(videoGamesButton));
        Utils.clickElementWithScrollDown(driver, videoGamesButton);
        logger.info("Clicked on 'Video Games' button.");
//        Thread.sleep(4000);

        wait.until(ExpectedConditions.presenceOfElementLocated(allVideoGamesButton));
        Utils.clickNthElement(driver, allVideoGamesButton, 2);
        logger.info("Clicked on the 2nd 'All Video Games' link.");
//        Thread.sleep(2000);

//        VideoGamesPage videoGamesPage = new VideoGamesPage(driver);
//        return videoGamesPage;
        return new VideoGamesPage(driver);
    }

    public CartPage goToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Utils.clickOnElement(driver, cartButton);
        logger.info("Clicked on the Cart button.");

        wait.until(ExpectedConditions.urlContains("cart"));
        logger.info("Navigated to the Cart page.");
//        Thread.sleep(2000);
//        CartPage cartPage = new CartPage(driver);
//        return cartPage;
        return new CartPage(driver);
    }
}
