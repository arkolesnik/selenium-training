package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends AbstractPage {

    private By productItems = By.cssSelector("#order_confirmation-wrapper td.item");

    @FindBy(css = "button[name='remove_cart_item']")
    private WebElement removeButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickRemoveButton() {
        removeButton.click();
    }

    public void waitProductTableChanged(WebDriverWait wait, int itemsQty) {
        wait.until(ExpectedConditions.numberOfElementsToBe(productItems, itemsQty));
    }
}
