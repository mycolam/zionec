import Pages.HomePage;
import Pages.Page;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import config.Values;
import listeners.AllureOnFailListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


@Listeners({AllureOnFailListener.class})
@Title("CertifID Test Suite")
public class ZionecTest {

    private HomePage homePg;

    @BeforeClass
    public void start() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.savePageSource = false;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
        //options.addArguments("--start-maximized");
        options.addArguments("--disable-dev-tools");
        WebDriver myWebDriver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(myWebDriver);
        homePg = new HomePage();
        Page.parentHandle = getWebDriver().getWindowHandle();
    }

    @BeforeMethod
    public void prepare() {
        homePg.openHomePage();
    }

    @AfterClass
    public void tearDown() {
        getWebDriver().quit();
    }

    @Stories("Позитивный")
    @Title("Заполнение всех полей")
    @Test(priority = 1)
    public void fillingAllFields() {
        homePg.setHomePhoneField("1234567890");
        homePg.setNameField(Page.getRandomString());
        homePg.setSecondNameField(Page.getRandomString());
        homePg.setSurnameField(Page.getRandomString());
        homePg.setEmailField(Page.getRandomString() + "@test.co");
        homePg.setMobilePhoneField("76666666666");
        homePg.clickSubmitButton();
        homePg.checkSuccessPageAppeared();
    }

    @Stories("Позитивный")
    @Title("Динамичекое отображение")
    @Test(priority = 2)
    public void dynamicShow() {
        homePg.clickDynamicShowElement("Об услуге");
        homePg.checkFoldedTextIsVisible("Теперь вы можете получать Единый счет на адрес");
        homePg.clickDynamicShowElement("Преимущества");
        homePg.checkFoldedTextIsVisible("Электронный счет – это современная альтернатива " +
                "бумажному счету");
    }

    @Stories("Позитивный")
    @Title("Переход по линкам")
    @Test(priority = 3)
    public void redirectLinks() {
        homePg.clickPersonalAreaLink();
        Page.switchBrowserTab();
        homePg.checkUrl("https://login.mgts.ru/amserver/UI/Login");
        getWebDriver().close();
        getWebDriver().switchTo().window(Page.parentHandle);
        homePg.clickServiceCentresLink();
        homePg.checkUrl("/home/service-centres/");
    }

    @Stories("Негативный")
    @Title("Тест обязательных полей")
    @Test(priority = 4)
    public void checkTheRequiredFields() {
        homePg.clickSubmitButton();
        homePg.checkUrl(Values.host);
        homePg.setHomePhoneField("1234567890");
        homePg.clickSubmitButton();
        homePg.checkUrl(Values.host);
        homePg.setNameField(Page.getRandomString());
        homePg.clickSubmitButton();
        homePg.checkUrl(Values.host);
        homePg.setSurnameField(Page.getRandomString());
        homePg.clickSubmitButton();
        homePg.checkUrl(Values.host);
        homePg.setEmailField(Page.getRandomString());
        homePg.clickSubmitButton();
        homePg.checkUrl(Values.host);
        homePg.setEmailField(Page.getRandomString() + "@test");
        homePg.clickSubmitButton();
        homePg.checkUrl(Values.host);
        homePg.setEmailField(Page.getRandomString() + "@test.co");
        homePg.clickSubmitButton();
        homePg.checkUrl(Values.host);
        homePg.setMobilePhoneField("76666666666");
        homePg.clickSubmitButton();
        homePg.checkSuccessPageAppeared();
    }

    @Stories("Негативный")
    @Title("Тест для проверки падения")
    @Test(priority = 5)
    public void checkFail() {
        homePg.setSecondNameField(Page.getRandomString());
        homePg.clickSubmitButton();
        homePg.checkSuccessPageAppeared();
    }
}
