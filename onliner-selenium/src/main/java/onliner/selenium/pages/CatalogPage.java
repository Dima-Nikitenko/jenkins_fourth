package onliner.selenium.pages;

import onliner.selenium.framework.BasePage;
import onliner.selenium.framework.elements.Label;
import org.openqa.selenium.By;

public class CatalogPage extends BasePage {

    private static String txtUniqueElement = String.format("//div[@class = 'catalog-navigation__title'][contains(%s, 'Каталог')]", translateText);
    private String lblMenuLocator = String.format("By.xpath//span[@class= 'catalog-navigation-classifier__item-title']/span[contains(%1$s, '%2$s')]", translateText, "%s");
    private String lblSubMenuLocator = String.format("By.xpath//div[@class = 'catalog-navigation-list__category' and @style = 'display: block;']//div[contains(%1$s, '%2$s')]", translateText, "%s");
    private String lblDropdownMenuLocator = String.format("By.xpath//div[contains(@class, 'aside-item_active')]//span[contains(@class, 'dropdown-title')][contains(%1$s, '%2$s')]", translateText, "%s");

    public CatalogPage() {
        super(By.xpath(txtUniqueElement), "Каталог");
    }

    public void navigateMenu(String menuItem, String subMenuItem, String dropdownMenuItem) {
        Label lblMenu = new Label(lblMenuLocator, menuItem, menuItem);
        Label lblSubMenu = new Label(lblSubMenuLocator, subMenuItem, subMenuItem);
        Label lblDropdownMenu = new Label(lblDropdownMenuLocator, dropdownMenuItem, dropdownMenuItem);
        lblMenu.click();
        lblSubMenu.moveToElement();
        lblDropdownMenu.clickAndWait();
    }
}