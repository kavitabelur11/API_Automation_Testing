Feature: Validating GET API's

  @Get_API @Scenario-1 @Regression
  Scenario Outline: Get List of All products
    Given Get list of all products
    When user calls the "<endpoint>" API
    Then The API call got following status code "<StatusCode>"

    Examples:
      | endpoint                                                             | StatusCode |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/products | 200        |
      | https://us-central1-nn-api-change.cloudfunctions.net/api/products    | 404        |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/prod12   | 404        |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/Pröd!$   | 404        |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/product  | 404        |

  @Get_API @Scenario-2 @Regression
  Scenario Outline: Identify The Products By Exact Name In QueryParameter
    Given Get details of specific products using "<endpoint>"
    Then The API call got following status code "<StatusCode>"

    Examples:
      | endpoint                                                                                | StatusCode |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/products?name=Laptop        | 200        |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/products?name=Tastatür      | 200        |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/products?name=Mousee        | 400        |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/products?name=legacy device | 400        |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/products?name=LAPTOP        | 400        |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/products?name=Laptop02      | 400        |

  @Get_API @Scenario-3 @Regression
  Scenario Outline: Identify The Products By Exact ID as Path Param
    Given Get details of specific products using "<endpoint>" with unique ID
    Then The API call got following status code "<StatusCode>"

    Examples:
      | endpoint                                                                 | StatusCode |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/products/1   | 200        |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/products/2   | 200        |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/products/3   | 200        |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/products/4   | 200        |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/products/999 | 200        |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/products/10  | 404        |
      | https://us-central1-nn-api-challenge.cloudfunctions.net/api/products/A4! | 404        |

  @Get_API @Scenario-7 @Regression
  Scenario Outline: Return orders of existing user
    Given Generate token for existing user order fetching using "<Email>" "<Password>" as request body
    When Get details of order for existing user
    Then The API call got following status code "<StatusCode>"

    Examples:
      | Email           | Password   | StatusCode |
      | abc@gmail.com   | abc@123    | 200        |
      | neuro@gmail.com | TestNN@123 | 200        |

	

	
	
	
	
	
	

	
	
	