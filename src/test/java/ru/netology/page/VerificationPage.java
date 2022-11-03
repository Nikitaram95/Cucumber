package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("[data-test-id='action-verify']");

    public VerificationPage() {
        codeField.should(visible);
        verifyButton.should(visible);
    }

    public DashboardPage verify(String verifyCode) {
        codeField.val(verifyCode);
        verifyButton.click();
        return new DashboardPage();
    }
}