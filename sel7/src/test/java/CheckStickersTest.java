import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CheckStickersTest {

    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void checkStickers() {
        driver.get("http://localhost/litecart/en/");
        List<WebElement> products = driver.findElements(By.cssSelector("li.product"));
        int count = 0;
        for (WebElement element : products) {
            if (element.findElements(By.cssSelector("div.sticker")).size() != 1) {
                count++;
            }
        }
        Assert.assertTrue(
                count + " of " + products.size() + " products have wrong number of stickers \n",
                count == 0);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
