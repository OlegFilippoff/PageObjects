package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;
import ru.netology.web.page.DashboardPage;

import java.util.Locale;

public class DataHelper {

    static Faker fake = new Faker(new Locale("ru"));

    private DataHelper() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static final CardNumber getCardNumber1() {
        return new CardNumber("5559000000000001");
    }

    public static final CardNumber getCardNumber2() {
        return new CardNumber("5559000000000002");
    }


    public static int getAmount() {
        int amount = fake.number().numberBetween(1, DashboardPage.returnMaxBalance());
        return amount;
    }
    public static int getAmountNegative() throws RuntimeException {
        int amountNegative = fake.number().numberBetween(DataHelper.getAmountNegative(), Integer.MAX_VALUE);
        return amountNegative;
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    @Value
    public static class CardNumber {
        private String cardNumber;
    }

}
