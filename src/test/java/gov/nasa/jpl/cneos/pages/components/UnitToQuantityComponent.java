package gov.nasa.jpl.cneos.pages.components;

import com.codeborne.selenide.SelenideElement;

public class UnitToQuantityComponent {
    public void setUnitToQuantity(SelenideElement quantity, String unit) {
        quantity.selectOption(unit);
    }
}
