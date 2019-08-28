package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.ChildrensBooksPage;
import com.sampleweb.pages.ToysAndGamesPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ToysTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private ToysAndGamesPage toysAndGamesPage;

    @BeforeMethod
    public void setUp() throws Exception {
        toysAndGamesPage = navigator.navigateToPage(driver, ToysAndGamesPage.PATH, ToysAndGamesPage.class);
        //toysAndGamesPage.selectToys();
    }

    //@Test(groups = "regression")
    //public void electronicsPhotoCategorySelectionTest() {
        //assertThat(toysAndGamesPage.isToysCategorySelected(), is(true));
    //}

    @Test(groups = "regression")
    public void toysAndGamesNavigationTest() {
        assertThat(toysAndGamesPage.isLoaded(), is(true));
    }

    @Test(groups = "regression")
    public void toysAndGamesTitleTest() {
        assertThat(toysAndGamesPage.isLoaded(), is(true));

    }

    @Test(groups = "regression")
    public void globalStoreTest(){
        assertThat(toysAndGamesPage.verifyGlobalStore(), is(true));
    }

    @Test(groups = "regression")
    public void avgRatingTest(){
        assertThat(toysAndGamesPage.verifyAvgRating(), is(true));
    }

    @Test(groups = "regression")
    public void verifyCategoryTest(){
        assertThat(toysAndGamesPage.verifyCategoryName(), is(true));
    }

    @Test(groups = "regression")
    public void verifyClickTest(){
        assertThat(toysAndGamesPage.verifyClickInMenu(), is(true));
    }

    @Test(groups = "regression")
    public void verifyPrimeTest(){
        assertThat(toysAndGamesPage.verifyPrime(), is(true));
    }
}
