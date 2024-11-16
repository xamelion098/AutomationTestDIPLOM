package  ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;


import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.BuyDebitBalans;
import ru.netology.page.MainPage;


import static com.codeborne.selenide.Selenide.open;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.CardInfo.*;


public class SqlTest {

    @BeforeAll
    static void setUpAll() { SelenideLogger.addListener("allure", new AllureSelenide()); }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");
    }

    @Test
   public void shouldBeApprovedWithApprovedCard() {
        val cardInfo = new DataHelper.CardInfo(getAPPROVEDCardNumber(), getValidMonth(), getValidYear(), validName(), getValidCodeCVV());
        val mainPage = new MainPage();
        val BuyDebitBalans = mainPage.payByCard();
        BuyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        BuyDebitBalans.successSendCardForm();
        assertEquals("APPROVED", SQLHelper.getCardPayment());
    }
    @Test
   public void shouldBeDeclinedWithDeclinedCard() {
        val cardInfo = new DataHelper.CardInfo(getDECLINEDCardNumber(), getValidMonth(), getValidYear(), validName(), getValidCodeCVV());
        val mainPage = new MainPage();
        val BuyDebitBalans = mainPage.payByCard();
        BuyDebitBalans.fullApprovedCorrectCardForm(cardInfo);
        BuyDebitBalans.successSendCardForm();
        assertEquals("DECLINED", SQLHelper.getCardPayment());
    }

}