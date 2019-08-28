package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.ToolsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ToolsTests extends BaseTest {
    private PageNavigator navigator = new PageNavigator();
    private ToolsPage toolsPage;

    @BeforeMethod
    public void setUp() throws Exception {
        toolsPage = navigator.navigateToPage(driver, ToolsPage.PATH, ToolsPage.class);
    }

    @Test(groups = "regression")
    public void toolPageTitleTest() {
        assertTrue(toolsPage.isTitleCorrect());
    }

    @Test(groups = "regression")
    public void globalSectionContainerTest() {
        assertTrue(toolsPage.isGlobalSection());
    }

    @Test(groups = "regression")
    public void reviewSectionContainerTest() {
        assertTrue(toolsPage.isReviewSection());
    }

    @Test(groups = "regression")
    public void toolsTitlesTest() {
        // The titles are different
        assertFalse(toolsPage.isTitleMatch());
    }

    @Test(groups = "regression")
    public void pageLinksTest() {
        assertTrue(toolsPage.isLinkTheSame());
    }

    @Test(groups = "regression")
    public void verifyPrimeItemsTest() {
        assertTrue(toolsPage.isPrimeLabel());
    }

    @Test(groups = "regression")
    public void checkElementsAreOrderedByStarsTest() {
        assertTrue(toolsPage.isPageSortedByStarReviews());
    }
}
