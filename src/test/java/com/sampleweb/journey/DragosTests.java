package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.BooksPage;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.print.Book;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class DragosTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private BooksPage booksPage;

    @BeforeMethod
    public void setUp() throws Exception {
        booksPage = navigator.navigateToPage(driver, BooksPage.PATH, BooksPage.class);
        //booksPage = navigator.navigateToPage(driver, booksPage.PATH, BooksPage.class);
        booksPage.selectBookCategory();
    }

    @Test (groups = "regression")
    public void checkTitleMatchTest() {
        assertThat(booksPage.isTitleMatching(), is(true));
    }

    @Test (groups = "regression")
    public void checkGlobalStoreExistsTest() {
        assertThat(booksPage.isGlobalStoreDisplayed(), is(true));
    }

    @Test (groups = "regression")
    public void checkAvgCustomerExistsTest() {
        assertThat(booksPage.isAvgCustomerDisplayed(), is(true));
    }

    @Test (groups = "regression")
    public void checkPageSameAfterMenuLinkClicked() {
        assertThat(booksPage.isTitleSameAfterClickingMenuLink(), is(true));
    }

    @Test (groups = "regression")
    public void checkSamePageAfter() {
        assertThat(booksPage.isPageSameAfterMenuLinkClicked(), is(true));
    }

    @Test (groups = "regression")
    public void checkEveryElementContainsPrimeLabelTests() {
        assertThat(booksPage.isEveryElementWithPrimeLabel(), is(true));
    }
}
