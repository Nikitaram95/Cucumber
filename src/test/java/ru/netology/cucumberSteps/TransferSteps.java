package ru.netology.cucumberSteps;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferSteps {
    LoginPage loginPage;
    VerificationPage verificationPage;
    DashboardPage dashboard;

    @Пусть("пользователь авторизуется с логином {string} и паролем {string}")
    public void auth(String login, String password) {
        open("http://localhost:9999/");
        loginPage = new LoginPage();
        verificationPage = loginPage.validLogin(login, password);
    }

    @И("c проверочным кодом {string}")
    public void setValidCode(String verifyCode) {
        dashboard = verificationPage.verify(verifyCode);
    }

    @Когда("пользователь переводит {string} рублей с карты {string} на свою карту {string}")
    public void successTransfer(String amount, String cardFrom, String indexCardTo) {
        var transferPage = dashboard.transferClick(Integer.valueOf(indexCardTo) - 1);
        transferPage.importTransferData(amount, cardFrom);
    }

    @Тогда("баланс его {string} карты из списка на главной странице должен стать {string} рублей")
    public void matchesBalance(String indexCard, String expectedBalance) {
        dashboard.reloadBalance();
        var sum = expectedBalance.replaceAll(" ","");
        int actualBalance = dashboard.getBalance(Integer.valueOf(indexCard) - 1);
        assertEquals(Integer.valueOf(sum), actualBalance);
    }
}
