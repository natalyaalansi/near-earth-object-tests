package gov.nasa.jpl.cneos.tests;

import gov.nasa.jpl.cneos.pages.NeosPage;
import gov.nasa.jpl.cneos.testdata.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class NeosTest extends TestBase {

    NeosPage neosPage = new NeosPage();

    @Test
    @DisplayName("Header navigation contains menu and breadcrumbs")
    @Tags({
            @Tag("WEB"),
            @Tag("SANITY"),
            @Tag("MINOR"),
    })
    void headerNavigationContainsMenuAndBreadcrumbs() {
        neosPage.openPage()
                .checkLeftMenu()
                .checkRightMenu()
                .checkBreadcrumbs();
    }

    @Test
    @DisplayName("Page content consists of required blocks")
    @Tags({
            @Tag("WEB"),
            @Tag("SANITY"),
            @Tag("MAJOR"),
    })
    void pageContentHasSpecificBlocks() {
        neosPage.openPage()
                .checkHeading()
                .checkSubheading()
                .checkVideoLink()
                .checkTableSettingsName()
                .checkTableGeneration();
    }

    @Test
    @DisplayName("Updating the results table according to default filters is disabled")
    @Tags({
            @Tag("WEB"),
            @Tag("SANITY"),
            @Tag("LOW"),
    })
    void updateButtonIsDisabledForDefaultFilters() {
        neosPage.openPage()
                .checkDefaultDateRangeFilter()
                .checkDefaultHMaxFilter()
                .checkDefaultDistMaxFilter()
                .checkDisabledUpdateButton();
    }

    @Test
    @DisplayName("The default filters are displayed, after resetting the filters")
    @Tags({
            @Tag("WEB"),
            @Tag("SANITY"),
            @Tag("MINOR"),
    })
    void defaultFiltersAreDisplayedAfterResettingSettings() {

        neosPage.openPage()
                .setDateRange(TestData.DateRange.recentPast)
                .setDistMax(TestData.DistMax.le1Ld)
                .setHMax(TestData.HMax.le22)
                .applyDefaultFilters()
                .checkDefaultDateRangeFilter()
                .checkDefaultHMaxFilter()
                .checkDefaultDistMaxFilter();
    }

    @Test
    @DisplayName("The table contains the results when specific filters are applied")
    @Tags({
            @Tag("WEB"),
            @Tag("SANITY"),
            @Tag("MAJOR"),
    })
    void tableContainsResultsForSpecificFilters() {
        neosPage.openPage()
                .setDateRange(TestData.DateRange.recentPast)
                .setDistMax(TestData.DistMax.le5Ld)
                .setHMax(TestData.HMax.le26)
                .updateData()
                .checkTableContainsResults();
    }

    @Test
    @DisplayName("The table does not contain the results when specific filters are applied")
    @Tags({
            @Tag("WEB"),
            @Tag("SANITY"),
            @Tag("MAJOR"),
    })
    void tableDoesNotContainResultsForSpecificFilters() {
        neosPage.openPage()
                .setDateRange(TestData.DateRange.recentPast)
                .setDistMax(TestData.DistMax.le1Ld)
                .setHMax(TestData.HMax.le26)
                .updateData()
                .checkTableContainsNoResult();
    }

    @CsvSource(value = {
            "au | km/s",
            "km | km/h",
            "LD | mi/h",
            "mi | km/s",
            "Re | km/h"
    }, delimiter = '|')
    @ParameterizedTest(name = "Distance and Velocity header columns contain modified {0} and {1} units respectively")
    @DisplayName("Distance and Velocity header columns contain modified units respectively")
    @Tags({
            @Tag("WEB"),
            @Tag("SANITY"),
            @Tag("MAJOR"),
    })
    void columnNamesChangeWhenUnitsChange(String distanceUnit, String velocityUnit) {
        neosPage.openPage()
                .setUnitToDistance(distanceUnit)
                .setUnitToVelocity(velocityUnit)
                .checkDistanceColumnsContainChangedUnits(distanceUnit)
                .checkVelocityColumnsContainChangedUnits(velocityUnit);
    }
}
