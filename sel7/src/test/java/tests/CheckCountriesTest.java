package tests;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CheckCountriesTest extends LoginAdminFixture {

    @Test
    public void checkCountriesSorting() {
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

        Assert.assertTrue(error.toString().isEmpty(), error.toString());
    }
}
