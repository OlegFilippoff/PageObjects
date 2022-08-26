package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import lombok.Value;
import ru.netology.web.data.DataHelper;
import java.time.Duration;
import java.util.Locale;
import static com.codeborne.selenide.Selenide.$;


@Value
public class MoneyTransfer {
    SelenideElement h1 = $("h1");
    SelenideElement popUpString = $("[data-test-id=amount] input");
    SelenideElement from = $("[data-test-id=from] [type=tel]");
    SelenideElement transferTo = $("[data-test-id=to]");
    SelenideElement popUpButton = $("[data-test-id=action-transfer]");
    SelenideElement cancelButton = $("[data-test-id=action-cancel]");
    DashboardPage dashboardPage = new DashboardPage();
    public MoneyTransfer() {
        h1.shouldBe(Condition.visible, Duration.ofMillis(15));
    }
    Faker fake = new Faker(new Locale("ru"));
    private int amount = fake.number().numberBetween(1, 1000);
    public void transferFromFirstToSecond(int amount) {
        String firstCard = DataHelper.getcardNumber1().getCardNumber();
        String secondCard = DataHelper.getcardNumber2().getCardNumber();
        dashboardPage.popUpFirstCard();
        popUpString.doubleClick().val(amount + "");
        from.val(secondCard);
        popUpButton.click();
    }

    public void transferFromSecondToFirst(int amount) {
        String firstCard = DataHelper.getcardNumber1().getCardNumber();
        String secondCard = DataHelper.getcardNumber2().getCardNumber();
        dashboardPage.popUpSecondCard();
        popUpString.doubleClick().val(amount + "");
        from.val(firstCard);
        popUpButton.click();
    }
    public void transferFromSecondToSecond(int amount) {
        String firstCard = DataHelper.getcardNumber1().getCardNumber();
        String secondCard = DataHelper.getcardNumber2().getCardNumber();
        dashboardPage.popUpSecondCard();
        popUpString.doubleClick().val(amount + "");
        from.val(secondCard);
        popUpButton.click();
    }
    public void transferFromFirstToFirst(int amount) {
        String firstCard = DataHelper.getcardNumber1().getCardNumber();
        String secondCard = DataHelper.getcardNumber2().getCardNumber();
        dashboardPage.popUpSecondCard();
        popUpString.doubleClick().val(amount + "");
        from.val(secondCard);
        popUpButton.click();
    }

}



