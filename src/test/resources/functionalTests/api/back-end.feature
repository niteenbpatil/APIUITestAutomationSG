@back-end
Feature: Backend Tests

  Scenario: Api POST login successfully
    Given User perform POST operation for "/login" with body
      | email              | password   |
      | eve.holt@reqres.in | cityslicka |

    Then Status code should be 200 and token: "QpwL5tke4Pnpja7X4"

  Scenario: Api GET list of users
    Given User perform GET method for "/users"
    Then Should be 6 items per page and first_name "George"

  Scenario: Verify DELETE operation user after POST
    Given Perform POST operation for "/users" with body
      | name  | job                |
      | Nitin | Developer In Tests |
    And Perform DELETE operation for "/users/2"
    Then Should be response status code 204

    #Scenario Outline: Creating users
      #Given I perform User POST method with "<name>" name and "<jobTitle>" job for "/users"
      #Then Should be response status code 201
      #Examples:
        #| name          | jobTitle                 |
      #| Nitin patil   | Test Automation Engineer |
      #| Madhuri Patil | Developer |