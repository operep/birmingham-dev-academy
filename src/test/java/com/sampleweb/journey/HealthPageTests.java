package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.HealthPage;
import com.sampleweb.pages.HomePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HealthPageTests extends BaseTest {
    private PageNavigator navigator = new PageNavigator();
    private HealthPage healthPage;

    @BeforeMethod
    public void setUp() throws Exception {
        healthPage = new HealthPage(driver);
    }

    @Test(groups = "regression")
    public void healthNavigationPageTest() throws Exception {
        assertThat(healthPage.isHealthPageLoaded(), is(true));
    }

    @Test(groups = "regression")
    public void healthPageValidTitleTest() throws Exception {
        assertThat(healthPage.verifyFirstLinkHasCatagoryName(), is(true));
    }

    @Test(groups = "regression")
    public void healthValidateGlobalStoreExistsTest() throws Exception {
        assertThat(healthPage.verifyGlobalStoreExists(), is(true));
    }

    @Test(groups = "regression")
    public void healthValidateCustomerReviewsExistTest() throws Exception {
        assertThat(healthPage.verifyAverageReviewsExist(), is(true));
    }

    @Test(groups = "regression")
    public void healthValidateNavigationRedirectsCorrectlyTest() throws Exception{
        assertThat(healthPage.verifyClickOnHeaderMenuDirectsToSamePage(), is(true));
    }

    @Test(groups = "regression")
    public void ensureAllPrimeItemsLabelledTest() throws Exception{
        assertThat(healthPage.verifySelectPrimeReturnsOnlyPrimeitems(), is (true));
    }

    //@Test(groups = "regression")

}
