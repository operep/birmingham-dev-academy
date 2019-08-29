package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.BabyPage;
import com.sampleweb.pages.ChildrensBooksPage;
import com.sampleweb.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ChildrensBookPageTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private ChildrensBooksPage childrensBooksPage;

    @BeforeMethod
    public void setUp() throws Exception {
        childrensBooksPage = navigator.navigateToPage(driver, ChildrensBooksPage.PATH, ChildrensBooksPage.class);
    }

    @Test (groups = "regression")
    public void babyProductsNavigationPageTest() {
        assertThat(childrensBooksPage.isPageLoaded(), is(true));
    }

    @Test (groups = "regression")
    public void babyProductsUrlNavigationPageTest() {
        assertThat(childrensBooksPage.isPageLoaded(), is(true));
    }

    @Test (groups = "regression")
    public void searchTest() {
        childrensBooksPage.setSearchCriteria("Audi lights").clickSubmitButton();
    }
}
