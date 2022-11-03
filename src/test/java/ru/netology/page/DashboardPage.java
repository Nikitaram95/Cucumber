package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Integer.parseInt;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item div");
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection actionButtons = $$("[data-test-id='action-deposit']");
    private SelenideElement reload = $("[data-test-id='action-reload']");
    private SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public DashboardPage() {
        heading.should(visible);
        reload.should(visible);
    }

    public void reloadBalance() {
        reload.click();
    }

    public int getBalance(int indexCard) {
        reloadBalance();
        String[] card = cards.get(indexCard).toString().split(" ");
        return parseInt(card[6]);
    }

    public TransferPage transferClick(int indexCardTo) {
        actionButtons.get(indexCardTo).click();
        return new TransferPage();
    }
}