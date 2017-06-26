package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPreviewBlock extends AbstractPage {

    @FindBy(css = "#cart a.content span.quantity")
    private WebElement cartIconQty;

    @FindBy(xpath = ".//a[contains(text(),'Checkout')]")
    private WebElement checkoutLink;

    public CartPreviewBlock(WebDriver driver) {
        super(driver);
    }

    public String getProductQtyFromCart() {
        return cartIconQty.getText();
    }

    public void waitCartCounterChanged(WebDriverWait wait, String expectedQty) {
        wait.until(ExpectedConditions.textToBePresentInElement(cartIconQty, expectedQty));
    }

    public void openCartPage(WebDriverWait wait) {
        checkoutLink.click();
        wait.until(ExpectedConditions.urlContains("checkout"));
    }

}
