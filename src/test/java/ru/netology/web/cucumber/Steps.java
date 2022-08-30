package ru.netology.web.cucumber;

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


    @Given("login page is opened")
    public void openPage() {
       open("http://localhost:9999");
    }

    @When("the field login is filled with {string} and password {string}")
    public void loginAndPass (String login, String pass) {
        verificationPage = loginPage.validLogin(new DataHelper.AuthInfo(login,pass));
    }

    @And("user inputs a valid code from SMS {string}")
        public void getCode (String code) {
        dashboardPage = verificationPage.validVerify(new DataHelper.VerificationCode(code));
    }

    @Then("the user gets in a personal account")
        public void verifyDashBoard() {
        dashboardPage.getHeading();
    }

    @When ("the user transfers money in amount of 5000 RUB from his card \"5559 0000 0000 0002\" to his first card")
    public void moneyTransfer5000 (int amount) {
        moneyTransfer.transferFromFirstToSecond(amount);
    }

    @Then("The balance of the card is 15000 RUB after popUp")
    public int CardBalance(int index) {
        return dashboardPage.getCardBalance(index);
    }
    @Given("Open Browser")
    public void openBrowser() {
        open("https://mail.ru/");
    }

}
