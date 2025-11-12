Feature: Validating POST API's

  @POST_API @Scenario-4 @Regression
  Scenario Outline: Verify if registering new user is created

    Given Register new user using "<Email>" "<Password>" as request body
    Then  The API call got following status code "<StatusCode>"

    Examples:
      | Email           | Password   | StatusCode |
      | abc@gmail.com   | abc@123    | 201        |
      | text@gmail.com  | test@123   | 201        |
      | abc@gmail.com   | abc@123    | 409        |
      | abc%Tail.com    | abc@!123   | 400        |
      |                 |            | 400        |
      | neuro@gmail.com | TestNN@123 | 201        |

  @POST_API @Scenario-5 @Regression
  Scenario Outline: Verify if registered user is able to login

    Given Login Register user using "<Email>" "<Password>" as request body
    Then  The API call got following status code "<StatusCode>"

    Examples:
      | Email          | Password   | StatusCode |
      | abc@gmail.com  | abc@123    | 200        |
      | text@gmail.com | test@123   | 200        |
      | cKAV@gmail.com | acKav@1234 | 400        |
      | abc@gmail.com  |            | 400        |
      |                |            | 400        |

  @POST_API @Scenario-6 @Regression
  Scenario Outline: Creating new order for existing User

#   Login to existing user and generate token
    Given Generate token for new order for existing user using "<Email>" "<Password>" as request body
    When Fetching the orders of existing user using "<productID>"
    Then The API call got following status code "<StatusCode>"

    Examples:
      | Email           | Password   | productID      | StatusCode |
      | neuro@gmail.com | TestNN@123 | 1,2            | 200        |
      | neuro@gmail.com | TestNN@123 | 1              | 200        |
      | abc@gmail.com   | abc@123    | 1,2,2000,30,12 | 200        |
      | neuro@gmail.com | TestNN@123 |                | 200        |