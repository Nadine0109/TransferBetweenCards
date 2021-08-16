package ru.netology.banktesting.test;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.banktesting.data.*;
import ru.netology.banktesting.pages.*;

public class MoneyTransferTest {
    int sumToTransfer = (int) (Math.random() * 9999);


    @BeforeEach
    public void setUpAll() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyToFirstCard() {
        var cardsInfo = DataHelper.getCardsInfo();
        var clientCards = new DashboardPage();
        int firstBalanceBefore = clientCards.getFirstCardBalance();
        int secondBalanceBefore = clientCards.getSecondCardBalance();
        var transferThis = clientCards.transferToFirst();
        transferThis.transfer(Integer.toString(sumToTransfer), cardsInfo, 1);
        assertEquals(firstBalanceBefore + sumToTransfer, clientCards.getFirstCardBalance());
        assertEquals(secondBalanceBefore - sumToTransfer, clientCards.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyToSecondCard() {
        var cardsInfo = DataHelper.getCardsInfo();
        var clientCards = new DashboardPage();
        int firstBalanceBefore = clientCards.getFirstCardBalance();
        int secondBalanceBefore = clientCards.getSecondCardBalance();
        var transferThis = clientCards.transferToSecond();
        transferThis.transfer(Integer.toString(sumToTransfer), cardsInfo, 2);
        assertEquals(firstBalanceBefore - sumToTransfer, clientCards.getFirstCardBalance());
        assertEquals(secondBalanceBefore + sumToTransfer, clientCards.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyToFirstCardFromItself () {
        var cardsInfo = DataHelper.getCardsInfo();
        var clientCards = new DashboardPage();
        int firstBalanceBefore = clientCards.getFirstCardBalance();
        int secondBalanceBefore = clientCards.getSecondCardBalance();
        var transferThis = clientCards.transferToFirst();
        transferThis.transfer(Integer.toString(sumToTransfer), cardsInfo, 2);
        assertEquals(firstBalanceBefore, clientCards.getFirstCardBalance());
        assertEquals(secondBalanceBefore, clientCards.getSecondCardBalance());
    }
    @Test
    void shouldTransferMoneyToSecondCardFromItself() {
        var cardsInfo = DataHelper.getCardsInfo();
        var clientCards = new DashboardPage();
        int firstBalanceBefore = clientCards.getFirstCardBalance();
        int secondBalanceBefore = clientCards.getSecondCardBalance();
        var transferThis = clientCards.transferToSecond();
        transferThis.transfer(Integer.toString(sumToTransfer), cardsInfo, 1);
        assertEquals(firstBalanceBefore, clientCards.getFirstCardBalance());
        assertEquals(secondBalanceBefore, clientCards.getSecondCardBalance());
    }
}
