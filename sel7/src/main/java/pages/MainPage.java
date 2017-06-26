package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends AbstractPage {

    @FindBy(id = "logotype-wrapper")
    private WebElement logotypeItem;

    @FindBy(xpath = ".//div[@id='box-most-popular']//li")
    private List<WebElement> productList;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void openMainPage() {
        logotypeItem.click();
    }

    public void openProduct(int numberOfProduct) {
        productList.get(numberOfProduct - 1).click();
    }

}
