package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.GardenAndOutdoors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GardenAndOutdoorsPageTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private GardenAndOutdoors gardenAndOutdoorsPage;

    @BeforeMethod
    public void setUp() throws Exception {
        gardenAndOutdoorsPage = navigator.navigateToPage(driver, GardenAndOutdoors.PATH, GardenAndOutdoors.class);
    }

    @Test (groups = "regression")
    public void gardenProductsUrlNavigationPageTest() {
        assertThat(gardenAndOutdoorsPage.isPageLoaded(), is(true));
    }

    @Test (groups = "regression")
    public void setDepartmentDropdownTest() {
        gardenAndOutdoorsPage.setDepartmentDropdown(GardenAndOutdoors.TITLE);
    }

    @Test (groups = "regression")
    public void checkTitleCorrect() throws Exception {
        assertThat(gardenAndOutdoorsPage.isTitleCorrect(), is(true));
    }

    @Test (groups = "regression")
    public void checkGlobalStoreExists() {
        assertThat(gardenAndOutdoorsPage.isGlobalStore(), is(true));
    }

    @Test (groups = "regression")
    public void checkAverageCustomerReviewsExist() {
        assertThat(gardenAndOutdoorsPage.isAverageCustomerReviewDisplayed(), is(true));
    }

    @Test (groups = "regression")
    public void checkTitleAndLinkHeaderCorrectTest() throws Exception {
        assertThat(gardenAndOutdoorsPage.isTitleAndMenuLinkEqual(), is(true));
    }

    @Test (groups = "regression")
    public void openNewLinkTest() throws Exception {
        assertThat(gardenAndOutdoorsPage.openNewLinkInNewTabAndCheckIfCorrect(), is(true));
    }

    @Test (groups = "regression")
    public void primeCheckboxTest() {
        gardenAndOutdoorsPage.makePrimeCheckboxChecked();
    }

    @Test (groups = "regression")
    public void isItemsPrimeOnlyTest() {
        assertThat(gardenAndOutdoorsPage.makePrimeCheckboxChecked().isOnlyPrimeItems(), is(true));
    }

    @Test(groups = "regression")
    public void selectHighestReviewLevelTest() {
        gardenAndOutdoorsPage.selectHighestReviewLevel();
    }

    @Test(groups = "regression")
    public void orderByRatingTest() {
        gardenAndOutdoorsPage.selectHighestReviewLevel().orderByRating();
    }

    @Test(groups = "regression")
    public void isItemsSortedByOrderTest() {
        gardenAndOutdoorsPage.selectHighestReviewLevel().orderByRating().isItemsSortedByOrder();
    }
}
