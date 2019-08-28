package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.ComputersAndAccessoriesPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DanielTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private ComputersAndAccessoriesPage computersAndAccessoriesPage;

    @BeforeMethod
    public void setUp() throws Exception {
        computersAndAccessoriesPage = navigator.navigateToPage(driver, ComputersAndAccessoriesPage.PATH, ComputersAndAccessoriesPage.class);
    }

    @Test (groups = "regression")
    public void computersAndAccessoriesPageNavigationPageTest() {
        assertThat(computersAndAccessoriesPage.isLoaded(), is(true));
    }

    @Test (groups = "regression")
    public void computersAndAccessoriesPageUrlNavigationPageTest() {
        assertThat(computersAndAccessoriesPage.isLoaded(), is(true));
    }

    @Test (groups = "regression")
    public void searchTest() {
        computersAndAccessoriesPage.setSearchCriteria("Keyboard").clickSubmitButton();
    }

    @Test (groups = "regression")
    public void titleTest() {
       assertThat( computersAndAccessoriesPage.getTitle(), is (computersAndAccessoriesPage.title));
    }

    @Test (groups = "regression")
    public void checkIfCategoryIsGlobalStoreTest() {
        assertThat( computersAndAccessoriesPage.isGlobalStore(), is (true));
    }
    @Test (groups = "regression")
    public void checkThatAvgReviewsIsDisplayedTest() {
       assertThat( computersAndAccessoriesPage.isAverageReviewsDisplayed(), is (true));
    }

    @Test (groups = "regression")
    public void checkIfMenuTitleEqualsPageTitleTest() {
        assertThat( computersAndAccessoriesPage.getDropDownMenuText(), is (computersAndAccessoriesPage.getTitle()));
    }

    @Test (groups = "regression")
    public void checkUserIsRedirectedToSamePage() {
        assertThat( computersAndAccessoriesPage.checkDropdownRedirectEqualsCurrentPage(), is (true));
    }

    @Test (groups = "regression")
    public void checkAmazonPrimeLabels() {
        assertThat( computersAndAccessoriesPage.checkAmazonPrimeLabels(), is (true));
    }

}
