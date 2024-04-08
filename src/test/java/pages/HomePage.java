package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage extends Baseclass {
    By signUpLoginButton = By.xpath("//a[@href='/login']");
    By homePageButton=By.xpath("//a[@href='/']");
    By cartPageButton=By.xpath("//a[@href='/view_cart']");
    By productsButton = By.xpath("//a[@href='/products']");
    By logOutButton = By.xpath("//a[@href='/logout']");

    By deleteButton = By.xpath("//a[@href='/delete_account']");

    public HomePage() {
        this.driver=driver;
    }

    public void clicksignUpOrLoginButton(){
        driver.findElement(signUpLoginButton).click();
    }

    public void clickOnProductsPage(){
        driver.findElement(productsButton).click();
    }

    public void closePopUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("const elements = document.getElementsByClassName('adsbygoogle adsbygoogle-noablate'); while (elements.length > 0) elements[0].remove()");

    }

    public void handleAlert(){
        Alert alert = driver.switchTo().alert();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    public void clickOnHomePage(){
        driver.findElement(homePageButton).click();
    }

    public void clickOnCartPage(){
        driver.findElement(cartPageButton).click();
    }

    public void clickOnLogOutPage(){
        driver.findElement(logOutButton).click();
    }

    public void clickOnDeletePage(){
        driver.findElement(deleteButton).click();
    }

}
