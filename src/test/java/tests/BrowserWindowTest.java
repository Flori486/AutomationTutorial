package tests;

import helpMethods.ElementHelper;
import helpMethods.TabWindowHelper;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import sharedData.SharedData;


public class BrowserWindowTest extends SharedData {
    @Test
    public void metodaTest() {

        TabWindowHelper tabWindowHelper= new TabWindowHelper(driver);

        ElementHelper elementHelper= new ElementHelper(driver);


        By alertFrameWindowsMenu = By.xpath("//h5[text()='Alerts, Frame & Windows']");
        elementHelper.clickJSLocator(alertFrameWindowsMenu);

        By browserWindowSubmenu = By.xpath("//span[text()='Browser Windows']");
        elementHelper.clickLocator(browserWindowSubmenu);

        By newTabElement = By.id("tabButton");
        elementHelper.clickLocator(newTabElement);

        System.out.println(driver.getCurrentUrl());

        tabWindowHelper.switchToSpecificTabWindow(1);

        System.out.println(driver.getCurrentUrl());

        tabWindowHelper.closeCurrentTabWindow();

        tabWindowHelper.switchToSpecificTabWindow(0);

        By newWindowElement = By.id("windowButton");
        elementHelper.clickLocator(newWindowElement);

        tabWindowHelper.switchToSpecificTabWindow(1);

        System.out.println(driver.getCurrentUrl());
    }
}