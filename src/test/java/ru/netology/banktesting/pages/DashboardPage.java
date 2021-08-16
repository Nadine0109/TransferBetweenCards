package ru.netology.banktesting.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement transferToFirstCardButton = $("[class=button__content]");
    private SelenideElement transferToSecondCardButton = $$("[class=button__content]").get(1);

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }


    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    public TransferBetweenCardsPage transferToFirst() {
        transferToFirstCardButton.click();
        return new TransferBetweenCardsPage();
    }

    public TransferBetweenCardsPage transferToSecond() {
        transferToSecondCardButton.click();
        return new TransferBetweenCardsPage();
    }
}

