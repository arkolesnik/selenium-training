package tests;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.TestUtils;

import java.util.concurrent.TimeUnit;

public class ProductDetailsTest extends UserFixture {

    @BeforeClass
    public void setWait() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void checkProductDetails() {
        StringBuilder error = new StringBuilder();

        WebElement productIcon = driver.findElement(By.cssSelector("#box-campaigns li:first-child"));

        WebElement regularPriceElementMain = productIcon.findElement(By.className("regular-price"));
        WebElement campaignPriceElementMain = productIcon.findElement(By.className("campaign-price"));

        String productNameMain = productIcon.findElement(By.cssSelector("div.name")).getText();
        String regularPriceMain = regularPriceElementMain.getText();
        String campaignPriceMain = campaignPriceElementMain.getText();

        if (!TestUtils.isFontGrey(regularPriceElementMain)) {
            error.append("Color of regular price on the Main page is not grey; \n");
        }

        if (!TestUtils.isFontStrike(productIcon, "regular-price")) {
            error.append("Regular price on the Main page is not crossed out; \n");
        }

        if (!TestUtils.isFontRed(campaignPriceElementMain)) {
            error.append("Color of campaign price on the Main page is not red; \n");
        }

        if (!TestUtils.isFontBold(campaignPriceElementMain)) {
            error.append("Font for campaign price on the Main page is not bold; \n");
        }

        if (TestUtils.getFontSize(campaignPriceElementMain) <= TestUtils.getFontSize(regularPriceElementMain)) {
            error.append("Campaign price is not bigger then regular one on the Main page; \n");
        }

        productIcon.click();

        WebElement productInfo = driver.findElement(By.cssSelector(".content .information"));
        WebElement regularPriceElementProduct = productInfo.findElement(By.className("regular-price"));
        WebElement campaignPriceElementProduct = productInfo.findElement(By.className("campaign-price"));

        String productNameProduct = driver.findElement(By.cssSelector("#box-product .title")).getText();
        String regularPriceProduct = regularPriceElementProduct.getText();
        String campaignPriceProduct = campaignPriceElementProduct.getText();

        if (!TestUtils.isFontGrey(regularPriceElementProduct)) {
            error.append("Color of regular price on the Product page is not grey; \n");
        }

        if (!TestUtils.isFontStrike(productInfo, "regular-price")) {
            error.append("Regular price on the Product page is not crossed out; \n");
        }

        if (!TestUtils.isFontRed(campaignPriceElementProduct)) {
            error.append("Color of campaign price on the Product page is not red; \n");
        }

        if (!TestUtils.isFontBold(campaignPriceElementProduct)) {
            error.append("Font for campaign price on the Product page is not bold; \n");
        }

        if (TestUtils.getFontSize(campaignPriceElementProduct) <= TestUtils.getFontSize(regularPriceElementProduct)) {
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
