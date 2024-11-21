Feature: Checkout flow automation

  Scenario: Add items to cart
    Given I login to the application with "Test" and "Test"
    When I navigate to the Video Games page
    And I sort free shipping and new items from high to low
    And I filter items below price 15000
    And I add the items to the cart
    Then the cart should contain the correct number of items and total price

  Scenario: Proceed to checkout and update details
    Given I login to the application with "Test" and "Test"
    And I have items in the cart
    When I proceed to checkout
    And I change the address to "Test", "1234567891", "Test", "1", "Alexandria", "Ad Daerah Al Gomrokeyah", "Test"
    And I change the payment method
    Then I should successfully remove items from the cart