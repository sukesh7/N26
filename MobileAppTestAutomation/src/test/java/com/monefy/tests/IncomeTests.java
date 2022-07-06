package com.monefy.tests;

import com.monefy.Base;
import com.monefy.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class IncomeTests extends Base {
    HomePage homePage;

    @Test
    public void shouldBeAbleToAddIncomeSuccessfully() {
        homePage = new HomePage();
        Double currentBalance = homePage.retrieveCurrentIncomeBalance();
        homePage.tapIncome()
                .enterExpenseValue(100)
                .chooseCategoryAction()
                .chooseCategory("Salary");

        Double expectedBalance = currentBalance+100;
        Double newBalance = homePage.retrieveCurrentIncomeBalance();
        Assert.assertEquals(newBalance, expectedBalance);
        waitSometime();
    }


    private void waitSometime() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
