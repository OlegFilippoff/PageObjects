package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV2;
import ru.netology.web.page.MoneyTransfer;

import static com.codeborne.selenide.Selenide.*;


public class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
    }

    @AfterEach
    void memoryClear() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    void shouldTransferMoneyFromFirstToSecond() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var startingBalance1 = dashboardPage.getCardBalance(0);
        var startingBalance2 = dashboardPage.getCardBalance(1);
        var amount = DataHelper.getAmount();
        var moneyTransferPage = dashboardPage.popUpSecondCard();
        var cardNumber = DataHelper.getCardNumber1();
        moneyTransferPage.transferMoneyFrom(cardNumber, amount);
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        Assertions.assertEquals(startingBalance1 - amount, actualBalance1);
        Assertions.assertEquals(startingBalance2 + amount, actualBalance2);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirst() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var startingBalance1 = dashboardPage.getCardBalance(0);
        var startingBalance2 = dashboardPage.getCardBalance(1);
        var amount = DataHelper.getAmount();
        var moneyTransferPage = dashboardPage.popUpFirstCard();
        var cardNumber = DataHelper.getCardNumber2();
        moneyTransferPage.transferMoneyFrom(cardNumber, amount);
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        Assertions.assertEquals(startingBalance1 + amount, actualBalance1);
        Assertions.assertEquals(startingBalance2 - amount, actualBalance2);
    }

    @Test
    void shouldTransferMoneyFromSecondToSecond() {
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var startingBalance1 = dashboardPage.getCardBalance(0);
        var startingBalance2 = dashboardPage.getCardBalance(1);
        var amount = DataHelper.getAmount();
        var moneyTransferPage = dashboardPage.popUpSecondCard();
        var cardNumber = DataHelper.getCardNumber2();
        moneyTransferPage.transferMoneyFrom(cardNumber, amount);
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        Assertions.assertEquals(startingBalance1, actualBalance1);
        Assertions.assertEquals(startingBalance2, actualBalance2);
    }

    @Test
    void shouldTransferMoneyFromFirstToFirst() {
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var startingBalance1 = dashboardPage.getCardBalance(0);
        var startingBalance2 = dashboardPage.getCardBalance(1);
        var amount = DataHelper.getAmount();
        var moneyTransferPage = dashboardPage.popUpFirstCard();
        var cardNumber = DataHelper.getCardNumber1();
        moneyTransferPage.transferMoneyFrom(cardNumber, amount);
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        Assertions.assertEquals(startingBalance1, actualBalance1);
        Assertions.assertEquals(startingBalance2, actualBalance2);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondNegative() throws RuntimeException {
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var startingBalance1 = dashboardPage.getCardBalance(0);
        var startingBalance2 = dashboardPage.getCardBalance(1);
        var moneyTransferPage = dashboardPage.popUpSecondCard();
        var cardNumber = DataHelper.getCardNumber1();
        var amount = DataHelper.getAmountNegative();
        moneyTransferPage.transferMoneyNegative(cardNumber, amount);
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        Assertions.assertEquals(startingBalance1, actualBalance1);
        Assertions.assertEquals(startingBalance2, actualBalance2);
        Assertions.assertThrows(RuntimeException.class, () -> moneyTransferPage.transferMoneyNegative(cardNumber, amount), "PopUpSum is more than balance");
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstNegative() throws RuntimeException {
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var startingBalance1 = dashboardPage.getCardBalance(0);
        var startingBalance2 = dashboardPage.getCardBalance(1);
        var moneyTransferPage = dashboardPage.popUpFirstCard();
        var cardNumber = DataHelper.getCardNumber2();
        var amount = DataHelper.getAmountNegative();
        moneyTransferPage.transferMoneyNegative(cardNumber, amount);
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        Assertions.assertEquals(startingBalance1, actualBalance1);
        Assertions.assertEquals(startingBalance2, actualBalance2);
        Assertions.assertThrows(RuntimeException.class, () -> moneyTransferPage.transferMoneyNegative(cardNumber, amount), "PopUpSum is more than balance");
    }
}
