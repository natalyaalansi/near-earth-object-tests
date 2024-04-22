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
        @Tag("web"),
        @Tag("sanity"),
})
public class NeosTest extends TestBase {

    NeosPage neosPage = new NeosPage();

    String noDataText = "No data match specified Table Settings",
    defaultNearFuture = "Near future (within 60 days)",
    defaultHLimit = "no H limit",
    defaultNominalDist = "Nominal dist. <= 0.05au";

    @Test
    @DisplayName("Header navigation contains menu and breadcrumbs")
    @Tag("minor")
    void headerNavigationContainsMenuAndBreadcrumbs() {

        step("Open NEO Earth Close Approaches page", () -> {
            neosPage.openPage();
        });

        step("Check \"Home\"s, Right Menu Items, Breadcrumbs", () -> {
            neosPage.checkLeftMenu("Home")
                    .checkRightMenu("About", "Orbits", "Close Approaches", "Impact Risk", "Planetary Defense", "Discovery Statistics", "Tools", "Extras")
                    .checkBreadcrumbs("Home", "Close Approaches", "Neos");
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
            neosPage.checkHeading("NEO Earth Close Approaches")
                    .checkSubheading("Close Approach Data");
        });

        step("Check for a link to a brief video tutorial", () -> {
            neosPage.checkVideoLink("neo_ca_tutorial.html");
        });

        step("Verify the presence of the header of the filter table and data table", () -> {
            neosPage.checkTableSettingsName("Table Settings");
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
            neosPage.checkDefaultDateRangeFilter(defaultNearFuture)
                    .checkDefaultHMaxFilter(defaultHLimit)
                    .checkDefaultDistMaxFilter(defaultNominalDist);
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
            neosPage.setDateRange(TestData.DateRange.RECENT_PAST)
                    .setDistMax(TestData.DistMax.LE1LD)
                    .setHMax(TestData.HMax.LE22);
        });

        step("Revert settings", () -> {
            neosPage.applyDefaultFilters();
        });

        step("Verify filter values are displayed by default after resetting the filters", () -> {
            neosPage.checkDefaultDateRangeFilter(defaultNearFuture)
                    .checkDefaultHMaxFilter( defaultHLimit)
                    .checkDefaultDistMaxFilter(defaultNominalDist);
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
            neosPage.setDateRange(TestData.DateRange.RECENT_PAST)
                    .setDistMax(TestData.DistMax.LE5LD)
                    .setHMax(TestData.HMax.LE26);
        });

        step("Click on Update Data button", () -> {
            neosPage.updateData();
        });

        step("Check the table contains data", () -> {
            neosPage.checkTableContainsResults(noDataText);
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
            neosPage.setDateRange(TestData.DateRange.RECENT_PAST)
                    .setDistMax(TestData.DistMax.LE1LD)
                    .setHMax(TestData.HMax.LE24);
        });

        step("Click on Update Data button", () -> {
            neosPage.updateData();
        });

        step("Check the table does not contain data", () -> {
            neosPage.checkTableContainsNoResult(noDataText);
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
