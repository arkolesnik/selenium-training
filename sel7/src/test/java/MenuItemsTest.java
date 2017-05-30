import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MenuItemsTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void clickAllMenuItems() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("box-apps-menu")));

        List<WebElement> outerMenuItems = driver.findElements(By.cssSelector("li[id^='app']"));
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

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
