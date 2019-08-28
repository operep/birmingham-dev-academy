package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.GroceryPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GroceryTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private GroceryPage groceryPage;

    @BeforeMethod
    public void setUp() throws Exception {
        groceryPage = navigator.navigateToPage(driver, GroceryPage.PATH, GroceryPage.class);
    }

    @Test(groups = "regression")
    public void groceryNavigationPageTest() {
        assertThat(groceryPage.isPageLoaded(), is(true));
    }

    @Test(groups = "regression")
    public void globalStoreDisplayedOnGroceryPageTest() {
        assertThat(groceryPage.isGlobalStoreDisplayed(), is(true));
    }

    @Test(groups = "regression")
    public void avgCustomerReviewDisplayedOnGroceryPageTest() {
        assertThat(groceryPage.isAverageCustomerReviewDisplayed(), is(true));
    }

    @Test(groups = "regression")
    public void subMenuTitleMatchesTitleOnGroceryPageTest() {
        assertThat(groceryPage.isSubmenuTitleMatchingTitle(), is(true));
    }

    @Test(groups = "regression")
    public void subMenuTitleLinkNavigatesToGroceryPageTest() {
        assertThat(groceryPage.isSubmenuLinkNavigatingToGroceryPage(), is(true));
    }

    @Test(groups = "regression")
    public void primeItemsFilteredOnGroceryPageTest() {
        assertThat(groceryPage.isPrimeItemsFiltered(), is(true));
    }

}

