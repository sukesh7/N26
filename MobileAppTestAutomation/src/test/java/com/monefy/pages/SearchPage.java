package com.monefy.pages;

import com.monefy.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SearchPage extends Base {
    @AndroidFindBy(id = "com.monefy.app.lite:id/pts_main") private MobileElement lblClickSearchMainResult;
    @AndroidFindBy(id = "com.monefy.app.lite:id/note_text_view") private MobileElement lblSelectSearchItem;
    @AndroidFindBy(id = "com.monefy.app.lite:id/et_search") private MobileElement txtSearchField;

    public InputOperationsPage tapMainSearchResult() {
        click(lblClickSearchMainResult);
        click(lblSelectSearchItem);
        return new InputOperationsPage();
    }
    public SearchPage enterSearchText(String text) {
        click(txtSearchField);
        sendKeys(txtSearchField, text);
        return this;
    }

}
