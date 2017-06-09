import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CheckGeoZonesTest extends LoginAdminFixture {

    @Test
    public void checkGeoZonesSorting() {
        By countryMenuItemLocator = By.xpath("//span[contains(text(), 'Geo Zones')]");
        driver.findElement(countryMenuItemLocator).click();

        StringBuilder error = new StringBuilder();
        List<WebElement> zonesRows = driver.findElements(By.xpath("//tr[@class='row']"));
        String rowLocator = "//tr[@class='row'][%s]";

        for (int i = 1; i <= zonesRows.size(); i++) {
            WebElement currentRow = driver.findElement(By.xpath(String.format(rowLocator, i)));
            WebElement countryCell = currentRow.findElement(By.cssSelector("a:not([title=Edit])"));
            String countryName = countryCell.getText();
            countryCell.click();

            List<WebElement> zones = driver.findElements(By.cssSelector("#table-zones select[name$='[zone_code]']"));
            List<String> geoZoneNames = new ArrayList<String>(zones.size());
            for (WebElement zone : zones) {
                geoZoneNames.add(zone.findElement(By.cssSelector("[selected='selected']")).getText());
            }
            if (!Ordering.natural().isOrdered(geoZoneNames)) {
                error.append("Zones for " + countryName + " are not sorted by ascending order \n");
            }
            driver.findElement(countryMenuItemLocator).click();
        }
    }

}
