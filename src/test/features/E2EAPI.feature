Feature: Send different request and verify response details

  @GetMethod @APITest
  Scenario: Send get request and verify response body and headers
   Given user sends get request
   Then verify correct arguments and headers

  @PostMethod @APITest
  Scenario: Send post request and verify response body and headers
    Given user sends post request
    Then verify headers and body details

  @PutMethod @APITest
  Scenario: Send put request and verify response body and headers
    Given user sends put request
    Then verify data is returned

  @DeleteMethod @APITest
  Scenario: Send delete request and verify request processed
    Given user sends delete request
    Then verify request is processed successfully

  @SetCookie @APITest
  Scenario: Set cookie and verify that it is set
    Given verify cookie is set in the request
#    Then verify cookie is set in the request

  @HeaderVerification @APITest
  Scenario: Set headers and verify that they are set in the response
    Given user sets headers
    Then verify headers are set in response

  @LoadTest @APITest
  Scenario: Test performance of an API on basis if response time
    Given user test API performance on the response time metric

  @SetConcurrentUser @APITest
  Scenario: Set 5 concurrent users to use API
    Given user sets concurrent users


