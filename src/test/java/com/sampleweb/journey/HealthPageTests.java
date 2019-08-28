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
        assertThat(healthPage.isLoaded(), is(true));
    }

    @Test(groups = "regression")
    public void healthPageValidTitleTest() throws Exception {
        assertThat(healthPage.verifyTitle(), is(true));
    }

    @Test(groups = "regression")
    public void healthValidateGlobalStoreTest() throws Exception {
        assertThat(healthPage.verifyGlobalStore(), is(true));
    }

    @Test(groups = "regression")
    public void healthAverageCustomerReviewDisplayTest() throws Exception {
        assertThat(healthPage.verifyAverageReviews(), is(true));
    }

    @Test(groups = "regression")
    public void naveBarTitleDisplayTest() throws Exception{
        assertThat(healthPage.verifyFirstLinkHasCatagoryName(), is(true));
    }

    @Test(groups = "regression")
    public void ensureAllPrimeItemsHealthTest() throws Exception{
        assertThat(healthPage.selectPrimeReturnsOnlyPrimeHealth(), is (true));
    }
}
