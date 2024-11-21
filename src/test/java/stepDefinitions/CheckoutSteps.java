package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.*;

import java.awt.*;
import java.util.List;

import static pages.BasePage.driver;

public class CheckoutSteps {
    private LoginPage loginPage;
    private HomePage homePage;
    private VideoGamesPage videoGamesPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    private List<WebElement> targetItems;
    private List<WebElement> cartItems;

    @Before
    public void setUp() {
        BasePage.setUp();
    }

    @After
    public void tearDown() {BasePage.tearDown();}


    @Given("I login to the application with {string} and {string}")
    public void login(String username, String password) throws InterruptedException {
        loginPage = new LoginPage(driver);
        homePage = loginPage.login(username, password);
    }

    @When("I navigate to the Video Games page")
    public void navigateToVideoGamesPage() throws InterruptedException {
        videoGamesPage = homePage.goToVideoGamesPage();
    }

    @When("I sort free shipping and new items from high to low")
    public void i_sort_free_shipping_and_new_items_from_high_to_low() {
        videoGamesPage.filterFreeShippingNewItems();
        videoGamesPage.sortItemsHighToLow();
    }

    @When("I filter items below price {int}")
    public void filterItemsBelowPrice(int price) throws InterruptedException {
        targetItems = videoGamesPage.filterItemsBelowPrice(price);
    }

    @When("I add the items to the cart")
    public void addItemsToCart() throws InterruptedException {
        cartItems = videoGamesPage.addItemsToCart(targetItems);
    }

    @Then("the cart should contain the correct number of items and total price")
    public void verifyCart() throws InterruptedException {
        int expectedCartItemsCount = videoGamesPage.getExpectedcartItemsCount(cartItems);
        double expectedCartTotalCost = videoGamesPage.getExpectedcartItemsTotalCost(cartItems);
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        cartPage = videoGamesPage.goToCart();
        boolean cartVerify=cartPage.verifyCart(expectedCartItemsCount, expectedCartTotalCost);
        Assert.assertEquals(cartVerify,true,"Cart has an error in count or total cost.");
    }

    @Given("I have items in the cart")
    public void haveItemsInTheCart() throws InterruptedException {
        cartPage = homePage.goToCart();
    }

    @When("I proceed to checkout")
    public void proceedToCheckout() {
        checkoutPage = cartPage.proceedToBuy();
        checkoutPage.skipPrime();
    }

    @When("I change the address to {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void changeAddress(String name, String phone, String address1, String address2, String city, String state, String country) throws InterruptedException, AWTException {
        checkoutPage.changeAddress(name, phone, address1, address2, city, state, country);
    }

    @When("I change the payment method")
    public void changePaymentMethod() {
        checkoutPage.changePaymentMethod();
    }

    @Then("I should successfully remove items from the cart")
    public void removeItemsFromCart() throws InterruptedException {
        cartPage.removeItemsFromCart();
        String cartStatus=cartPage.checkCartStatus();
        Assert.assertEquals(cartStatus, "Your Amazon Cart is empty.", "The cart contains items.");
    }
}
