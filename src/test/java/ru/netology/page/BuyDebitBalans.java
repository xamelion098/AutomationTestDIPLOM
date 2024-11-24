package ru.netology.page;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BuyDebitBalans {
    private final SelenideElement paymentByCard = $(byText("Оплата по карте"));
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
    private final SelenideElement incorrectCardNumber = $$(".notification__content")
            .find(exactText("Ошибка! Банк отказал в проведении операции."));


    private final SelenideElement emptyCard = $$(".input__inner").findBy(text("Неверный формат"));
    private final SelenideElement emptyMonth = $$(".input__inner").findBy(text("Неверный формат"));
    private final SelenideElement incorrectMouth = $$(".input__sub").findBy(text("Неверно указан срок действия карты"));
    private final SelenideElement emptyYear = $$(".input__inner").findBy(text("Неверный формат"));
    private final SelenideElement incorrectYear = $$(".input__sub").findBy(text("Истёк срок действия карты"));
    private final SelenideElement emptyName = $$(".input__sub").findBy(text("Поле обязательно для заполнения"));
    private final SelenideElement emptyCvv = $$(".input__inner").findBy(text("Неверный формат"));

    public void fullApprovedCorrectCardForm(DataHelper.CardInfo cardInfo) {
        paymentByCard.click();
        cardNumber.setValue(cardInfo.getCardNumber());
        month.setValue(cardInfo.getMonth());
        years.setValue(cardInfo.getYears());
        name.setValue(cardInfo.getName());
        cvv.setValue(cardInfo.getCvv());
        сontinue.click();
    }

    public void successSendCardForm() {

        successFormByCard.shouldBe(visible, Duration.ofSeconds(50))
                .should(exactText("Операция одобрена Банком."));
    }

    public void randomMaskCard() {

        incorrectCardNumber.shouldBe(visible, Duration.ofSeconds(50))
                .should(exactText("Ошибка! Банк отказал в проведении операции."));
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
        emptyCard.shouldBe(visible)
                .should(text("Неверный формат"));

    }

    public void errorMonth() {
        incorrectMouth.shouldBe(visible)
                .should(exactText("Неверно указан срок действия карты"));
    }

    public void errorYears() {
        incorrectYear.shouldBe(visible)
                .should(exactText("Истёк срок действия карты"));
    }

    public void errorName() {

        emptyName.shouldBe(visible)
                .should(text("Поле обязательно для заполнения"));
    }

    public void errorCvv() {
        emptyCvv.shouldBe(visible)
                .should(text("Неверный формат"));
    }


}

