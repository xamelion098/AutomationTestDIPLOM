package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;


public class PaymentGateTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeAll
    public static void shouldCleanBase() {
        SQLHelper.cleanBase();
    }

    @Test
    public void testFullSuccessFormAPPROVEDCardPaymentGate() {
        open("http://localhost:8080/");
        var cardInfo = new DataHelper.CardInfo(getAPPROVEDCardNumber(), getValidMonth(), getValidYear(), validName(), getValidCodeCVV());
        var mainPage = new MainPage();
        var buyDebitBalans = mainPage.payByCard();
        buyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        buyDebitBalans.successSendCardForm();
    }

    @Test
    public void testFullSuccessFormGetDECLINEDCardNumberCardPaymentGate() {
        open("http://localhost:8080/");
        var cardInfo = new DataHelper.CardInfo(getDECLINEDCardNumber(), getValidMonth(), getValidYear(), validName(), getValidCodeCVV());
        var mainPage = new MainPage();
        var buyDebitBalans = mainPage.payByCard();
        buyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        buyDebitBalans.randomMaskCard();
    }

    @Test
    public void SQLApprovedWithApprovedCardTest() {
        val cardInfo = new DataHelper.CardInfo(getAPPROVEDCardNumber(), getValidMonth(), getValidYear(), validName(), getValidCodeCVV());
        val mainPage = new MainPage();
        val BuyDebitBalans = mainPage.payByCard();
        BuyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        BuyDebitBalans.successSendCardForm();
        assertEquals("APPROVED", SQLHelper.getCardPayment());
    }

    @Test
    public void SQLDeclinedWithDeclinedCardTest() {
        val cardInfo = new DataHelper.CardInfo(getDECLINEDCardNumber(), getValidMonth(), getValidYear(), validName(), getValidCodeCVV());
        val mainPage = new MainPage();
        val BuyDebitBalans = mainPage.payByCard();
        BuyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        BuyDebitBalans.successSendCardForm();
        assertEquals("DECLINED", SQLHelper.getCardPayment());
    }


    @DisplayName("Тесты на невалидные значения в полях (все остальные поля запонены валидными данными)")
    @Test
    public void testFullSuccessFormRANDOMMASKCARDPaymentGate() {
        open("http://localhost:8080/");
        var cardInfo = new DataHelper.CardInfo(getRandomInValidMaskCard(), getValidMonth(), getValidYear(), validName(), getValidCodeCVV());
        var mainPage = new MainPage();
        var buyDebitBalans = mainPage.payByCard();
        buyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        buyDebitBalans.randomMaskCard();
    }

    @Test
    void incorrectMaskCardTest() {
        open("http://localhost:8080/");
        var cardInfo = new DataHelper.CardInfo(getInValidShortMaskCard(), getValidMonth(), getValidYear(), validName(), getValidCodeCVV());
        var mainPage = new MainPage();
        var buyDebitBalans = mainPage.payByCard();
        buyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        buyDebitBalans.errorMaskCard();
    }

    @Test
    void incorrectMonthTest() {
        open("http://localhost:8080/");
        var cardInfo = new DataHelper.CardInfo(getAPPROVEDCardNumber(), getInValidMonth(), getValidYear(), validName(), getValidCodeCVV());
        var mainPage = new MainPage();
        var buyDebitBalans = mainPage.payByCard();
        buyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        buyDebitBalans.errorMonth();
    }

    @Test
    void incorrectYeasTest() {
        open("http://localhost:8080/");
        var cardInfo = new DataHelper.CardInfo(getAPPROVEDCardNumber(), getValidMonth(), getInValidYear(), validName(), getValidCodeCVV());
        var mainPage = new MainPage();
        var buyDebitBalans = mainPage.payByCard();
        buyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        buyDebitBalans.errorYears();
    }

    @Test
    void incorrectNameRUENTest() {
        open("http://localhost:8080/");
        var cardInfo = new DataHelper.CardInfo(getAPPROVEDCardNumber(), getValidMonth(), getValidYear(), invalidNameRUEN(), getValidCodeCVV());
        var mainPage = new MainPage();
        var buyDebitBalans = mainPage.payByCard();
        buyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        buyDebitBalans.errorName();
    }

    @Test
    void incorrectLongNameTest() {
        open("http://localhost:8080/");
        var cardInfo = new DataHelper.CardInfo(getAPPROVEDCardNumber(), getValidMonth(), getValidYear(), invalidLongName(), getValidCodeCVV());
        var mainPage = new MainPage();
        var buyDebitBalans = mainPage.payByCard();
        buyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        buyDebitBalans.errorName();
    }

    @Test
    void incorrectNameNumberTest() {
        open("http://localhost:8080/");
        var cardInfo = new DataHelper.CardInfo(getAPPROVEDCardNumber(), getValidMonth(), getValidYear(), invalidNameNumber(), getValidCodeCVV());
        var mainPage = new MainPage();
        var buyDebitBalans = mainPage.payByCard();
        buyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        buyDebitBalans.errorName();
    }

    @Test
    void incorrectCvvCodeTest() {
        open("http://localhost:8080/");
        var cardInfo = new DataHelper.CardInfo(getAPPROVEDCardNumber(), getValidMonth(), getValidYear(), validName(), getInValidCodeCVV());
        var mainPage = new MainPage();
        var buyDebitBalans = mainPage.payByCard();
        buyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        buyDebitBalans.errorCvv();
    }


    @Test
    public void nullInfoAllTest() {
        open("http://localhost:8080/");
        var cardInfo = new DataHelper.CardInfo(null, null, null, null, null);
        var mainPage = new MainPage();
        var buyDebitBalans = mainPage.payByCard();
        buyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        buyDebitBalans.errorCardForm();


    }
    @Test
    public void nullCardTest() {
        open("http://localhost:8080/");
        var cardInfo = new DataHelper.CardInfo(null, getValidMonth(), getValidYear(), validName(), getValidCodeCVV());
        var mainPage = new MainPage();
        var buyDebitBalans = mainPage.payByCard();
        buyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        buyDebitBalans.errorMaskCard();

    }
    @Test
    public void nullMonthTest() {
        open("http://localhost:8080/");
        var cardInfo = new DataHelper.CardInfo(getAPPROVEDCardNumber(), null, getValidYear(), validName(), getValidCodeCVV());
        var mainPage = new MainPage();
        var buyDebitBalans = mainPage.payByCard();
        buyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        buyDebitBalans.emptyMonth();
    }
    @Test
    public void nullYearTest() {
        open("http://localhost:8080/");
        var cardInfo = new DataHelper.CardInfo(getAPPROVEDCardNumber(), getValidMonth(), null, validName(), getValidCodeCVV());
        var mainPage = new MainPage();
        var buyDebitBalans = mainPage.payByCard();
        buyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        buyDebitBalans.emptyYear();

    }
    @Test
    public void nullNameTest() {
        open("http://localhost:8080/");
        var cardInfo = new DataHelper.CardInfo(getAPPROVEDCardNumber(), getValidMonth(), getValidYear(), null, getValidCodeCVV());
        var mainPage = new MainPage();
        var buyDebitBalans = mainPage.payByCard();
        buyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        buyDebitBalans.emptyOwner();

    }
    @Test
    public void nullCVVTest() {
        open("http://localhost:8080/");
        var cardInfo = new DataHelper.CardInfo(getAPPROVEDCardNumber(), getValidMonth(), getValidYear(), validName(), null);
        var mainPage = new MainPage();
        var buyDebitBalans = mainPage.payByCard();
        buyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        buyDebitBalans.emptyCVV();

    }
}





