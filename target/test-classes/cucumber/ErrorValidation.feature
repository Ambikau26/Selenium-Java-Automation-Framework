@tag
Feature: Error Validation

Background:
Given I landed on Ecommerce Page 

  @tag2
  Scenario Outline: Title
    Given I landed on Ecommerce Page
    When Loggeed in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | name 			    	|  password   |
      | aaaaambikau26@gmail.com |  2222       |
     