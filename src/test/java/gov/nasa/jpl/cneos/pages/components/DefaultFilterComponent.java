package gov.nasa.jpl.cneos.pages.components;

import com.codeborne.selenide.SelenideElement;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultFilterComponent {
    public void checkDefaultFilter(SelenideElement element, String defaultValue) {
        String defaultDateRangeValue = element.getSelectedOptionText();
        assertThat(defaultDateRangeValue).isEqualTo(defaultValue);
    }
}
