package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.HomePage;
import com.sampleweb.pages.PetSuppliesPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PetSupplyPageTests extends BaseTest {
    private PageNavigator navigator = new PageNavigator();

    private PetSuppliesPage page = new PetSuppliesPage(driver);
    private boolean isFirstTestComplete = true;

    @BeforeMethod
    public void setUp() throws Exception {
        if(isFirstTestComplete)
            page = navigator.navigateToPage(driver, PetSuppliesPage.PATH, PetSuppliesPage.class);
        else
            page = navigator.navigateToPage(driver, HomePage.PATH, PetSuppliesPage.class);

    }

//    1. Navigate to your specific category page and ensure that title is correct.
    @Test(groups = "regression")
    public void petSuppliesCategoryTitleTest() {

        page.selectDropdownCategory("Pet Supplies");
        page.clickSubmitButton();
        assertThat(page.isLoaded(), is(true));
        isFirstTestComplete = true;
    }

//    2. Navigate to you specific category page and verify that Global store section exists or doesn't (if there's no such section on the page)
    @Test (groups = "regression")
    public void petSuppliesGlobalStoreCheckboxTest() {
        assertThat(page.isGlobalStoreCheckboxDisplayed(), is(true));
    }

//    3. Navigate to your specific category page and verify that Avg. Customer Review section displayed
    @Test (groups = "regression")
    public void petSuppliesAverageCustomerReviewDisplayedTest() {
        assertThat(page.isAverageCustomerRatingTitleDisplayed(), is(true));
        assertThat(page.isAverageCustomerRatingFieldsDisplayed(), is(true));
    }

//    4. Navigate to your specific category page and verify that first link in second line menu is the same as the category name

    @Test (groups = "regression")
    public void petSuppliesPageFirstLinkInSecondMenuSameAsTitleTest(){
        assertThat(page.isFirstLinkInSecondMenuSameAsTitleTest(), is(true));
    }
//    5. Navigate to your specific category page and verify that  after clicking on the first link in second line menu user is being redirected to the same page
    @Test (groups = "regression")
    public void petSuppliesPageFirstLinkInSecondMenuDirectsToSamePageTest(){
        page.clickOnFirstLinkInSecondMenu();
        assertThat(page.isLoaded(), is(true));
    }

//    Not finished yet.
//    6. Navigate to your specific category page, check the PRIME checkbox and verify that all items from the results list on the first page have prime label in the description. Exclude items with More buying choices line
    @Test(groups = "regression")
    public void petSuppliesPageShowsPrimeItemsOnlyWhenPrimeCheckboxIsCheckedTest(){
        page.clickOnPrimeCheckbox();
        assertThat(page.checkOnlyPrimeItemsAreAvailable(), is(true));
    }

}
