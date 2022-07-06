package com.monefy.tests;

import com.monefy.Base;
import com.monefy.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class ExpenseTests extends Base {

    @Test
    public void shouldBeAbleToAddExpenseSuccessfully() {
        HomePage homePage = new HomePage();
        Double currentBalance = homePage.retrieveCurrentExpenseBalance();
        homePage.tapExpense()
                .enterExpenseValue(56)
                .chooseCategoryAction()
                .chooseCategory("Clothes");

        Double expectedBalance = currentBalance+56;
        Double newBalance = homePage.retrieveCurrentExpenseBalance();
        Assert.assertEquals(newBalance, expectedBalance);
    }

    @Test(dependsOnMethods = {"shouldBeAbleToAddExpenseSuccessfully"})
    public void shouldBeAbleToSearchAndUpdateAnExpense() {
        HomePage homePage = new HomePage();
        homePage.tapSearchButton()
                .enterSearchText("Cloth")
                .tapMainSearchResult()
                .deleteNumber()
                .enterExpenseValue(51)
                .navigateToExpenseListPage()
                .navigateToHomePage();

        Double expectedBalance = Double.valueOf(51);
        Double newBalance = homePage.retrieveCurrentExpenseBalance();
        Assert.assertEquals(newBalance, expectedBalance);
    }

    @Test(dependsOnMethods = {"shouldBeAbleToSearchAndUpdateAnExpense"})
    public void shouldBeAbleToDeleteAnExpense() {
        HomePage homePage = new HomePage();
        homePage.tapSearchButton()
                .enterSearchText("Cloth")
                .tapMainSearchResult()
                .clickDelete()
                .navigateToHomePage();

        Double expectedBalance = Double.valueOf(0.00);
        Double newBalance = homePage.retrieveCurrentExpenseBalance();
        Assert.assertEquals(newBalance, expectedBalance);
    }

}
