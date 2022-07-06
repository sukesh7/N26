package com.monefy.pages;

import com.monefy.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class InputOperationsPage extends Base {
    @AndroidFindBy(id = "com.monefy.app.lite:id/keyboard_action_button") private MobileElement btnChooseCategory;
    @AndroidFindBy(id = "com.monefy.app.lite:id/buttonKeyboard0") private MobileElement btnKeyboardElement0;
    @AndroidFindBy(id = "com.monefy.app.lite:id/buttonKeyboard1") private MobileElement btnKeyboardElement1;
    @AndroidFindBy(id = "com.monefy.app.lite:id/buttonKeyboard2") private MobileElement btnKeyboardElement2;
    @AndroidFindBy(id = "com.monefy.app.lite:id/buttonKeyboard3") private MobileElement btnKeyboardElement3;
    @AndroidFindBy(id = "com.monefy.app.lite:id/buttonKeyboard4") private MobileElement btnKeyboardElement4;
    @AndroidFindBy(id = "com.monefy.app.lite:id/buttonKeyboard5") private MobileElement btnKeyboardElement5;
    @AndroidFindBy(id = "com.monefy.app.lite:id/buttonKeyboard6") private MobileElement btnKeyboardElement6;
    @AndroidFindBy(id = "com.monefy.app.lite:id/buttonKeyboard7") private MobileElement btnKeyboardElement7;
    @AndroidFindBy(id = "com.monefy.app.lite:id/buttonKeyboard8") private MobileElement btnKeyboardElement8;
    @AndroidFindBy(id = "com.monefy.app.lite:id/buttonKeyboard9") private MobileElement btnKeyboardElement9;
    @AndroidFindBy(id = "com.monefy.app.lite:id/buttonKeyboardClear") private MobileElement btnKeyboardClear;
    @AndroidFindBy(accessibility = "Navigate up") private MobileElement btnNavigateUp;
    @AndroidFindBy(accessibility = "Open navigation") private MobileElement btnOpenNavigation;
    @AndroidFindBy(accessibility = "Delete") private MobileElement btnDelete;


    public InputOperationsPage enterExpenseValue(Integer amount) {
        int[] digits = Integer.toString(amount).chars().map(c -> c-'0').toArray();
        for(int digit : digits)
            clickNumber(digit);
        return this;
    }

    public CategoryPage chooseCategoryAction() {
        click(btnChooseCategory);
        return new CategoryPage();
    }

    public InputOperationsPage deleteNumber() {
        click(btnKeyboardClear);
        click(btnKeyboardClear);
        return this;
    }

    public InputOperationsPage navigateToExpenseListPage() {
        click(btnNavigateUp);
        return this;
    }

    public HomePage navigateToHomePage() {
        click(btnOpenNavigation);
        return new HomePage();
    }

    public InputOperationsPage clickDelete() {
        click(btnDelete);
        return this;
    }

    private void clickNumber(int digit){
        switch (digit){
            case 0:
                click(btnKeyboardElement0);
                break;
            case 1:
                click(btnKeyboardElement1);
                break;
            case 2:
                click(btnKeyboardElement2);
                break;
            case 3:
                click(btnKeyboardElement3);
                break;
            case 4:
                click(btnKeyboardElement4);
                break;
            case 5:
                click(btnKeyboardElement5);
                break;
            case 6:
                click(btnKeyboardElement6);
                break;
            case 7:
                click(btnKeyboardElement7);
                break;
            case 8:
                click(btnKeyboardElement8);
                break;
            case 9:
                click(btnKeyboardElement9);
                break;
        }

    }
}
