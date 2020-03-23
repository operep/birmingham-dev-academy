package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.GardenAndOutdoors;
import com.sampleweb.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageIntoGardenPageTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private HomePage homePage;
    private GardenAndOutdoors gardenAndOutdoorsPage;

    @BeforeMethod
    public void loadHomePageBeforeMethods() throws Exception {
        gardenAndOutdoorsPage = navigator.navigateToPage(driver, homePage.PATH, GardenAndOutdoors.class);
    }

    @Test(groups = "regression")
    public void navigateToThisPage() {
        gardenAndOutdoorsPage.navigateToGardenAndOutdoorsPage();
    }

    @Test (groups = "regression")
    public void setDepartmentDropdownTest() {
        gardenAndOutdoorsPage.setDepartmentDropdown(GardenAndOutdoors.TITLE);
    }
}
