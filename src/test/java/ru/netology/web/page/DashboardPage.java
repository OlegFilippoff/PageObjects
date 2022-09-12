package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Value
public class DashboardPage {
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";
    private static SelenideElement heading = $("[data-test-id=dashboard]");
    private static SelenideElement personalAccount = $("[data-test-id=dashboard]");
    private static SelenideElement popUpFirstCard = $$("[data-test-id=action-deposit]").get(0);
    private static SelenideElement popUpSecondCard = $$("[data-test-id=action-deposit]").get(1);
    private static SelenideElement refreshButton = $("data-test-id=action-reload");
    private static ElementsCollection cards = $$(".list__item");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public static int getCardBalance(int index) {
        String text = cards.get(index).text();
        return getBalanceFromText(text);
    }

    public static int getBalanceFromText(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public static MoneyTransfer popUpFirstCard() {
        popUpFirstCard.click();
        return new MoneyTransfer();
    }

    public static MoneyTransfer popUpSecondCard() {
        popUpSecondCard.click();
        return new MoneyTransfer();
    }

    public static int returnMaxBalance() {
        int maxBalance = Integer.MAX_VALUE;
        int minBalance = 0;
        for (int i = 0; i < cards.size(); i++) {
            int balance = getCardBalance(i);
            if (balance < maxBalance) {
                maxBalance = balance;
                if (maxBalance > minBalance) {
                    minBalance = balance;
                }
            } else {
                maxBalance = minBalance;
            }
        }
        return minBalance;
    }
    public static int returnMaxBalanceNegative() {
        int maxBalance = Integer.MAX_VALUE;
        for (int i = 0; i < cards.size(); i++) {
            int balance = getCardBalance(i);
            if (balance > maxBalance) {
                maxBalance = balance;
            }
        }
        return maxBalance;
    }
}



