package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.ElectronicsPhotoPage;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.hamcrest.Matchers.lessThanOrEqualTo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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

    @Test(groups = "regression")
    public void fiveStartsReviewsTest() throws Exception {
        setUp();
        assertThat(electronicsPhotoPage.isFiveStarsSelected(), is(true));
    }

    @Test(groups = "regression")
    public void avgCustomerReviewSortSelectionTest() throws Exception{
        setUp();
        assertThat(electronicsPhotoPage.isAvgCustomerReviewSortSelected(), is(true));
    }

    @Test(groups = "regression")
    public void ratingSortByStarsTest() throws Exception {
        setUp();
        float prevScore = Collections.max(electronicsPhotoPage.getReviewScores());
        System.out.println(prevScore);
        for (float currScore : electronicsPhotoPage.getReviewScores()) {
            assertThat(currScore, lessThanOrEqualTo(prevScore));
            prevScore = currScore;
        }
    }

}
