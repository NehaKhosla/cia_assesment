package pages;

import org.openqa.selenium.By;

public class CheckoutPage extends Baseclass {
    By placeOrderButton= By.xpath("//a[@href='/payment']");

    public void placeOrder(){
        driver.findElement(placeOrderButton).click();
    }
}
