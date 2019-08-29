package com.sampleweb.journey;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.PCVideoGamesPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// Suppress the 'regression is undefined' warning
@SuppressWarnings("groupsTestNG")
public class PCVideoGamesTest extends BaseTest {

  private PCVideoGamesPage page;
  private PageNavigator navigator = new PageNavigator();


  @BeforeMethod
  public void setUp() throws Exception {
    page = navigator.navigateToPage(driver, PCVideoGamesPage.PATH, PCVideoGamesPage.class);
  }

  @Test (groups = "regression")
  void gamesNavigationPageDropdownTitleTest() throws Exception {
    // Navigate to the homepage and then perform the dropdown navigation
    page = navigator.navigateToPage(driver, baseUrl(), PCVideoGamesPage.class);
    page.navigateByDropdown();
    assertTrue(page.isLoaded());
  }

  @Test (groups = "regression")
  void gamesHasGlobalTest() {
    assertTrue(page.hasGlobal());
  }

  @Test (groups = "regression")
  void gamesHasAvgReviewTest() {
    assertTrue(page.hasAvgReview());
  }

  @Test (groups = "regression")
  void gameHasSameFirstNavLink() {
    assertTrue(page.hasSameFirstNavLink());
  }

  @Test (groups = "regression")
  void gameFirstNaveLinkSamePage() {
    assertTrue(page.firstLinkSameNav());
  }

  @Test (groups = "regression")
  void gameCheckPrimeItems() {
    assertTrue(page.checkPrimeItems());
  }

  @Test(groups = "regression")
  void gameCheckReviewOrder() {
    assertFalse(page.checkReviewOrder());
  }

}
