package pages;

import org.openqa.selenium.By;

public class LoginPage extends Baseclass {

    By nameField=By.xpath("//input[@placeholder='Name']");
    By signUpEmail=By.xpath("//form[@action='/signup']/input[@placeholder='Email Address']");
    By loginEmail=By.xpath("//form[@action='/login']/input[@placeholder='Email Address']");
    By loginPassword=By.xpath("//input[@placeholder='Password']");
    By signUpButton=By.xpath("//button[text()='Signup']");
    By loginButton=By.xpath("//button[text()='Login']");




    public void createNewUser(String name, String email){

        driver.findElement(nameField).sendKeys(name);
        driver.findElement(signUpEmail).sendKeys(email);
        driver.findElement(signUpButton).click();
    }

    public void loginWithExistingUser(String email, String password){
        driver.findElement(loginEmail).sendKeys(email);
        driver.findElement(loginPassword).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
