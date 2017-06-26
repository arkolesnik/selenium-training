package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CartPreviewBlock;
import pages.ProductPage;
import pages.MainPage;

public class CartTest extends UserFixture {

    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CartPreviewBlock cartPreviewBlock;

    @BeforeClass
    public void initializePages() {
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        cartPreviewBlock = new CartPreviewBlock(driver);
    }

    @Test
    public void addProductToCart() {
        for (int i = 1; i < 4; i++) {
            mainPage.openProduct(1);
            String previousNumber = cartPreviewBlock.getProductQtyFromCart();
            productPage.addProductToCart();
            Integer expectedNumber = Integer.valueOf(previousNumber) + 1;

            cartPreviewBlock.waitCartCounterChanged(wait, expectedNumber.toString());
            mainPage.openMainPage();
        }
        cartPreviewBlock.openCartPage(wait);

        for (int i = 3; i > 0; i--) {
            cartPage.clickRemoveButton();
            cartPage.waitProductTableChanged(wait, i - 1);
        }
    }

}
