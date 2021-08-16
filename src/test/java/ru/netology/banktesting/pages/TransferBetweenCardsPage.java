package ru.netology.banktesting.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.banktesting.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TransferBetweenCardsPage {
    private SelenideElement sumField = $("[class=input__control]");
    private SelenideElement cardField = $("[type=tel]");
    private SelenideElement transferButton = $("[class=button__text]");
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public TransferBetweenCardsPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage transfer(String sum, DataHelper.CardsInfo cardsInfo, int number) {
        sumField.setValue(sum);
        if (number != 1) {
            cardField.setValue(cardsInfo.getFirst());
        } else {
            cardField.setValue(cardsInfo.getSecond());
        }
        transferButton.click();
        return new DashboardPage();
    }

}
