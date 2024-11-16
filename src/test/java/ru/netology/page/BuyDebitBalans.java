package ru.netology.page;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BuyDebitBalans {
    private final SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement month = $("[placeholder='08']");
    private final SelenideElement years = $("[placeholder='22']");
    private final SelenideElement name = $(byText("Владелец"))
            .parent().$(".input__control");
    private final SelenideElement cvv = $("[placeholder='999']");
    private final SelenideElement сontinue = $$(".button__content")
            .find(Condition.exactText("Продолжить"));
    private final SelenideElement successFormByCard = $$(".notification__content")
            .find(exactText("Операция одобрена Банком."));
    private final SelenideElement incorrectCardNumber = $(By.xpath("//*[@id=\"root\"]/div/div[3]/div[3]"));


    private final SelenideElement emptyCard = $(By.xpath("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[3]"));
    private final SelenideElement emptyMonth = $(By.xpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[3]"));
    private final SelenideElement incorrectMouth = $(By.xpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[3]"));
    private final SelenideElement emptyYear = $(By.xpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[3]"));
    private final SelenideElement incorrectYear = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[3]"));
    private final SelenideElement emptyName = $(By.xpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[3]"));
    private final SelenideElement emptyCvv = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[3]"));

    public void fullApprovedCorrectCardForm(DataHelper.CardInfo cardInfo) {

        cardNumber.setValue(cardInfo.getCardNumber());
        month.setValue(cardInfo.getMonth());
        years.setValue(cardInfo.getYears());
        name.setValue(cardInfo.getName());
        cvv.setValue(cardInfo.getCvv());
        сontinue.click();
    }

    public void successSendCardForm() {
        successFormByCard.shouldBe(Condition.visible, Duration.ofSeconds(50));
    }

    public void randomMaskCard() {
        incorrectCardNumber.shouldBe(Condition.visible, Duration.ofSeconds(50));
    }

    public void errorCardForm() {
        сontinue.click();
        emptyCard.shouldBe(visible);
        emptyMonth.shouldBe(visible);
        emptyYear.shouldBe(visible);
        emptyName.shouldBe(visible);
        emptyCvv.shouldBe(visible);
    }

    public void errorMaskCard() {
        emptyCard.shouldBe(visible);

    }

    public void errorMonth() {
        incorrectMouth.shouldBe(visible);
    }

    public void errorYears() {
        incorrectYear.shouldBe(visible);
    }

    public void errorName() {
        emptyName.shouldBe(visible);
    }

    public void errorCvv() {
        emptyCvv.shouldBe(visible);
    }


}

