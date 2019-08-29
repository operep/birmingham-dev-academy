package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.HomeKitchen;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HomeKitchenTest extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private HomeKitchen homeKitchen;

    public void setUp() throws Exception {
        homeKitchen = navigator.navigateToPage(driver, HomeKitchen.PATH, HomeKitchen.class);
    }

    @Test(groups = "regression")
    public void directToPantryTest() throws Exception {
        homeKitchen = new HomeKitchen(driver);
        assertThat(homeKitchen.directToKitchen().isTitleCorrect(homeKitchen.title), is(true));
    }

    @Test(groups = "regression")
    public void isGlobalSectionSelectorThereTest() throws Exception {
        setUp();
        assertThat(homeKitchen.isGlobalStoreSectionThere(), is(true));
    }

    @Test(groups = "regression")
    public void isAverageCustomerReviewThereTest() throws Exception {
        setUp();
        assertThat(homeKitchen.isAverageCustomerReviewThere(), is(true));
    }

    @Test(groups = "regression")
    public void isSubMenuCorrectTest() throws Exception{
        setUp();
        assertThat(homeKitchen.isSubMenuCorrect(), is(true));
    }

    @Test(groups = "regression")
    public void isPrimeCorrectTest() throws Exception {
        setUp();
        assertThat(homeKitchen.isPrimeCorrect(), is(true));
    }

}
