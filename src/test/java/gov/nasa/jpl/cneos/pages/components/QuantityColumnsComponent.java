package gov.nasa.jpl.cneos.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;

public class QuantityColumnsComponent {
    public void checkQuantityColumnsContainChangedUnits(SelenideElement element1, SelenideElement element2, String unit) {
        element1.shouldHave(text(unit));
        element2.shouldHave(text(unit));
    }
}
