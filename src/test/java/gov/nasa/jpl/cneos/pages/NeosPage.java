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

    public NeosPage checkLeftMenu(String itemText) {
        leftMenuItem.shouldHave(text(itemText));
        return this;
    }

    public NeosPage checkRightMenu(String... itemTexts) {
        rightMenuItems.shouldHave(texts(itemTexts));
        return this;
    }

    public NeosPage checkBreadcrumbs(String... itemTexts) {
        breadcrumbs.shouldHave(texts(itemTexts));
        return this;
    }

    public NeosPage checkHeading(String headingText) {
        heading.shouldHave(text(headingText));
        return this;
    }

    public NeosPage checkSubheading(String subheadingText) {
        subheading.shouldHave(text(subheadingText));
        return this;
    }

    public NeosPage checkVideoLink(String link) {
        briefTutorial.shouldHave(href(link));
        return this;
    }

    public NeosPage checkTableSettingsName(String tableName) {
        tableFilters.shouldHave(text(tableName));
        return this;
    }

    public NeosPage checkTableGeneration() {
        closeApproachTable.shouldBe(visible);
        return this;
    }

    public NeosPage checkDefaultDateRangeFilter(String defaultFilterName) {
        defaultFilterComponent.checkDefaultFilter(dateRange, defaultFilterName);
        return this;
    }

    public NeosPage checkDefaultDistMaxFilter(String defaultFilterName) {
        defaultFilterComponent.checkDefaultFilter(distMax, defaultFilterName);
        return this;
    }

    public NeosPage checkDefaultHMaxFilter(String defaultFilterName) {
        defaultFilterComponent.checkDefaultFilter(hMax, defaultFilterName);
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

    public NeosPage checkTableContainsResults(String noDataText) {
        resulTableRows.shouldHave(sizeGreaterThan(0));
        resulTableRows.first().shouldNotHave(text(noDataText));
        return this;
    }

    public NeosPage checkTableContainsNoResult(String noDataText) {
        resulTableRows.shouldHave(size(1));
        resulTableRows.shouldHave(texts(noDataText));
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
