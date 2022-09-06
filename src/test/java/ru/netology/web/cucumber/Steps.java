package ru.netology.web.cucumber;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV2;
import ru.netology.web.page.MoneyTransfer;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class Steps {
    private static LoginPageV2 loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;
    private static MoneyTransfer moneyTransfer;


    @Given("login page is opened {string}")
    public void openPage(String url) {

        loginPage = open(url, LoginPageV2.class);
    }

    @When("the field login is filled with {string} and password {string}")
    public void loginAndPass(String login, String pass) {
        verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
    }

    @And("user inputs a valid code from SMS {string}")
    public void getCode(String code) {
        dashboardPage = verificationPage.validVerify(new DataHelper.VerificationCode(code));
    }

    @Then("the user gets in a personal account")
    public void verifyDashBoard() {
      dashboardPage.getPersonalAccount().shouldBe(Condition.visible);
      moneyTransfer = new MoneyTransfer();
    }

    @When("the user transfers money in amount of {int} RUB from his card 5559000000000002 to his first card")
    public void moneyTransfer5000(int amount) {
        moneyTransfer.transferFromSecondToFirst(5000);
    }

    @Then("The balance of the card {string} is 15000 RUB after popUp")
    public int CardBalance(String cardNum) {
        int index =0;
        if (cardNum.equals("5559000000000001")) {
            index = 1;
        } else {
            index = 2;
        }
        return dashboardPage.getCardBalance(index);
    }

    @Given("Open Browser")
    public void openBrowser() {
        open("https://mail.ru/");
    }

}
