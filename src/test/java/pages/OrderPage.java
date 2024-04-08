package pages;

import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class OrderPage extends Baseclass{

    By proceedToCheckout= By.xpath("//a[text()='Proceed To Checkout']");
    By productDetailsLink=By.xpath("//a[@href='/product_details/2']");
    By price=By.xpath("//p[@class='cart_total_price']");
    By quantity=By.xpath("//td[@class='cart_quantity']/button");
    By deleteProduct=By.xpath("//a[@class='cart_quantity_delete']");
    By productsButton = By.xpath("//a[@href='/products']");
    By viewCartLink=By.xpath("//div[@class='modal-body']//a[@href='/view_cart']");

    public void proceedToCheckout(){
        driver.findElement(proceedToCheckout).click();
    }

    public List<String> getOrderDetails() {
        String productText=driver.findElement(productDetailsLink).getText();
        String productQuantity=driver.findElement(quantity).getText();
        String productPrice=driver.findElement(price).getText();
        List<String> listOfOrderDetails = new ArrayList<>();
        listOfOrderDetails.add(productText);
        listOfOrderDetails.add(productQuantity);
        listOfOrderDetails.add(productPrice);

        return listOfOrderDetails;
    }

    public void verifyOrderDetails(List<String> details){
        String productText=driver.findElement(productDetailsLink).getText();
        Assert.assertEquals("Product Details are not same",productText, details.get(0));
        String productQuantity=driver.findElement(quantity).getText();
        Assert.assertEquals("Product quantity is not same",productQuantity, details.get(1));
        String productPrice=driver.findElement(price).getText();
        Assert.assertEquals("Product price is not same",productPrice, details.get(2));
    }

    public void clearCart(){
        driver.findElement(deleteProduct).click();
    }

    public void navigateToProductPage(){
        driver.findElement(productsButton).click();
    }

    public void clickOnViewCart(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(viewCartLink).click();
    }
}
