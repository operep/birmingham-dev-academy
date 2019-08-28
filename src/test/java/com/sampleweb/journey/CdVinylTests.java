package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.CdVinylPage;
import com.sampleweb.pages.HomePage;

import com.sampleweb.pages.HomePage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class CdVinylTests  extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private CdVinylPage cdVinylPage;

    @BeforeMethod
    public void setUp() throws Exception {
        cdVinylPage = navigator.navigateToPage(driver, CdVinylPage.PATH, CdVinylPage.class);
    }

    @Test(groups = "regression")
    public void navigateToPage() throws Exception {
        //Navigate to homepage
        navigator.navigateToPage(driver, HomePage.PATH, HomePage.class);

        //CDs & Vinyl dropdown
        final String DROPDOWN_PATH = "//select[@class=\"nav-search-dropdown searchSelect\"]/option[@value=\"search-alias=popular\"]";

        driver.findElementByXPath(DROPDOWN_PATH).click();
        cdVinylPage.setSearchCriteria("").clickSubmitButton();

        assertThat(cdVinylPage.isLoaded(), is(true));
    }


    @Test(groups = "regression")
    public void titleTest() {
        assertThat(cdVinylPage.isExistsTitle(), is(true));
    }

    @Test(groups = "regression")
    public void checkGlobalStoreSection() {
        assertThat(cdVinylPage.isExistsGlobalStore(), is(true));
    }

    @Test(groups = "regression")
    public void checkAverageCustomerReview() {
        assertThat(cdVinylPage.isExistsAverageCustomerReview(), is(true));
    }

    //Its grouped under music so title is the 4th element
    @Test(groups = "regression")
    public void fourthLinkHeaderIsCategory() {
        assertThat(cdVinylPage.isExistsFourthLinkHeader(), is(true));
    }
    @Test(groups = "regression")
    public void checkfourthLinkRedirect() {
        assertThat(cdVinylPage.isExistsFourthLinkRedirect(), is(true));
    }

    @Test(groups = "regression")
    public void checkPrimeCheckBox() {
        assertThat(cdVinylPage.isPrimeCheckboxVisibleAndInItems(), is(true));
    }
}
