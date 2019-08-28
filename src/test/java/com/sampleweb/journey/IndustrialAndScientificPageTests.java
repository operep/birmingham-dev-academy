package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.IndustrialAndScientificPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MyTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private IndustrialAndScientificPage inSciPage;

    @BeforeMethod
    public void setUp() throws Exception {
        inSciPage = navigator.navigateToPage(driver, IndustrialAndScientificPage.getPATH(), IndustrialAndScientificPage.class);
        inSciPage.navigateToInSciPage();
    }

    @Test (groups = "regression")
    public void hasCorrectTitle() {
        assertThat(inSciPage.isLoaded(), is(true));
    }

    @Test (groups = "regression")
    public void hasGlobalStoreSectionTest() {
        assertThat(inSciPage.hasGlobalStoreSection(), is(true));
    }

    @Test (groups = "regression")
    public void hasCustomerReviewSectionTest() {
        assertThat(inSciPage.hasCustomerReviewSection(), is(true));
    }

    @Test (groups = "regression")
    public void matchesCategoryNameTest() {
        assertThat(inSciPage.matchesCategoryName(), is(true));
    }

    @Test (groups = "regression")
    public void isRedirectedToSamePage() {
        assertThat(inSciPage.redirectedToSamePage(), is(true));
    }

    @Test (groups = "regression")
    public void verifyPrimeLabelTest() {
        assertThat(inSciPage.verifyPrimeLabel(), is(true));
    }

}
