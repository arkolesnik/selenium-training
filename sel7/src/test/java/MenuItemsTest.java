import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;

public class MenuItemsTest extends LoginAdminFixture {

    @Test
    public void clickAllMenuItems() {
        List<WebElement> outerMenuItems = driver.findElements(By.cssSelector("li[id^='app"));
        String outerMenuItemLocator = "li[id^='app']:nth-child(%s)";
        String innerMenuItemLocator = "li[id^='doc']:nth-child(%s)";
        for (int i = 1; i <= outerMenuItems.size(); i++) {
            driver.findElement(By.cssSelector(String.format(outerMenuItemLocator, i))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
            List<WebElement> innerMenuItems = driver.findElements(By.cssSelector("li[id^='doc']"));
            for (int j = 2; j <= innerMenuItems.size(); j++) {
                driver.findElement(By.cssSelector(String.format(innerMenuItemLocator, j))).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
            }
        }
    }

}
