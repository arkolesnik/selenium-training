import com.google.common.base.CharMatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static boolean isFontRed(WebElement element) {
        String[] colors = getRgb(element.getCssValue("color"));
        return colors[1].equalsIgnoreCase("0") && colors[2].equalsIgnoreCase("0");
    }

    public static boolean isFontGrey(WebElement element) {
        String[] colors = getRgb(element.getCssValue("color"));
        List<String> rgb = new ArrayList<String>();
        rgb.add(colors[0]);
        rgb.add(colors[1]);
        rgb.add(colors[2]);
        return rgb.stream().distinct().count() == 1;
    }

    public static boolean isFontBold(WebElement element) {
        String boldValue = element.getCssValue("font-weight");
        return boldValue.equalsIgnoreCase("bold") || Integer.valueOf(boldValue) >= 700;
    }

    public static boolean isFontStrike(WebElement element, String className) {
        try {
            element.findElement(By.cssSelector("s." + className));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static double getFontSize(WebElement element) {
        String size = element.getCssValue("font-size");
        return Double.valueOf(size.replaceAll("[^0-9\\.]+", ""));
    }

    private static String[] getRgb(String color) {
        return CharMatcher.is(',')
                .or(CharMatcher.digit())
                .retainFrom(color)
                .split("\\,");
    }

}
