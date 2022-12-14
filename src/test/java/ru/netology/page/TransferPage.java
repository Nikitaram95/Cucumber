package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransferPage {
    private SelenideElement transferSumField = $("[data-test-id=amount] input");
    private SelenideElement transferCardField = $("[data-test-id=from] input");
    private SelenideElement applyButton = $("[data-test-id=action-transfer]");
    private SelenideElement cancelButton = $("[data-test-id=action-cancel]");
    private SelenideElement notification = $("[data-test-id=error-notification]");
    private SelenideElement notificationButton = $("[data-test-id=error-notification] button");

    public TransferPage() {
        transferSumField.should(visible);
        transferCardField.should(visible);
        applyButton.should(visible);
        cancelButton.should(visible);
        notification.should(hidden);
        notificationButton.should(hidden);
    }

    public void cleanForm(){
        $("[data-test-id='amount'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='from'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }

    public void importTransferData(String value, String cardNumber) {
        cleanForm();
        transferSumField.setValue(value);
        transferCardField.setValue(String.valueOf(cardNumber));
        applyButton.click();
    }

    public void checkNotification(){
        notification.should(visible);
        notificationButton.click();
        notification.should(hidden);
    }
}