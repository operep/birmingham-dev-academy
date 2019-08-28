package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.ClassicalMusicPage;
import com.sampleweb.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ClassicalTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private PageNavigator homeNavigator = new PageNavigator();
    private ClassicalMusicPage classicalPage;
    private HomePage homepage;

    @BeforeMethod
    public void setUp() throws Exception {
        classicalPage = navigator.navigateToPage(driver, ClassicalMusicPage.PATH, ClassicalMusicPage.class);
    }

    @Test (groups = "regression")
    public void classicalPageLoadingTest() {
        assertThat(classicalPage.isLoaded(), is(true));
    }

    @Test (groups = "regression")
    public void classicalPageTitleTest() {
        assertThat(classicalPage.getTitle(), is(classicalPage.title));
    }

    @Test (groups = "regression")
    public void classicalPageNavigateTest() throws Exception {
        homepage = homeNavigator.navigateToPage(driver, HomePage.PATH, HomePage.class);
        homepage.setSearchCategoryClassical().clickSubmitButton();
        assertThat(homepage.getTitle(), is(classicalPage.title));
    }

    @Test (groups = "regression")
    public void classicalPageContainsGlobalTest() {
        WebElement globalSection = classicalPage.getGlobalSection();
        assertThat(globalSection.getText(), is("Global Store"));
    }

    @Test (groups = "regression")
    public void classicalPageContainsAvgCustReview() {
        WebElement avgRevSection = classicalPage.getAvgReviewSection();
        assertThat(avgRevSection.getText(), is("Avg. Customer Review"));
    }

    // redirects to 'Amazon Music'
    @Test (groups = "regression")
    public void classicalPageContainsCorrectLinkName() {
        WebElement categoryLink = classicalPage.getCategoryLink();
        assertThat(categoryLink.getAttribute("data-category"), is("music"));
    }

    // redirects to 'Amazon Music'
    @Test (groups = "regression")
    public void classicalPageContainsCorrectLinkTarget() {
        classicalPage.followCategoryLink();
        assertThat(classicalPage.getTitle(), is("CDs & Vinyl"));
    }

    @Test (groups="regression")
    public void classicalPageGlobalStoreCheckboxWorks() {
        classicalPage.activateGlobalStoreCheckbox();
        List<WebElement> products = classicalPage.getAllPrimeElements();
        //System.out.println(products.size());//gives 16 products on the page
        for(WebElement p: products) {
            assertThat(p.findElement(By.xpath("//i[@aria-label='Amazon Prime']")).isDisplayed(), is(true));
        }
    }
}
