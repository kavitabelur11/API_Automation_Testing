package neuroNation.apitest.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import neuroNation.apitest.utils.TestDataBuild;
import neuroNation.apitest.utils.Utils;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    Response response1;
    TestDataBuild data = new TestDataBuild();
    String RegisterNewUserURL = "https://us-central1-nn-api-challenge.cloudfunctions.net/api/register";
    String LoginRegisterUserURL = "https://us-central1-nn-api-challenge.cloudfunctions.net/api/login";
    String RegisterUserOdersURL = "https://us-central1-nn-api-challenge.cloudfunctions.net/api/orders";
    String GetOrderForExistingUSer = "https://us-central1-nn-api-challenge.cloudfunctions.net/api/orders";
    static String place_id;
    String token;

    @Given("Get list of all products")
    public void get_List_of_all_Products() {
        try {
            res = given().spec(getRequestSpecification());
        } catch (IOException e) {
            System.out.println("in try catch block");
            throw new RuntimeException(e);
        }
    }

    @When("user calls the {string} API")
    public void user_calls_the_api(String endpoint) {
        response = given()
                .baseUri(endpoint)
                .when()
                .get(endpoint);
        System.out.println("List of Products -->"+response.getBody().asString());
    }

    @Then("The API call got following status code {string}")
    public void the_API_call_got_success_with_status_code(String StatusCode) {
        assertEquals(Integer.parseInt(StatusCode), response.getStatusCode());
    }

    //#########################-----2------##############################

    @Given("Get details of specific products using {string}")
    public void Get_details_of_specific_products(String endpoint) {
        response = given()
                .baseUri(endpoint)
                .when()
                .get(endpoint);
        System.out.println("Detail of Specific Product is as follows -->"+response.getBody().asString());
    }

    //###########################----3-----############################

    @Given("Get details of specific products using {string} with unique ID")
    public void Get_details_of_specific_products_using_ID(String endpoint) {
        response = given()
                .baseUri(endpoint)
                .when()
                .get(endpoint);
        System.out.println("Detail of Specific Product using its ID is -->"+response.getBody().asString());
    }

    //###########################----4-----############################

//    @Given("Register new user using {string} {string} as request body")
//    public void Register_New_User(String email, String password) throws IOException {
//        res = given().spec(postRequestSpecification())
//                .body(data.RegisterUser(email, password));
//        response = (Response) res;
//    }

    @Given("Register new user using {string} {string} as request body")
    public void Register_New_User(String email, String password) throws IOException {
        response = given()
                .baseUri(RegisterNewUserURL)
                .when()
                .body(data.RegisterUser(email, password))
                .post(RegisterNewUserURL);
        System.out.println("Detail of new user created is -->"+response.getBody().asString());
    }


//###########################----5-----############################


    @Given("Login Register user using {string} {string} as request body")
    public void Login_Registered_User(String email, String password) throws IOException {
        response = given()
                .baseUri(LoginRegisterUserURL)
                .when()
                .body(data.RegisterUser(email, password))
                .post(LoginRegisterUserURL);
        System.out.println("Result of user login is -->"+response.getBody().asString());
    }

    //###########################----6-----############################

    @Given ("Generate token for new order for existing user using {string} {string} as request body")
    public void TokenGeneration(String email, String password) throws IOException {
        response = given()
                .baseUri(LoginRegisterUserURL)
                .when()
                .body(data.RegisterUser(email, password))
                .post(LoginRegisterUserURL);

        token = response.jsonPath().getString("token");
        System.out.println("Token generated is --> " + token);
    }

    @When ("Fetching the orders of existing user using {string}")
    public void FetchingOrderDetails(String productIds)
    {
        String[] idsArray = productIds.split(",");
        Map<String, Object> RequestBody = new HashMap<>();
        RequestBody.put("product",idsArray);

        System.out.println("Received IDs: " + Arrays.toString(idsArray));
        response1 = given()
                .baseUri(RegisterUserOdersURL)
                .when()
                .header("Authorization","Bearer "+ token)
                .header("Content-Type","application/json")
                .body(RequestBody)
                .post(RegisterUserOdersURL);
        System.out.println("Orders of existing user is -->"+response1.getBody().asString());
    }

    //###########################----7----############################

    @Given ("Generate token for existing user order fetching using {string} {string} as request body")
    public void TokenGenerationforFetchingOrders(String email, String password) throws IOException {
        response = given()
                .baseUri(LoginRegisterUserURL)
                .when()
                .body(data.RegisterUser(email, password))
                .post(LoginRegisterUserURL);

        token = response.jsonPath().getString("token");
        System.out.println("Token generated is --> " + token);
    }

    @When("Get details of order for existing user")
    public void getDetailsOfOrderForExistingUser() {
        response = given()
                .when()
                .header("Authorization","Bearer "+ token)
                .header("Content-Type","application/json")
                .get(GetOrderForExistingUSer);
        System.out.println("Detail of order for specific user is -->"+response.getBody().asString());

    }


}


