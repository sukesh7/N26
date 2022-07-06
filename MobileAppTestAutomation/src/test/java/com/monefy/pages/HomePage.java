package com.monefy.pages;

import com.monefy.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends Base {
    @AndroidFindBy(id = "com.monefy.app.lite:id/expense_button_title") private MobileElement btnExpense;
    @AndroidFindBy(id = "com.monefy.app.lite:id/income_button_title") private MobileElement btnIncome;
    @AndroidFindBy(id = "com.monefy.app.lite:id/income_amount_text") private MobileElement lblIncomeAmount;
    @AndroidFindBy(id = "com.monefy.app.lite:id/expense_amount_text") private MobileElement lblExpenseAmount;
    @AndroidFindBy(accessibility = "Search records") private MobileElement btnSearch;


    public InputOperationsPage tapExpense() {
        click(btnExpense);
        return new InputOperationsPage();
    }

    public InputOperationsPage tapIncome() {
        click(btnIncome);
        return new InputOperationsPage();
    }

    public Double retrieveCurrentExpenseBalance() {
        String balance = getAttribute(lblExpenseAmount, "text");
        return Double.parseDouble(balance.substring(1));
    }

    public Double retrieveCurrentIncomeBalance() {
        String balance = getAttribute(lblIncomeAmount, "text");
        return Double.parseDouble(balance.substring(1));
    }

    public SearchPage tapSearchButton() {
        click(btnSearch);
        return new SearchPage();
    }

}
