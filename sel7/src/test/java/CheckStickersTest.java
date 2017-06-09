import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckStickersTest extends DriverFixture {

    @BeforeClass
    public void setWait() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
                count == 0,
                count + " of " + products.size() + " products have wrong number of stickers \n");
    }

}
