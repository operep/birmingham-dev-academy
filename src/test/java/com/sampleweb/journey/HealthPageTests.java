package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.HealthPage;
import com.sampleweb.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HealthPageTests extends BaseTest {
    private PageNavigator navigator = new PageNavigator();
    private HealthPage healthPage;

    @BeforeMethod
    public void setUp() throws Exception {
        healthPage = navigator.navigateToPage(driver, HealthPage.PATH, HealthPage.class);
    }

    @Test(groups = "regression")
    public void healthNavigationPageTest() {
        assertThat(healthPage.isLoaded(), is(true));
    }

    @Test(groups = "regression")
    public void healthPageValidTitleTest() throws Exception {
        assertThat(healthPage.verifyTitle(), is(true));
    }


}
