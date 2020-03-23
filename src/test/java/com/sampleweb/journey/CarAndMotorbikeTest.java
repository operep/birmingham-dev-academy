package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.CarAndMotorbikePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

// A wild lenny appears ( ͡° ͜ʖ ͡°)

public class CarAndMotorbikeTest extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private CarAndMotorbikePage carAndMotorbikePage;

    @BeforeMethod
    public void setUp() throws Exception {
        carAndMotorbikePage = navigator.navigateToPage(driver, "https://www.amazon.co.uk/", CarAndMotorbikePage.class);
        carAndMotorbikePage.selectOptionFromDropdown();
    }

    @Test (groups = "regression")
    public void carNavigationPageTest() {
        assertThat(carAndMotorbikePage.isLoaded(), is(true));
    }

    @Test (groups = "regression")
    public void carUrlNavigationPageTest() {
        assertThat(carAndMotorbikePage.isLoaded(), is(true));
    }

    @Test (groups = "regression")
    public void carTitlePageCorrectTest() {
        assertThat(carAndMotorbikePage.checkPageTitle(), is(true));
    }

    @Test (groups = "regression")
    public void globalStoreTitleExistsTest() {
        assertThat(carAndMotorbikePage.doesGlobalStoreTitleExists(), is(true));
    }

    @Test (groups = "regression")
    public void avgCustomerReviewTitleExistsTest() {
        assertThat(carAndMotorbikePage.isAvgCustomerReviewDisplayed(), is(true));
    }

    @Test (groups = "regression")
    public void redirectedToSamePageTest() {
        carAndMotorbikePage.selectSecondLineMenuLink();
        assertThat(carAndMotorbikePage.checkPageTitle(), is(true));
    }

    @Test (groups = "regression")
    public void checkPrimeLabels() {
        carAndMotorbikePage.selectPrimeCheckbox();
        assertThat(carAndMotorbikePage.checkPrimeLabels(), is(true));
    }

}
