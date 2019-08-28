package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.GardenAndOutdoors;
import com.sampleweb.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ShuaibTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private HomePage homePage;
    private GardenAndOutdoors gardenAndOutdoorsPage;

    @BeforeMethod
    public void setUp() throws Exception {
        gardenAndOutdoorsPage = navigator.navigateToPage(driver, GardenAndOutdoors.PATH, GardenAndOutdoors.class);
    }

    /*@BeforeMethod
    public void setUp() throws Exception {
        gardenAndOutdoorsPage = navigator.navigateToPage(driver, homePage.PATH, GardenAndOutdoors.class);
    }*/

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
        gardenAndOutdoorsPage.setDepartmentDropdown(gardenAndOutdoorsPage.title);
        delayDriver();
    }

    @Test (groups = "regression")
    public void navigateToThisPage() {
        gardenAndOutdoorsPage.navigateHere();
        delayDriver();
    }

    @Test (groups = "regression")
    public void checkTitleCorrect() throws Exception {
        assertThat(gardenAndOutdoorsPage.isTitleCorrect(), is(true));
        delayDriver();
    }

    @Test (groups = "regression")
    public void checkGlobalStoreMethodTest() {
        assertThat(gardenAndOutdoorsPage.isGlobalStore(), is(true));
        delayDriver();
    }

    /*@Test (groups = "regression")
    public void searchTest() {
        gardenAndOutdoorsPage.setSearchCriteria("Audi lights").clickSubmitButton();
    }*/

    private void delayDriver() {
        synchronized (driver) {
            try {
                driver.wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
