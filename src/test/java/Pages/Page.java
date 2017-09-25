package Pages;

import java.util.concurrent.TimeUnit;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class Page {

    public static String parentHandle;

    public static void sleep(int time){
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getRandomString() {
        return "test_" + getRandomChar()+getRandomChar()+getRandomChar()+getRandomChar();
    }

    public static char getRandomChar() {
        return (char)((int)(Math.random()*25+97));
    }

    public static void switchBrowserTab() {
        try {
            for (String handle : getWebDriver().getWindowHandles()) {
                if (!handle.equals(parentHandle)) {
                    getWebDriver().switchTo().window(handle);
                }
            }
        } catch (Exception e) {
            System.err.println("Couldn't get to second page");
        }
        sleep(1);
    }

}
