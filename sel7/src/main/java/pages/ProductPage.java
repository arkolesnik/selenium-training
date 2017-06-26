package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends AbstractPage {

    @FindBy(css = "select[name='options[Size]']")
    private List<WebElement> sizeItem;

    @FindBy(css = "button[name='add_cart_product']")
    private WebElement addToCartButton;

    @FindBy(xpath = ".//option[text()='Small']")
    private WebElement smallProductOption;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToCart() {
        if (sizeItem.size() > 0) {
            sizeItem.get(0).click();
            smallProductOption.click();
        }
        addToCartButton.click();
    }
}
