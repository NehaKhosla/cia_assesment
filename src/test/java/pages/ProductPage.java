package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.String.valueOf;

public class ProductPage extends Baseclass {
    By searchProductField=By.xpath("//input[@id='search_product']");
    By searchButton=By.xpath("//button[@id='submit_search']");
    By searchedProductHeader=By.xpath("//h2[text()='Searched Products']");
    By filteredProduct=By.xpath("//h2[text()='BRAND - H&M PRODUCTS']");
    By brandsFilterText=By.xpath("//*[text()='H&M']/..//span[@class='pull-right']");
    By addToCartButton=By.xpath("//div[contains(@class,'productinfo')]/a[@data-product-id='2']//i");
    By addedToCartMessage=By.xpath("//h4[contains(@class,'modal-title')]");
    By viewCartLink=By.xpath("//div[@class='modal-body']//a[@href='/view_cart']");
    By listOfProducts=By.xpath("//div[@class='features_items']//div[@class='single-products']");
    By brandFilter=By.xpath("//a[@href='/brand_products/H&M']");
    By selectProduct=By.xpath("//div[contains(@class,'productinfo')]/a[@data-product-id='6']");

    String addToCartMessageText="Added!";

    public void addProductToCart() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        driver.findElement(searchProductField).click();
        driver.findElement(searchProductField).sendKeys("tshirt");
        driver.findElement(searchButton).click();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(searchedProductHeader));
        driver.findElement(addToCartButton).click();
        Thread.sleep(3000);
        String message=driver.findElement(addedToCartMessage).getText();
        Assert.assertEquals("Product is not added",message,addToCartMessageText);
        driver.findElement(viewCartLink).click();
    }

    public void filterProductList(){
        String quantity=driver.findElement(brandsFilterText).getText();
        driver.findElement(brandFilter).click();
        List<WebElement> list=driver.findElements(listOfProducts);
        int size=list.size();
        String sizeOfProduct=valueOf(size);
        if(quantity.contains(sizeOfProduct)){
            driver.findElement(selectProduct).click();
        }
    }
}
