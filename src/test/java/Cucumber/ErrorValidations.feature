@tag
Feature: Error Validations
  To validate error scenarios during login and product verification

  Scenario Outline: Verify login error message with invalid credentials
    Given the user is on the landing page
    When the user tries to login with email "<email>" and password "<password>"
    Then the error message "<errorMessage>" should be displayed

    Examples:
      | email                  | password    | errorMessage                    |
      | anshika@gmail.com      | Iamki000    | Incorrect email or password.    |
      | invalid@example.com    | wrongpass   | Incorrect email or password.    |

  Scenario Outline: Verify product is not present in the cart
    Given the user is logged in with email "<email>" and password "<password>"
    When the user adds the product "<productName>" to the cart
    And the user navigates to the cart page
    Then the product "<incorrectProductName>" should not be present in the cart

    Examples:
      | email                         | password         | productName  | incorrectProductName |
      | anveshpothireddy@gmail.com    | Anveshthop@1176  | ZARA COAT 3  | ZARA COAT 33         |


