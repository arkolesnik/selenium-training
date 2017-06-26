package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class AddNewProductTest extends LoginAdminFixture {

    @Test
    public void addNewProduct() {

        driver.findElement(By.xpath(".//span[text()='Catalog']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content h1")));
        for (WebElement element : driver.findElements(By.cssSelector(".button"))) {
            if (element.getAttribute("innerHTML").contains("Add New Product")) {
                element.click();
                break;
            }
        }

        wait.until(ExpectedConditions.urlContains("edit_product"));

        driver.findElement(By.xpath(".//label[1]/input[@type='radio']")).click();

        String productName = "TestProduct" + RandomStringUtils.randomNumeric(3);
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(productName);
        driver.findElement(By.cssSelector("input[name='code']")).sendKeys(RandomStringUtils.randomNumeric(5));
        driver.findElement(By.cssSelector("input[data-name='Rubber Ducks'")).click();
        driver.findElement(By.cssSelector("select[name='default_category_id']")).click();
        driver.findElement(By.xpath(".//option[text()='Rubber Ducks']")).click();
        driver.findElement(By.xpath(".//td[text()='Unisex']/preceding-sibling::td/input")).click();
        driver.findElement(By.xpath(".//strong[text()='Quantity']/following-sibling::input")).clear();
        driver.findElement(By.xpath(".//strong[text()='Quantity']/following-sibling::input")).sendKeys("1");

        URL resource = AddNewProductTest.class.getResource("reader-rubber-duck.jpg");
        File file = null;
        try {
            file = Paths.get(resource.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String path = file.getAbsolutePath();
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(path);

        driver.findElement(By.xpath(
                ".//strong[text()='Date Valid From']/following-sibling::input")).sendKeys("662017");
        driver.findElement(By.xpath(
                ".//strong[text()='Date Valid To']/following-sibling::input")).sendKeys("772017");

        driver.findElement(By.linkText("Information")).click();
        driver.findElement(By.cssSelector("select[name='manufacturer_id']")).click();
        driver.findElement(By.xpath(".//option[text()='ACME Corp.']")).click();
        driver.findElement(By.cssSelector("input[name='keywords']")).sendKeys(
                RandomStringUtils.randomAlphabetic(5));
        driver.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys(
                RandomStringUtils.randomAlphabetic(10));
        driver.findElement(By.cssSelector("div[class='trumbowyg-editor']")).sendKeys(
                RandomStringUtils.randomAlphabetic(30));
        driver.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys("Smarty Duck");
        driver.findElement(By.cssSelector("input[name='meta_description[en]']")).sendKeys(
                RandomStringUtils.randomAlphabetic(7));

        driver.findElement(By.linkText("Prices")).click();
        driver.findElement(By.cssSelector("input[name='purchase_price']")).clear();
        driver.findElement(By.cssSelector("input[name='purchase_price']")).sendKeys("20");
        driver.findElement(By.cssSelector("select[name='purchase_price_currency_code']")).click();
        driver.findElement(By.xpath(".//option[text()='US Dollars']")).click();
        driver.findElement(By.cssSelector("input[name='prices[USD]']")).sendKeys("20");
        driver.findElement(By.cssSelector("input[name='gross_prices[USD]']")).clear();
        driver.findElement(By.cssSelector("input[name='gross_prices[USD]']")).sendKeys("22");

        driver.findElement(By.cssSelector("button[name='save']")).click();
        wait.until(ExpectedConditions.urlContains("catalog"));

        Assert.assertTrue(driver.findElements(By.linkText(productName)).size() == 1,
                "Your product is not shown in catalog");

    }
}
