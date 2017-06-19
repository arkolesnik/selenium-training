import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.Set;

public class CheckTabsTest extends LoginAdminFixture {

    @Test
    public void checkLinksOpenInNewTabs() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        String rowsLocator = "//tr[@class='row']";
        int countriesQty = driver.findElements(By.xpath(rowsLocator)).size();
        int randomCountryNumber = RandomUtils.nextInt(0, countriesQty);
        String rowLocator = rowsLocator + "[%s]";
        driver.findElement(By.xpath(String.format(rowLocator, randomCountryNumber)))
                .findElement(By.cssSelector("a:not([title=Edit])")).click();

        String externalLinksLocator = "(.//i[contains(@class, 'fa-external-link')])";
        String externalLinkLocator = externalLinksLocator + "[%s]";
        int linksQty = driver.findElements(By.xpath(externalLinksLocator)).size();

        for (int i = 1; i < linksQty; i++) {
            int windowsQty = driver.getWindowHandles().size();
            driver.findElement(By.xpath(String.format(externalLinkLocator, i))).click();
            String mainWindow = driver.getWindowHandle();
            Set<String> allWindows = driver.getWindowHandles();
            String secondWindow = "";

            wait.until(ExpectedConditions.numberOfWindowsToBe(windowsQty + 1));
            for (String windowHandle : allWindows) {
                if (!windowHandle.equalsIgnoreCase(mainWindow)) {
                    secondWindow = windowHandle;
                }
            }
            driver.switchTo().window(secondWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }

}
