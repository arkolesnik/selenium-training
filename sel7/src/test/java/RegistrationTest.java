import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends DriverFixture {

    @Test
    public void registerAndCheck() {

        String firstName = RandomStringUtils.randomAlphabetic(7);
        String lastName = RandomStringUtils.randomAlphabetic(10);
        String address1 = RandomStringUtils.randomAlphabetic(20);
        String postcode = RandomStringUtils.randomNumeric(5);
        String email = RandomStringUtils.randomAlphanumeric(10) + "@test.com";
        String phone = "+1" + RandomStringUtils.randomNumeric(11);
        String password = RandomStringUtils.randomAlphanumeric(10);

        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.linkText("New customers click here")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("create-account"))));

        driver.findElement(By.cssSelector("[name='firstname']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("[name='lastname']")).sendKeys(lastName);
        driver.findElement(By.cssSelector("[name='address1']")).sendKeys(address1);
        driver.findElement(By.cssSelector("[name='postcode']")).sendKeys(postcode);
        driver.findElement(By.cssSelector("[name='city']")).sendKeys("Los Angeles");
        driver.findElement(By.cssSelector(".selection .select2-selection__rendered")).click();
        driver.findElement(By.xpath(".//li[text()='United States']")).click();
        driver.findElement(By.cssSelector("select[name='zone_code']")).click();
        driver.findElement(By.xpath(".//option[text()='California']")).click();
        driver.findElement(By.cssSelector("[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("[name='phone']")).sendKeys(phone);
        driver.findElement(By.cssSelector("[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("[name='confirmed_password']")).sendKeys(password);
        driver.findElement(By.cssSelector("[name='create_account']")).click();

        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed(), "You was not registered \n");

        driver.findElement(By.linkText("Logout")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("box-account-login"))));

        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Logout"))));
    }
}
