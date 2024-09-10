@tag
Feature: Purchase the order from eCommerce website
  I want to use this template for my feature file

  Background:
  Given I landed on eCommerce Page
  

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <Password>
    When I add product <productName> to cart 
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the ConfirmationPage

    Examples: 
      | name                       | Password             | productName  |
      | anveshpothireddy@gmail.com | Anveshthop@1176      | ZARA COAT 3  |
      
