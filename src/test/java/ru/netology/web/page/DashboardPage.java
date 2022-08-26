package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Value
public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    private SelenideElement personalAccount = $("[data-test-id=dashboard]");
    private SelenideElement popUpFirstCard = $$("[data-test-id=action-deposit]").get(0);
    private SelenideElement popUpSecondCard = $$("[data-test-id=action-deposit]").get(1);
    private SelenideElement refreshButton = $("data-test-id=action-reload");
    private ElementsCollection cards = $$(".list__item");

    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public int getCardBalance(int index) {
        String text = cards.get(index).text();
        return getBalanceFromText(text);
    }

    public int getBalanceFromText(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void popUpFirstCard() {
        popUpFirstCard.click();
    }

    public void popUpSecondCard() {
        popUpSecondCard.click();
    }
}
