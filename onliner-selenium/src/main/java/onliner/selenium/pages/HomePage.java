package onliner.selenium.pages;

import onliner.selenium.framework.BasePage;
import onliner.selenium.framework.elements.Label;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private static String lblUniqueElement = String.format("//header/h2/a[contains(%s, 'Каталог')]", translateText);
    private String lblSectionLocator = String.format("By.xpath//header/h2/a[contains(%1$s, '%2$s')]", translateText, "%s");


    public HomePage() {
        super(By.xpath(lblUniqueElement), "Onliner");
    }

    public void navigateSection(String sectionName) {
        Label lblSection = new Label(lblSectionLocator, sectionName, sectionName);
        lblSection.clickAndWait();
    }
}
