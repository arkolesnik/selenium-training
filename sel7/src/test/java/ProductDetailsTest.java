import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class ProductDetailsTest extends DriverFixture {

    @BeforeClass
    public void setWait() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void checkProductDetails() {
        driver.get("http://localhost/litecart/en/");

        StringBuilder error = new StringBuilder();

        WebElement productIcon = driver.findElement(By.cssSelector("#box-campaigns li:first-child"));

        WebElement regularPriceElementMain = productIcon.findElement(By.className("regular-price"));
        WebElement campaignPriceElementMain = productIcon.findElement(By.className("campaign-price"));

        String productNameMain = productIcon.findElement(By.cssSelector("div.name")).getText();
        String regularPriceMain = regularPriceElementMain.getText();
        String campaignPriceMain = campaignPriceElementMain.getText();

        if (!Utils.isFontGrey(regularPriceElementMain)) {
            error.append("Color of regular price on the Main page is not grey; \n");
        }

        if (!Utils.isFontStrike(productIcon, "regular-price")) {
            error.append("Regular price on the Main page is not crossed out; \n");
        }

        if (!Utils.isFontRed(campaignPriceElementMain)) {
            error.append("Color of campaign price on the Main page is not red; \n");
        }

        if (!Utils.isFontBold(campaignPriceElementMain)) {
            error.append("Font for campaign price on the Main page is not bold; \n");
        }

        if (Utils.getFontSize(campaignPriceElementMain) <= Utils.getFontSize(regularPriceElementMain)) {
            error.append("Campaign price is not bigger then regular one on the Main page; \n");
        }

        productIcon.click();

        WebElement productInfo = driver.findElement(By.cssSelector(".content .information"));
        WebElement regularPriceElementProduct = productInfo.findElement(By.className("regular-price"));
        WebElement campaignPriceElementProduct = productInfo.findElement(By.className("campaign-price"));

        String productNameProduct = driver.findElement(By.cssSelector("#box-product .title")).getText();
        String regularPriceProduct = regularPriceElementProduct.getText();
        String campaignPriceProduct = campaignPriceElementProduct.getText();

        if (!Utils.isFontGrey(regularPriceElementProduct)) {
            error.append("Color of regular price on the Product page is not grey; \n");
        }

        if (!Utils.isFontStrike(productInfo, "regular-price")) {
            error.append("Regular price on the Product page is not crossed out; \n");
        }

        if (!Utils.isFontRed(campaignPriceElementProduct)) {
            error.append("Color of campaign price on the Product page is not red; \n");
        }

        if (!Utils.isFontBold(campaignPriceElementProduct)) {
            error.append("Font for campaign price on the Product page is not bold; \n");
        }

        if (Utils.getFontSize(campaignPriceElementProduct) <= Utils.getFontSize(regularPriceElementProduct)) {
            error.append("Campaign price is not bigger then regular one on the Product page; \n");
        }

        if (!productNameMain.equalsIgnoreCase(productNameProduct)) {
            error.append("Product names are not equal; \n");
        }
        if (!regularPriceMain.equalsIgnoreCase(regularPriceProduct)) {
            error.append("Regular prices are not equal; \n");
        }

        if (!campaignPriceMain.equalsIgnoreCase(campaignPriceProduct)) {
            error.append("Campaign prices are not equal; \n");
        }

        Assert.assertTrue(error.toString().isEmpty(), error.toString());
    }

}
