Feature: Purchase

  Scenario Outline: Customer navigation through product categories
    Given the customer is searching products on the online demo shop
    When the customer clicks on the "<category>"
    Then the customer should see information about the "<product>"

    Examples:
      | category |     product     |
      | Phones | Samsung galaxy s6 |
      | Laptops | Sony vaio i5     |
      | Monitors | Apple monitor 24 |

  Scenario Outline: Customer navigates through the site to make a laptop purchase
    Given the customer is searching products on the online demo shop
    When the customer adds the "Sony vaio i5" to the cart
    Then the pop up confirmation is displayed
    And the "Sony vaio i5" is  added in the cart
    When the customer adds the "Dell i7 8gb" to the cart
    Then the pop up confirmation is displayed
    And the "Dell i7 8gb" is  added in the cart
    When the customer deletes the "<deleteproduct>" from the cart
    Then the "<deleteproduct>" is removed from the cart
    When the customer goes to place the order
    Then the payment form is displayed
    When the customer fills the "<name>" and the "<credit card>"
    And the customer clicks on Purchase button
    Then the purchase is successful
    And the total amount is the expected one
    And the purchase id is generated
    When the customer clicks on the ok button to finish the purchase
    Then the user is redirected to the home page

    Examples:
      |  deleteproduct  |   name |  credit card     |
      |  Sony vaio i5   | Jordi  | 8938234567562345 |



