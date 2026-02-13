@tag
Feature: Purchase the order from ecommerce website

Background:
Given I landed on Ecommerce Page 

  @Regression
  Scenario Outline: Positive Test of the Submitting the order
    Given Loggeed in with username <name> and password <password>
    When I add product <productName> to Cart
    And checkOut <productName> and submit the order
    Then "Thankyou for the order." message is displayed on ConfirmationPage

    Examples:
      | name 				|  password 	  | productName		 |
      | ambikau26@gmail.com |  Ambika12345    | ZARA COAT 3		 |
     