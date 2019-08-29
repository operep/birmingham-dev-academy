package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.SportsAndOutdoorsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SportsAndOutdoorsPageTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private SportsAndOutdoorsPage sportsAndOutdoorsPage;

    @BeforeMethod
    public void setUp() throws Exception {
        sportsAndOutdoorsPage = navigator.navigateToPage(driver, SportsAndOutdoorsPage.PATH, SportsAndOutdoorsPage.class);
    }

    @Test (groups = "regression")
    public void sportsAndOutdoorsURLNavigationPageTest() { assertThat(sportsAndOutdoorsPage.isOnCorrectPage(), is(true)); }

    @Test (groups = "regression")
    public void sportsAndOutdoorsGlobalStoreTest() { assertThat(sportsAndOutdoorsPage.isGlobalStoreExists(), is(true)); }

    @Test (groups = "regression")
    public void sportsAndOutdoorsAvgCustomerReviewTest() { assertThat(sportsAndOutdoorsPage.isAvgCustomerReviewExists(), is(true)); }

    @Test (groups = "regression")
    public void sportsAndOutdoorsPageLinkSameAsCategoryNameTest() { assertThat(sportsAndOutdoorsPage.isPageLinkSameAsCategoryName(), is(true)); }

    @Test (groups = "regression")
    public void sportsAndOutdoorsPageLinkToSamePageTest() { assertThat(sportsAndOutdoorsPage.isSamePageOnClick(), is(true)); }

    @Test (groups = "regression")
    public void sportsAndOutdoorsPrimeOrMoreBuyingChoicesTest() { assertThat(sportsAndOutdoorsPage.primeOrMoreBuyingChoicesExists(), is(true)); }

    //Write later
//    @Test (groups = "regression")
//    public void sportsAndOutdoorsRatingThresholdCheck(){ (true, is(true)); }

}
