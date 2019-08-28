package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.ChildrensBooksPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class OlekTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private ChildrensBooksPage childrensBooksPage;

    @BeforeMethod
    public void setUp() throws Exception {
        childrensBooksPage = navigator.navigateToPage(driver, ChildrensBooksPage.PATH, ChildrensBooksPage.class);
    }

//    @Test (groups = "regression")
//    public void babyProductsNavigationPageTest() {
//        assertThat(childrensBooksPage.isLoaded(), is(true));
//    }
//
//    @Test (groups = "regression")
//    public void babyProductsUrlNavigationPageTest() {
//        assertThat(childrensBooksPage.isLoaded(), is(true));
//    }
//
//
//    @Test (groups = "regression")
//    public void test() throws Exception {
//        assertThat(childrensBooksPage.isTitleCorrect(), is(true));
//    }

    @Test (groups = "regression")
    public void titleNavigationTest() throws Exception {
        assertThat(childrensBooksPage.isTitleCorrect(), is(true));
    }

    @Test (groups = "regression")
    public void checkTitle() throws Exception {
        WebElement selectDropDown = driver.findElementByXPath("//select[@aria-describedby=\"searchDropdownDescription\"]");

        Select dropdown = new Select(selectDropDown);

        dropdown.selectByVisibleText("Luxury Beauty");

        WebElement searchButton = driver.findElementByXPath("//*[@id=\"nav-search\"]/form/div[2]/div/input");

        searchButton.click();

    }

    @Test (groups = "regression")
    public void CheckIfGlobalStoreExist() throws Exception {
        WebElement globalStoreElem = driver.findElementByXPath("//input[@name=\"s-ref-checkbox-11259240031\"]");

        if(globalStoreElem == null)
        {
            Assert.fail();
        }
    }

    @Test (groups = "regression")
    public void customerReview() throws Exception {
        WebElement customerReview = driver.findElementByXPath("//*[@id=\"leftNav\"]/ul[11]");

        if(customerReview == null)
        {
            Assert.fail();
        }
    }


    @Test (groups = "regression")
    public void firstLinkSecondMenuSameAsCat() throws Exception {
        WebElement categoryLink = driver.findElementByXPath("//*[@id=\"nav-subnav\"]/a[1]");

        if(categoryLink == null || !categoryLink.getText().equals(childrensBooksPage.getTitle()))
        {
            Assert.fail();
        }
    }

    @Test (groups = "regression")
    public void checkLinkSameAsPage() throws Exception {
        WebElement categoryLink = driver.findElementByXPath("//*[@id=\"nav-subnav\"]/a[1]");
        categoryLink.click();

        WebElement pageTitle = driver.findElementByXPath("//img[@alt=\"Luxury Beauty\"]");

        if(!pageTitle.getAttribute("alt").equals(childrensBooksPage.getTitle()))
        {
            Assert.fail();
        }
    }


}
