import com.google.common.collect.Ordering;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckCountriesTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void checkCountriesSorting() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("box-apps-menu")));

        By countryMenuItemLocator = By.xpath("//span[contains(text(), 'Countries')]");
        driver.findElement(countryMenuItemLocator).click();

        List<String> countryNames = new ArrayList<String>();
        StringBuilder error = new StringBuilder();

        List<WebElement> countryRows = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        String rowLocator = "//tr[@class='row'][%s]";

        for (int i = 1; i <= countryRows.size(); i++) {
            WebElement currentRow = driver.findElement(By.xpath(String.format(rowLocator, i)));
            WebElement countryCell = currentRow.findElement(By.cssSelector("a:not([title=Edit])"));
            String countryName = countryCell.getText();
            countryNames.add(countryName);
            if (Integer.valueOf(currentRow.findElement(By.cssSelector("td:nth-of-type(6)")).getText()) > 0) {
                countryCell.click();
                List<String> geoZoneNames = new ArrayList<String>();
                List<WebElement> geoZoneElements =
                        driver.findElements(By.cssSelector("#table-zones tr:not(.header):not(:last-child) td:nth-of-type(3)"));
                for (WebElement geoZoneElement : geoZoneElements) {
                    geoZoneNames.add(geoZoneElement.getText());
                }
                if (!Ordering.natural().isOrdered(geoZoneNames)) {
                    error.append("Zones for " + countryName + " are not sorted by ascending order \n");
                }
                driver.findElement(countryMenuItemLocator).click();
            }
        }

        if (!Ordering.natural().isOrdered(countryNames)) {
            error.append("Countries are not sorted by ascending order \n");
        }

        Assert.assertTrue(error.toString(), error.toString().isEmpty());
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
