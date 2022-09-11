package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import ru.netology.web.data.DataHelper;

import javax.xml.crypto.Data;

import static com.codeborne.selenide.Selenide.$;



@Value
public class MoneyTransfer {
    SelenideElement popUpString = $("[data-test-id=amount] input");
    SelenideElement from = $("[data-test-id=from] [type=tel]");
    SelenideElement transferTo = $("[data-test-id=to]");
    SelenideElement popUpButton = $("[data-test-id=action-transfer]");
    SelenideElement cancelButton = $("[data-test-id=action-cancel]");

    public void transferMoneyFrom(DataHelper.CardNumber cardNumberFrom, int amount) {
        popUpString.doubleClick().val(amount + "");
        from.val(cardNumberFrom.getCardNumber());
        popUpButton.click();
    }

    public void transferMoneyNegative(DataHelper.CardNumber cardNumberFrom, int amount) throws RuntimeException {
        popUpString.doubleClick().val(amount + "");
        from.val(cardNumberFrom.getCardNumber());
        popUpButton.click();
    }

}



