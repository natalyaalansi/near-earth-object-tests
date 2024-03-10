package gov.nasa.jpl.cneos.tests;

import gov.nasa.jpl.cneos.pages.NeosPage;
import gov.nasa.jpl.cneos.testdata.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.qameta.allure.Allure.step;

@Tags({
        @Tag("neos"),
        @Tag("web"),
        @Tag("sanity"),
})
public class NeosTest extends TestBase {

    NeosPage neosPage = new NeosPage();

    @Test
    @DisplayName("Header navigation contains menu and breadcrumbs")
    @Tag("minor")
    void headerNavigationContainsMenuAndBreadcrumbs() {

        step("Open NEO Earth Close Approaches page", () -> {
            neosPage.openPage();
        });

        step("Check Left Menu Items, Right Menu Items, Breadcrumbs", () -> {
            neosPage.checkLeftMenu()
                    .checkRightMenu()
                    .checkBreadcrumbs();
        });
    }

    @Test
    @DisplayName("Page content consists of required blocks")
    @Tag("major")
    void pageContentHasSpecificBlocks() {

        step("Open NEO Earth Close Approaches page", () -> {
            neosPage.openPage();
        });

        step("Verify Heading, Subheading", () -> {
            neosPage.checkHeading()
                    .checkSubheading();
        });

        step("Check for a link to a brief video tutorial", () -> {
            neosPage.checkVideoLink();
        });

        step("Verify the presence of the header of the filter table and data table", () -> {
            neosPage.checkTableSettingsName();
            neosPage.checkTableGeneration();
        });
    }

    @Test
    @DisplayName("Updating the results table according to default filters is disabled")
    @Tag("low")
    void updateButtonIsDisabledForDefaultFilters() {

        step("Open NEO Earth Close Approaches page", () -> {
            neosPage.openPage();
        });

        step("Verify filter values are displayed by default", () -> {
            neosPage.checkDefaultDateRangeFilter()
                    .checkDefaultHMaxFilter()
                    .checkDefaultDistMaxFilter();
        });

        step("Check Update Data button is disabled", () -> {
            neosPage.checkDisabledUpdateButton();
        });
    }

    @Test
    @DisplayName("The default filters are displayed, after resetting the filters")
    @Tag("minor")
    void defaultFiltersAreDisplayedAfterResettingSettings() {

        step("Open NEO Earth Close Approaches page", () -> {
            neosPage.openPage();
        });

        step("Set specific filters", () -> {
            neosPage.setDateRange(TestData.DateRange.recentPast)
                    .setDistMax(TestData.DistMax.le1Ld)
                    .setHMax(TestData.HMax.le22);
        });

        step("Revert settings", () -> {
            neosPage.applyDefaultFilters();
        });

        step("Verify filter values are displayed by default after resetting the filters", () -> {
            neosPage.checkDefaultDateRangeFilter()
                    .checkDefaultHMaxFilter()
                    .checkDefaultDistMaxFilter();
        });
    }

    @Test
    @DisplayName("The table contains the results when specific filters are applied")
    @Tag("major")
    void tableContainsResultsForSpecificFilters() {

        step("Open NEO Earth Close Approaches page", () -> {
            neosPage.openPage();
        });

        step("Set specific filters", () -> {
            neosPage.setDateRange(TestData.DateRange.recentPast)
                    .setDistMax(TestData.DistMax.le5Ld)
                    .setHMax(TestData.HMax.le26);
        });

        step("Click on Update Data button", () -> {
            neosPage.updateData();
        });

        step("Check the table contains data", () -> {
            neosPage.checkTableContainsResults();
        });
    }

    @Test
    @DisplayName("The table does not contain the results when specific filters are applied")
    @Tag("major")
    void tableDoesNotContainResultsForSpecificFilters() {

        step("Open NEO Earth Close Approaches page", () -> {
            neosPage.openPage();
        });

        step("Set specific filters", () -> {
            neosPage.setDateRange(TestData.DateRange.recentPast)
                    .setDistMax(TestData.DistMax.le1Ld)
                    .setHMax(TestData.HMax.le26);
        });

        step("Click on Update Data button", () -> {
            neosPage.updateData();
        });

        step("Check the table does not contain data", () -> {
            neosPage.checkTableContainsNoResult();
        });
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
    @Tag("major")
    void columnNamesChangeWhenUnitsChange(String distanceUnit, String velocityUnit) {

        step("Open NEO Earth Close Approaches page", () -> {
            neosPage.openPage();
        });

        step("Set units to Distance, Velocity", () -> {
            neosPage.setUnitToDistance(distanceUnit)
                    .setUnitToVelocity(velocityUnit);
        });

        step("Check that set units are displayed in Distance, Velocity table headers", () -> {
            neosPage.checkDistanceColumnsContainChangedUnits(distanceUnit)
                    .checkVelocityColumnsContainChangedUnits(velocityUnit);
        });
    }
}
