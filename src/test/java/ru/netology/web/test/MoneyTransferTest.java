package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.junit.jupiter.api.*;
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
        verificationPage.validVerify(verificationCode);
        DashboardPage dashboardPage = new DashboardPage();
        var startingBalance1 = dashboardPage.getCardBalance(0);
        var startingBalance2 = dashboardPage.getCardBalance(1);
        var moneyTransferPage = new MoneyTransfer();
        var amount = moneyTransferPage.getAmount();
        moneyTransferPage.transferFromFirstToSecond(amount);
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        Assertions.assertEquals(startingBalance1 + amount, actualBalance1);
        Assertions.assertEquals(startingBalance2, actualBalance2 + amount);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirst() {
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        DashboardPage dashboardPage = new DashboardPage();
        var startingBalance1 = dashboardPage.getCardBalance(0);
        var startingBalance2 = dashboardPage.getCardBalance(1);
        var moneyTransferPage = new MoneyTransfer();
        var amount = moneyTransferPage.getAmount();
        moneyTransferPage.transferFromSecondToFirst(amount);
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        Assertions.assertEquals(startingBalance1 - amount, actualBalance1);
        Assertions.assertEquals(startingBalance2 + amount, actualBalance2);
    }

    @Test
    void shouldTransferMoneyFromSecondToSecond() {
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        DashboardPage dashboardPage = new DashboardPage();
        var startingBalance1 = dashboardPage.getCardBalance(0);
        var startingBalance2 = dashboardPage.getCardBalance(1);
        var moneyTransferPage = new MoneyTransfer();
        var amount = moneyTransferPage.getAmount();
        moneyTransferPage.transferFromSecondToSecond(amount);
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
        verificationPage.validVerify(verificationCode);
        DashboardPage dashboardPage = new DashboardPage();
        var startingBalance1 = dashboardPage.getCardBalance(0);
        var startingBalance2 = dashboardPage.getCardBalance(1);
        var moneyTransferPage = new MoneyTransfer();
        var amount = moneyTransferPage.getAmount();
        moneyTransferPage.transferFromFirstToFirst(amount);
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        Assertions.assertEquals(startingBalance1, actualBalance1);
        Assertions.assertEquals(startingBalance2, actualBalance2);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondNegative() {
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        DashboardPage dashboardPage = new DashboardPage();
        var startingBalance1 = dashboardPage.getCardBalance(0);
        var startingBalance2 = dashboardPage.getCardBalance(1);
        var moneyTransferPage = new MoneyTransfer();
        var amount = 100000;
        moneyTransferPage.transferFromFirstToSecond(amount);
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        Assertions.assertEquals(startingBalance1 - amount, actualBalance1);
        Assertions.assertEquals(startingBalance2 + amount, actualBalance2);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstNegative() {
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        DashboardPage dashboardPage = new DashboardPage();
        var startingBalance1 = dashboardPage.getCardBalance(0);
        var startingBalance2 = dashboardPage.getCardBalance(1);
        var moneyTransferPage = new MoneyTransfer();
        var amount = 100000;
        moneyTransferPage.transferFromSecondToFirst(amount);
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        Assertions.assertEquals(startingBalance1 + amount, actualBalance1);
        Assertions.assertEquals(startingBalance2 - amount, actualBalance2);
    }
}
