package com.monefy.pages;

import com.monefy.Base;
import io.appium.java_client.MobileElement;

public class CategoryPage extends Base {
    private String btnSelectCategory = "//*[@text='%s']";

    public CategoryPage chooseCategory(String category) {
        MobileElement element = (MobileElement) driver.findElementByXPath(String.format(btnSelectCategory, category));
        click(element);
        return this;
    }

}
