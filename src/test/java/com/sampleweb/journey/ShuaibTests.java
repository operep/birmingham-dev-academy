package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.GardenAndOutdoors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ShuaibTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private GardenAndOutdoors gardenAndOutdoorsPage;

    @BeforeMethod
    public void setUp() throws Exception {
        gardenAndOutdoorsPage = navigator.navigateToPage(driver, GardenAndOutdoors.PATH, GardenAndOutdoors.class);
    }

    @Test(groups = "regression")
    public void gardenProductsNavigationPageTest() {
        assertThat(gardenAndOutdoorsPage.isLoaded(), is(true));
    }

    @Test (groups = "regression")
    public void gardenProductsUrlNavigationPageTest() {
        assertThat(gardenAndOutdoorsPage.isLoaded(), is(true));
    }

    @Test (groups = "regression")
    public void setDepartmentDropdownTest() {
        gardenAndOutdoorsPage.setDepartmentDropdown();
    }

    /*@Test (groups = "regression")
    public void searchTest() {
        gardenAndOutdoorsPage.setSearchCriteria("Audi lights").clickSubmitButton();
    }*/
}
