import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class MyFirstTest extends DriverFixture {

    @Test
    public void myTest() {
        driver.get("http://software-testing.ru/");
        driver.findElement(By.cssSelector(".tpmenuid4 .tpparenttitle")).click();
        wait.until(ExpectedConditions.urlContains("edu"));
    }
}
