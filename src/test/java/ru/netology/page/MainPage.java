package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private final SelenideElement paymentButton = $$(".button__content")
            .find(exactText("Купить"));
    private final SelenideElement creditButton = $(byText("Купить в кредит"));
    private final SelenideElement paymentByCard = $(byText("Оплата по карте"));
    private final SelenideElement paymentByCredit = $(byText("Кредит по данным карты"));

    public BuyDebitBalans payByCard() {
        paymentButton.click();
        paymentByCard.shouldBe(visible);
        return new BuyDebitBalans();
    }

    public BuyCreditBalans payByCredit() {
        creditButton.click();
        paymentByCredit.shouldBe(visible);
        return new BuyCreditBalans();
    }


}
