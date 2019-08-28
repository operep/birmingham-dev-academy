package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.ElectronicsPhotoPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.core.Is.is;

import static org.hamcrest.MatcherAssert.assertThat;

public class ElectronicsPhotoTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private ElectronicsPhotoPage electronicsPhotoPage;
    private boolean isInitialTest = false;

    public void setUp() throws Exception {
        if (isInitialTest) {
            electronicsPhotoPage = navigator.navigateToPage(driver, ElectronicsPhotoPage.HOME_PATH, ElectronicsPhotoPage.class);
            electronicsPhotoPage.selectElectronicsCategory();
            isInitialTest = false;
        } else {
            electronicsPhotoPage = navigator.navigateToPage(driver, ElectronicsPhotoPage.PATH, ElectronicsPhotoPage.class);
        }
    }

    @Test(groups = "regression")
    public void electronicsPhotoCategorySelectionTest() throws Exception {
        isInitialTest = true;
        setUp();
        assertThat(electronicsPhotoPage.isElectronicsCategorySelected(), is(true));
    }

    // checks if the loaded page is The Electronics Store
    @Test(groups = "regression")
    public void electronicsPhotoNavigationPageTest() throws Exception {
        isInitialTest = true;
        setUp();
        assertThat(electronicsPhotoPage.isPageLoaded(), is(true));
    }

    @Test(groups = "regression")
    public void globalStoreCategoryTest() throws Exception {
        setUp();
        assertThat(electronicsPhotoPage.isGlobalStoreDisplayed(), is(true));
    }

    @Test(groups = "regression")
    public void avgCategoryTest() throws Exception {
        setUp();
        assertThat(electronicsPhotoPage.isAvgCustomerReviewDisplayed(), is(true));
    }

    @Test(groups = "regression")
    public void secondLineLinkTest() throws Exception {
        setUp();
        assertThat(electronicsPhotoPage.isSecondLineLinkDisplayed(), is(true));
    }

    @Test(groups = "regression")
    public void compareInitialAndReloadTitle() throws Exception {
        setUp();
        assertThat(electronicsPhotoPage.isTitleSameAfterReload(), is(true));
    }

    @Test(groups = "regression")
    public void primeLabelsTest() throws Exception {
        setUp();
        electronicsPhotoPage.checkPrimeLabels();
    }
}
