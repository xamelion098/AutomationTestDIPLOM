package  ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class BuyCreditBalans {
    private final SelenideElement paymentByCredit = $(byText("Кредит по данным карты"));

}
