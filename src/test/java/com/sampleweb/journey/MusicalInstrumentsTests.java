package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.MusicalInstrumentsPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MusicalInstrumentsTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private MusicalInstrumentsPage musicalInstrumentsPage;

    public void loadMusicalInstrumentsPage() throws Exception {
        musicalInstrumentsPage = navigator.navigateToPage(driver, MusicalInstrumentsPage.PATH, MusicalInstrumentsPage.class);
    }

    @Test (groups = "regression")
    public void musicalInstrumentsNavigationAndTitleTest() throws Exception {
        musicalInstrumentsPage = navigator.navigateToPage(driver, MusicalInstrumentsPage.HOMEPATH, MusicalInstrumentsPage.class);
        musicalInstrumentsPage.SelectCategoryFromMenu();
        assertThat(musicalInstrumentsPage.isTitleCorrect(), is(true));
    }

    @Test (groups = "regression")
    public void musicalInstrumentsDisplaysGlobalStoreTest() throws Exception {
        loadMusicalInstrumentsPage();
        assertThat(musicalInstrumentsPage.hasGlobalStore(), is(true));
    }

    @Test (groups = "regression")
    public void musicalInstrumentsDisplaysAvgCustomerScoreTest() throws Exception {
        loadMusicalInstrumentsPage();
        assertThat(musicalInstrumentsPage.hasAvgCustomerScore(), is(true));
    }

    @Test (groups = "regression")
    public void musicalInstrumentsMenuNameValidityTest() throws Exception {
        loadMusicalInstrumentsPage();
        assertThat(musicalInstrumentsPage.isFirstLinkInSecondLineValid(), is(true));
    }

    @Test (groups = "regression")
    public void musicalInstrumentsSamePageOnClickTest() throws Exception {
        loadMusicalInstrumentsPage();
        assertThat(musicalInstrumentsPage.clickFirstLinkInSecondLineRedirect(), is(true));
    }

    @Test (groups = "regression")
    public void musicalInstrumentsVerifyPrimeLabelsTest() throws Exception {
        loadMusicalInstrumentsPage();
        musicalInstrumentsPage.checkPrimeBoxVerifyPrimeLabels();
    }

}
