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
    private BooksPage BooksPage;

    @BeforeMethod
    public void setUp() throws Exception {
        BooksPage = navigator.navigateToPage(driver, "https://www.amazon.co.uk/", BooksPage.class);
        BooksPage.selectBooks();
    }


    @Test (groups = "regression")
    public void checkTitleMatchTest() {
        assertThat(BooksPage.doesTitleMatch(), is(true));
    }

    @Test (groups = "regression")
    public void checkGlobalStoreExistsTest() {
        assertThat(BooksPage.isGlobalStoreDisplayed(), is(true));
    }

    @Test (groups = "regression")
    public void checkAvgCustomerExistsTest() {
        assertThat(BooksPage.isAvgCustomerDisplayed(), is(true));
    }

    @Test (groups = "regression")
    public void checkSecondLineMenuMatchsTest() {
        assertThat(BooksPage.doesSecondLineMatchs(), is(true));
    }

    @Test (groups = "regression")
    public void checkClickedLinkSameTest() {
        assertThat(BooksPage.isSameWhenClicked(), is(true));
    }

    @Test (groups = "regression")
    public void checkPrimeBoxTest() {
        assertThat(BooksPage.isPrimeBoxEverywhere(), is(true));
    }


















}
