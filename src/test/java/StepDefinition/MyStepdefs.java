package StepDefinition;

import apiMethods.ApiBaseClass;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.*;
import java.util.*;

public class MyStepdefs extends Baseclass {

    HomePage homePage=new HomePage();
    LoginPage loginPage=new LoginPage();
    SignUp signUp=new SignUp();
    ProductPage productPage=new ProductPage();
    OrderPage orderPage=new OrderPage();
    CheckoutPage checkoutPage=new CheckoutPage();
    PaymentPage paymentPage=new PaymentPage();

    ApiBaseClass apiBaseClass=new ApiBaseClass();
    private static String email;
    String password="Password";

    private static List<String> orderDetails=new ArrayList<>();


    @Given("user is on home page")
    public void userIsOnHomePage() {
        navigateToURL();
    }

    @Then("user click on Sign Up button")
    public void userClickOnSignUpButton() {
        homePage.clicksignUpOrLoginButton();
    }

    @And("user enter user data on Sign Up page")
    public void userEnterUserDataOnSignUpPage() {
        signUp.signUpForNewUser(password);
    }

    @And("user enter name and email in New User SignUp")
    public void userEnterNameAndEmailInNewUserSignUp() {
        String name="Akshay";
        email=generateRandomEmail();
        loginPage.createNewUser(name,email);
    }

    @Then("user verify account created")
    public void userVerifyAccountCreated() throws InterruptedException {
        signUp.verifyAccountCreatedMessage();
        homePage.closePopUp();
    }

    @And("user enter username email and password")
    public void userEnterUsernameEmailAndPassword() {
        loginPage.loginWithExistingUser(email,password);
    }

    @Then("user searched product and add to cart")
    public void userSearchedProductAndAddToCart() throws InterruptedException {
        homePage.clickOnProductsPage();
        productPage.addProductToCart();
        orderDetails=orderPage.getOrderDetails();
    }

    @And("user proceed to checkout, complete the payment details and place the order")
    public void userProceedToCheckoutCompleteThePaymentDetailsAndPlaceTheOrder() {
        orderPage.proceedToCheckout();
        checkoutPage.placeOrder();
        paymentPage.enterCardDetails();
    }

    @Then("user verifies product in cart")
    public void userVerifiesProductInCart() {
        homePage.clickOnCartPage();
        orderPage.verifyOrderDetails(orderDetails);
    }

    @Then("verify correct arguments and headers")
    public void verifyCorrectArgumentsAndHeaders() {
        apiBaseClass.verifyGetRequestHeaders();
    }

    @Given("user sends get request")
    public void userSendsGetRequest() {
        apiBaseClass.sendGetReqest();
    }

    @Given("user sends post request")
    public void userSendsPostRequest() {
        apiBaseClass.sendPostRequest();
    }

    @Then("verify headers and body details")
    public void verifyHeadersAndBodyDetails() {
        apiBaseClass.verifyPostDetails();
    }

    @Given("user sends put request")
    public void userSendsPutRequest() {
        apiBaseClass.sendPutRequest();
    }

    @Then("verify data is returned")
    public void verifyDataIsReturned() {
        apiBaseClass.verifyPutResponseDetails();
    }

    @Then("verify request is processed successfully")
    public void verifyRequestIsProcessedSuccessfully() {
        apiBaseClass.verifyDeleteResponseDetails();
    }

    @Given("user sends delete request")
    public void userSendsDeleteRequest() {
        apiBaseClass.sendDeleteRequest();
    }

    @Given("user sets cookie")
    public void userSetsCookie() {
        apiBaseClass.setCookie();
    }

    @Then("verify cookie is set in the request")
    public void verifyCookieIsSetInTheRequest() {
        apiBaseClass.verifyCookieSetting();
    }

    @Then("verify headers are set in response")
    public void verifyHeadersAreSetInResponse() {
        apiBaseClass.verifyHeaders();
    }

    @Given("user sets headers")
    public void userSetsHeaders() {
        apiBaseClass.setHeaders();
    }

    @Given("user sets concurrent users")
    public void userSetsConcurrentUsers() {
        apiBaseClass.testConcurrentUsers();
    }

    @Given("user test API performance on the response time metric")
    public void userTestAPIPerformanceOnTheResponseTimeMetric() {
        apiBaseClass.testApiPerformance_ResponseTime();
    }


    @Then("user logs out of the application")
    public void userLogsOutOfTheApplication() {
        homePage.clickOnHomePage();
        homePage.clickOnLogOutPage();
    }

    @And("user delete the account")
    public void userDeleteTheAccount() {
        paymentPage.clickOnContinueButton();
        homePage.clickOnDeletePage();
    }

    @Then("user clears the cart, go to product page, filters the result and again add to cart")
    public void userClearsTheCartGoToProductPageFiltersTheResultAndAgainAddToCart() throws InterruptedException {
        orderPage.clearCart();
        Thread.sleep(1000);
        orderPage.navigateToProductPage();
        productPage.filterProductList();
        orderPage.clickOnViewCart();
    }

    @After
    public void exitBrowser(){
        teardown();
    }
}
