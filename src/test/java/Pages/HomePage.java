package Pages;

import config.Values;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static com.codeborne.selenide.Selectors.*;

/**
 * Created by mycola on 25.09.2017.
 */
public class HomePage extends Page{

    @Step("Открыть начальную страницу")
    public void openHomePage() {
        open(Values.host);
    }

    @Step("Заполнить поле \"Номер домашнего телефона\": {0}")
    public void setHomePhoneField(String phone) {
        $("#HOME_PHONE_FID1").shouldBe(visible).setValue(phone).pressTab();
    }

    @Step("Заполнить поле \"Имя\": {0}")
    public void setNameField(String name) {
        $("#NAME_FID1").shouldBe(visible).setValue(name).pressTab();
    }

    @Step("Заполнить поле \"Отчество\": {0}")
    public void setSecondNameField(String name) {
        $("#SECOND_NAME_FID1").shouldBe(visible).setValue(name).pressTab();
    }

    @Step("Заполнить поле \"Фамилия\": {0}")
    public void setSurnameField(String name) {
        $("#SURNAME_FID1").shouldBe(visible).setValue(name).pressTab();
    }

    @Step("Заполнить поле \"Электронный адрес\": {0}")
    public void setEmailField(String email) {
        $("#EMAIL_FID1").shouldBe(visible).setValue(email).pressTab();
    }

    @Step("Заполнить поле \"Номер мобильного телефона\": {0}")
    public void setMobilePhoneField(String phone) {
        $("#MOBILE_FID1").shouldBe(visible).setValue(phone).pressTab();
    }

    @Step("Нажать кнопку отправления данных")
    public void clickSubmitButton() {
        $("#fb_close_FID1").shouldBe(visible).shouldBe(enabled).click();
    }

    @Step("Нажать элемент с динамическим отображением: {0}")
    public void clickDynamicShowElement(String text) {
        $(byText(text)).shouldBe(visible).click();
    }

    @Step("Нажать ссылку \"Личный кабинет\"")
    public void clickPersonalAreaLink() {
        $(byXpath("//a[@target='_blank'][text()='Личном кабинете']")).shouldBe(visible).click();
    }

    @Step("Нажать ссылку \"Центр продаж и обслуживания\"")
    public void clickServiceCentresLink() {
        $(byXpath("//a[text()='Центров продаж и обслуживания']")).shouldBe(visible).click();
    }

    @Step("Проверить появление страницы с успешным подтверждением")
    public void checkSuccessPageAppeared() {
        String success_url = Values.host + "?success_FID1=yes&ID=";
        if (!url().contains(success_url)) {
            Assert.fail("URL does not match: " + success_url + " | " + url());
        }
    }

    @Step("Проверить отображение спрятанного текста: {0}")
    public void checkFoldedTextIsVisible(String text) {
        $(byXpath("//p[contains(text(), '" + text + "')]")).shouldBe(visible);
    }

    @Step("Проверить переход на страницу: {0}")
    public void checkUrl(String url) {
        if (!url().contains(url)) {
            Assert.fail("URL does not match: " + url + " | " + url());
        }
    }

}
