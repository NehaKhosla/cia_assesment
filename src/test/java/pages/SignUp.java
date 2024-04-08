package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class SignUp extends Baseclass {

    By titleRadioButton=By.xpath("//input[@value='Mr']");
    By nameField=By.xpath("//input[@id='name']");
    By emailField=By.xpath("//input[@id='email']");
    By passwordField=By.xpath("//input[@id='password']");
    By firstNameField=By.xpath("//input[@id='first_name']");
    By lastNameField=By.xpath("//input[@id='last_name']");
    By addressField=By.xpath("//input[@id='address1']");
    By countryDropdown=By.xpath("//select[@id='country']");
    By stateField=By.xpath("//input[@id='state']");
    By cityField=By.xpath("//input[@id='city']");
    By zipField=By.xpath("//input[@id='zipcode']");
    By mobileNumberField=By.xpath("//input[@id='mobile_number']");
    By createAccountButton=By.xpath("//button[text()='Create Account']");
    By accountCreatedMessage=By.xpath("//h2[@data-qa='account-created']/b");
    By continueButton=By.xpath("//a[text()='Continue']");
    String accountCreationMessage="ACCOUNT CREATED!";

    public void signUpForNewUser(String password){
        driver.findElement(titleRadioButton).click();
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys("Akshay");
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(firstNameField).sendKeys("Akshay");
        driver.findElement(lastNameField).sendKeys("Kumar");
        driver.findElement(addressField).sendKeys("A-980 Shastri Nagar");
        Select sel=new Select(driver.findElement((countryDropdown)));
        sel.selectByValue("India");
        driver.findElement(stateField).sendKeys("Delhi");
        driver.findElement(cityField).sendKeys("New Delhi");
        driver.findElement(zipField).sendKeys("110052");
        driver.findElement(mobileNumberField).sendKeys("0000000000");
        driver.findElement(createAccountButton).click();
    }

    public void verifyAccountCreatedMessage(){
        String message=driver.findElement(accountCreatedMessage).getText();
        System.out.println(message);
        Assert.assertEquals("Account is not created",message,accountCreationMessage);
        driver.findElement(continueButton).click();
    }
}
