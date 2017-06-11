import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class CartTest extends DriverFixture {

    @Test
    public void addProductToCart() {
        driver.get("http://localhost/litecart/en/");
        By productSelector = By.xpath(".//div[@id='box-most-popular']//li[1]");
        By actualProductNumberSelector = By.cssSelector("#cart a.content span.quantity");

        for (int i = 1; i < 4; i++) {
            driver.findElement(productSelector).click();

            String previousNumber = driver.findElement(actualProductNumberSelector).getText();
            driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
            Integer expectedNumber = Integer.valueOf(previousNumber) + 1;
            wait.until(ExpectedConditions.textToBe(actualProductNumberSelector, expectedNumber.toString()));
            driver.findElement(By.id("logotype-wrapper")).click();
        }
        driver.findElement(By.xpath(".//a[contains(text(),'Checkout')]")).click();
        wait.until(ExpectedConditions.urlContains("checkout"));

        for (int i = 1; i < 4; i++) {
            driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click();
            wait.until(ExpectedConditions.numberOfElementsToBe(
                    By.cssSelector("#order_confirmation-wrapper td.item"), 3 - i));
        }
    }

}
