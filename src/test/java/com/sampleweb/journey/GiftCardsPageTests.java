package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.GiftCardsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

//This page doesn't have a title, so for test '1. Navigate to your specific category page and ensure that title is correct', the
//first link in the second line menu has been used as the 'title'


public class GiftCardsPageTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private GiftCardsPage giftCardsPage;

    @BeforeMethod
    public void setUp() throws Exception {
        giftCardsPage = navigator.navigateToPage(driver, GiftCardsPage.PATH, GiftCardsPage.class);
    }

    @Test(groups="regression")
    public void giftCardsNavigationPageTest(){
        assertThat(giftCardsPage.isPageLoaded(), is(true));
    }

    @Test(groups="regression")
    public void giftCardsGlobalStoreSelectionExists(){
        assertThat(giftCardsPage.verifyGiftCardPageGlobalStoreSelectionExists(), is(false));
    }

    @Test(groups="regression")
    public void giftCardsCustomerReviewExists(){
        assertThat(giftCardsPage.verifyGiftCardPageCustomerReviewExists(), is(true));
    }

    @Test(groups="regression")
    public void giftCardsLinkInMenuSameAsCategory(){
        assertThat(giftCardsPage.verifyGiftCardPageTitle(), is(true));
    }

    @Test(groups="regression")
    public void giftCardsFirstLinkSecondLineMenuRedirection(){
        assertThat(giftCardsPage.verifyGiftCardMenuButtonRedirection(), is(true));
    }

    @Test(groups="regression")
    public void giftCardsPrimeLabelDescription(){
        assertThat(giftCardsPage.verifyPrimeCheckboxResultsList(), is(true));
    }

}
