import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckLogsTest extends LoginAdminFixture {

    @Test
    public void checkLogsAreAbsent() {
        String catalogPageUrl = "http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1";
        driver.get(catalogPageUrl);
        String productsSelector =
                "(//td/a[contains(@href, 'app=catalog&doc=edit_product&category_id=1&product_id')][not(contains(@title,'Edit'))])";
        String rowSelector = productsSelector + "[%s]";
        int productQty = driver.findElements(By.xpath(productsSelector)).size();
        StringBuilder error = new StringBuilder();
        for (int i = 1; i < productQty; i++) {
            driver.findElement(By.xpath(String.format(rowSelector, i))).click();
            if (driver.manage().logs().get("browser").getAll().size() > 0) {
                error.append("There are browser log messages when click " + i + " product \n");
            }
            driver.get(catalogPageUrl);
        }
        Assert.assertTrue(error.toString().isEmpty(), error.toString());
    }

}
