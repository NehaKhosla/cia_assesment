package apiMethods;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApiBaseClass {

    private static Response response;
    Map<String, String> headers = new HashMap<>();


    public void sendGetReqest() {
        response = RestAssured.get("https://httpbin.org/get");

        // Verify the status code is 200 (OK)
        response.then().statusCode(200);
    }
    public void verifyGetRequestHeaders(){
        // Verify the response body contains the correct arguments and headers
        response.then().assertThat()
                .body("args", notNullValue()) // Verify args field is not null
                .body("headers.Accept", equalTo("*/*")) // Verify Accept header
                .body("headers.Host", equalTo("httpbin.org")); // Verify Host header

        // Verify the response headers
        response.then().assertThat()
                .header("Content-Type", containsString("application/json")) // Ensure Content-Type header is present and has the correct value
                .and()
                .header("access-control-allow-credentials", equalTo("true")) // Verify access-control-allow-credentials header
                .and()
                .header("access-control-allow-origin", equalTo("*")) // Verify access-control-allow-origin header
                .and()
                .header("content-length", notNullValue()) // Verify content-length header is present
                .and()
                .header("date", notNullValue()) // Verify date header is present
                .and()
                .header("server", containsString("gunicorn")); // Verify server header contains "gunicorn"

    }

    public void sendPostRequest() {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("")
                .post("https://httpbin.org/post");

        // Verify the status code is 200 (OK)
        response.then().statusCode(200);
    }
        public void verifyPostDetails(){

        // Verify response body contains correct data
        response.then().assertThat()
                .body("data", equalTo(""))
                .body("origin", notNullValue())
                .body("url", equalTo("https://httpbin.org/post"));

        // Verify response headers
        response.then().assertThat()
                .contentType(ContentType.JSON)
                .header("Content-Length", notNullValue())
                .header("Server", containsString("gunicorn"));

        // Verify specific headers
        response.then().assertThat().header("Content-Type", equalTo("application/json"))
                .and().header("Access-Control-Allow-Origin", equalTo("*"))
                .and().header("Access-Control-Allow-Credentials", equalTo("true"));

        System.out.println("All the code executed from post ");
    }
    public void sendPutRequest() {
        // Send a PUT request to https://httpbin.org/put with JSON data
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("")
                .put("https://httpbin.org/put");

        // Verify the status code is 200 (OK)
        response.then().statusCode(200);
    }
    public void verifyPutResponseDetails(){

        // Verify response body contains correct data
        response.then().assertThat()
                .body("data", equalTo(""))
                .body("url", equalTo("https://httpbin.org/put"));

        // Verify specific headers
        response.then().assertThat().header("Content-Type", equalTo("application/json"))
                .and().header("Access-Control-Allow-Origin", equalTo("*"))
                .and().header("Access-Control-Allow-Credentials", equalTo("true"));
    }

    public void sendDeleteRequest() {
        // Send a DELETE request to https://httpbin.org/delete
        response = RestAssured.delete("https://httpbin.org/delete");

        // Verify the status code is 200 (OK)
        response.then().statusCode(200);
    }
    public void verifyDeleteResponseDetails(){
        // Verify the response body
        response.then().assertThat()
                .body("url", equalTo("https://httpbin.org/delete"))
                .body("origin", notNullValue())
                .body("headers", notNullValue());

        // Verify response headers
        response.then().assertThat()
                .contentType("application/json")
                .header("Content-Length", notNullValue())
                .header("Server", containsString("gunicorn"));

        // Verify specific headers
        response.then().assertThat().header("Access-Control-Allow-Origin", equalTo("*"))
                .and().header("Access-Control-Allow-Credentials", equalTo("true"));
    }

    public void setCookie() {
        // Set a cookie by sending a request to https://httpbin.org/cookies/set?free=cookie
//        RestAssured.get("https://httpbin.org/cookies/set?free=cookie");
    }
    public void verifyCookieSetting(){
        RestAssured.baseURI = "https://httpbin.org";

        Response setCookieResponse = given()
                .queryParam("free", "cookie")
                .when()
                .get("/cookies/set");

        // Log response body after setting the cookie
        System.out.println("Response body after setting cookie:");
        System.out.println(setCookieResponse.getBody().asString());

        // Send a request to verify that the cookie is set and returned correctly
        Response getCookiesResponse = given()
                .when()
                .get("/cookies");

        // Log response body after trying to retrieve the cookie
        System.out.println("Response body after trying to retrieve cookie:");
        System.out.println(getCookiesResponse.getBody().asString());

        // Retrieve the cookie
        Cookie cookie = getCookiesResponse.getDetailedCookie("free");

        // Verify that the cookie is not null
        assertNotNull("Cookie 'free' is not set", cookie);

        // Verify that the cookie's value is correct
        if (cookie != null) {
            assertNotNull("Cookie value is null", cookie.getValue());
            assertEquals("cookie", cookie.getValue());
        }
    }


    public void setHeaders() {
        // Define headers to be sent in the request
        headers.put("Header1", "Value1");
        headers.put("Header2", "Value2");

        // Send a GET request to https://httpbin.org/headers with custom headers
        response = RestAssured.given()
                .headers(headers)
                .get("https://httpbin.org/headers");
        // Verify the status code is 200 (OK)
        response.then().statusCode(200);
    }

    public void verifyHeaders(){
        // Verify the response body contains the same headers that were sent in the request
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            response.then().assertThat()
                    .body("headers." + entry.getKey(), equalTo(entry.getValue()));
        }
    }

    public void testConcurrentUsers() {
        // Number of concurrent users
        int concurrentUsers = 5;

        // Create a fixed thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(concurrentUsers);

        // Submit tasks for each concurrent user
        for (int i = 0; i < concurrentUsers; i++) {
            executorService.submit(() -> {
                // Send a GET request to the target endpoint
                Response response = RestAssured.get("https://httpbin.org/get");

                // Print the response status code
                System.out.println("Thread: " + Thread.currentThread().getId() + ", Status code: " + response.getStatusCode());
            });
        }

        // Shutdown the executor service after all tasks are completed
        executorService.shutdown();
    }

    public void testApiPerformance_ResponseTime() {
        // Number of concurrent users
        int concurrentUsers = 10;

        // Create a fixed thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(concurrentUsers);

        // Record start time
        long startTime = System.currentTimeMillis();

        // Submit tasks for each concurrent user
        for (int i = 0; i < concurrentUsers; i++) {
            executorService.submit(() -> {
                // Send a GET request to the target API endpoint
                RestAssured.get("https://httpbin.org/get");
            });
        }

        // Shutdown the executor service after all tasks are completed
        executorService.shutdown();

        try {
            // Wait for all tasks to complete or timeout after 30 seconds
            executorService.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Record end time
        long endTime = System.currentTimeMillis();

        // Calculate total elapsed time
        long totalTime = endTime - startTime;

        // Calculate average response time
        long averageResponseTime = totalTime / concurrentUsers;

        // Print average response time
        System.out.println("Average response time: " + averageResponseTime + " milliseconds");
    }

}
