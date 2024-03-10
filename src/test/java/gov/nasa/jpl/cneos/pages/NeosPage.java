package gov.nasa.jpl.cneos.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import gov.nasa.jpl.cneos.pages.components.DefaultFilterComponent;
import gov.nasa.jpl.cneos.pages.components.QuantityColumnsComponent;
import gov.nasa.jpl.cneos.pages.components.UnitToQuantityComponent;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class NeosPage {

    private SelenideElement leftMenuItem = $(".name"),
            heading = $("h1"),
            subheading = $("h3"), video = $("h4 a"),
            briefTutorial = $("h4 a"),
            tableFilters = $("#caTableFilters"),
            closeApproachTable = $("#closeApproachTable_wrapper"),
            dateRange = $("[ng-model*='dateRange']"),
            distMax = $("[ng-model*='distMax']"),
            hMax = $("[ng-model*='HMax']"),
            updateButton = $("[class*='ca-button']"),
            defaultSettingsButton = $(byText("default settings")),
            distanceUnits = $("#du"),
            velocityUnits = $("#vu"),
            caDistanceNominal = $("#closeApproachTable thead tr th:nth-child(4)"),
            caDistanceMinimum = $("#closeApproachTable thead tr th:nth-child(5)"),
            vRelative = $("#closeApproachTable thead tr th:nth-child(6)"),
            vInfinity = $("#closeApproachTable thead tr th:nth-child(7)");


    private ElementsCollection rightMenuItems = $$(".right li[class*='has-dropdown']"),
            breadcrumbs = $$(".breadcrumbs li"),
            resulTableRows = $$("#closeApproachTable tbody tr");

    DefaultFilterComponent defaultFilterComponent = new DefaultFilterComponent();
    UnitToQuantityComponent unitToQuantityComponent = new UnitToQuantityComponent();
    QuantityColumnsComponent quantityColumnsComponent = new QuantityColumnsComponent();

    public NeosPage openPage() {
        open("/ca/");
        return this;
    }

    public NeosPage checkLeftMenu() {
        leftMenuItem.shouldHave(text("Home"));
        return this;
    }

    public NeosPage checkRightMenu() {
        rightMenuItems.shouldHave(texts("About", "Orbits", "Close Approaches", "Impact Risk", "Planetary Defense", "Discovery Statistics", "Tools", "Extras"));
        return this;
    }

    public NeosPage checkBreadcrumbs() {
        breadcrumbs.shouldHave(texts("Home", "Close Approaches", "Neos"));
        return this;
    }

    public NeosPage checkHeading() {
        heading.shouldHave(text("NEO Earth Close Approaches"));
        return this;
    }

    public NeosPage checkSubheading() {
        subheading.shouldHave(text("Close Approach Data"));
        return this;
    }

    public NeosPage checkVideoLink() {
        briefTutorial.shouldHave(href("neo_ca_tutorial.html"));
        return this;
    }

    public NeosPage checkTableSettingsName() {
        tableFilters.shouldHave(text("Table Settings"));
        return this;
    }

    public NeosPage checkTableGeneration() {
        closeApproachTable.shouldBe(visible);
        return this;
    }

    public NeosPage checkDefaultDateRangeFilter() {
        defaultFilterComponent.checkDefaultFilter(dateRange, "Near future (within 60 days)");
        return this;
    }

    public NeosPage checkDefaultDistMaxFilter() {
        defaultFilterComponent.checkDefaultFilter(distMax, "Nominal dist. <= 0.05au");
        return this;
    }

    public NeosPage checkDefaultHMaxFilter() {
        defaultFilterComponent.checkDefaultFilter(hMax, "no H limit");
        return this;
    }

    public NeosPage checkDisabledUpdateButton() {
        updateButton.shouldBe(disabled);
        return this;
    }

    public NeosPage setDateRange(String option) {
        dateRange.selectOption(option);
        return this;
    }

    public NeosPage setDistMax(String option) {
        distMax.selectOption(option);
        return this;
    }

    public NeosPage setHMax(String option) {
        hMax.selectOption(option);
        return this;
    }

    public NeosPage applyDefaultFilters() {
        defaultSettingsButton.click();
        return this;
    }

    public NeosPage updateData() {
        updateButton.click();
        return this;
    }

    public NeosPage checkTableContainsResults() {
        resulTableRows.shouldHave(sizeGreaterThan(0));
        resulTableRows.first().shouldNotHave(text("No data match specified Table Settings"));
        return this;
    }

    public NeosPage checkTableContainsNoResult() {
        resulTableRows.shouldHave(size(1));
        resulTableRows.shouldHave(texts("No data match specified Table Settings"));
        return this;
    }

    public NeosPage setUnitToDistance(String unit) {
        unitToQuantityComponent.setUnitToQuantity(distanceUnits, unit);
        return this;
    }

    public NeosPage setUnitToVelocity(String unit) {
        unitToQuantityComponent.setUnitToQuantity(velocityUnits, unit);
        return this;
    }

    public NeosPage checkDistanceColumnsContainChangedUnits(String unit) {
        quantityColumnsComponent.checkQuantityColumnsContainChangedUnits(caDistanceNominal, caDistanceMinimum, unit);
        return this;
    }

    public NeosPage checkVelocityColumnsContainChangedUnits(String unit) {
        quantityColumnsComponent.checkQuantityColumnsContainChangedUnits(vRelative, vInfinity, unit);
        return this;
    }
}
