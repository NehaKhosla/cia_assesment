package pages;

import org.junit.Assert;
import org.openqa.selenium.By;

public class PaymentPage extends Baseclass {

    By cardName=By.xpath("//input[@name='name_on_card']");
    By cardNumber= By.xpath("//input[@name='card_number']");
    By cvcNumber= By.xpath("//input[@name='cvc']");
    By expiryMonth= By.xpath("//input[@name='expiry_month']");
    By expiryYear= By.xpath("//input[@name='expiry_year']");
    By payConfirmOrder=By.xpath("//button[@id='submit']");
    By orderPlacedMessage=By.xpath("//*[@data-qa='order-placed']/b");
    By continueButton=By.xpath("//a[@data-qa='continue-button']");

    String orderPlacedText="ORDER PLACED!";

    public void enterCardDetails(){
        driver.findElement(cardName).sendKeys("Nidhi");
        driver.findElement(cardNumber).sendKeys("000000000000");
        driver.findElement(cvcNumber).sendKeys("000");
        driver.findElement(expiryMonth).sendKeys("12");
        driver.findElement(expiryYear).sendKeys("2030");
        driver.findElement(payConfirmOrder).click();
    }

    public void verifyOrderIsPlaced(){
        String message=driver.findElement(orderPlacedMessage).getText();
        System.out.println(message);
        Assert.assertEquals("Account is not created",message,orderPlacedText);
    }

    public void clickOnContinueButton(){
        driver.findElement(continueButton).click();
    }

}
