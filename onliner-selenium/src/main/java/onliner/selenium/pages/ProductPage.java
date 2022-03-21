package onliner.selenium.pages;

import onliner.selenium.framework.BasePage;
import onliner.selenium.framework.elements.Button;
import onliner.selenium.framework.elements.CheckBox;
import onliner.selenium.framework.elements.DropDown;
import onliner.selenium.framework.elements.TextBox;
import org.openqa.selenium.By;

public class ProductPage extends BasePage {

    private static String txtUniqueElement = String.format("//div[@class = 'schema-header']//h1[contains(%s, 'Телевизоры')]", translateText);
    private Button btnProducer = new Button(By.xpath("//div[@class = 'schema-filter__label']/span[contains(text(), 'Производитель')]/../following-sibling::div//div[@class = 'schema-filter-control__item']"), "Производитель");
    private TextBox txtMaxPrice = new TextBox(By.xpath("//div[@class = 'schema-filter__label']/span[contains(text(), 'Минимальная цена')]/../following-sibling::div//input[contains(@data-bind, 'value: facet.value.to')]"), "Минимальная цена");
    private DropDown ddlMinDiagonal = new DropDown(By.xpath("//div[@class = 'schema-filter__label']/span[contains(text(), 'Диагональ')]/../following-sibling::div//select[contains(@data-bind, 'value: facet.value.from')]"), "Минимальная диагональ");
    private DropDown ddlMaxDiagonal = new DropDown(By.xpath("//div[@class = 'schema-filter__label']/span[contains(text(), 'Диагональ')]/../following-sibling::div//select[contains(@data-bind, 'value: facet.value.to')]"), "Минимальная диагональ");
    private String chkProducerLocator = String.format("By.xpath//div[@class = 'schema-filter-popover__title'][contains(text(), 'Производитель' )]/..//span[@class = 'schema-filter__checkbox-text'][contains(%1$s, '%2$s')]", translateText, "%s");
    private String chkResolutionLocator = String.format("By.xpath//div[@class = 'schema-filter__label']/span[contains(text(), 'Разрешение')]/../following-sibling::div//span[contains(%1$s, '%2$s')]", translateText, "%s");

    public ProductPage() {
        super(By.xpath(txtUniqueElement), "Телевизоры");
    }

    public void filterByParameters(String producer, String maxPrice, String minDiagonal, String maxDiagonal, String resolution) {
        filterByProducer(producer);
        filterByMaxPrice(maxPrice);
        filterByMinDiagonal(minDiagonal);
        filterByMaxDiagonal(maxDiagonal);
        filterByResolution(resolution);
    }

    public void filterByProducer(String producer) {
        CheckBox chkProducer = new CheckBox(chkProducerLocator, producer, producer);
        txtMaxPrice.scrollToElementRaw();
        btnProducer.click();
        chkProducer.click();
    }

    public void filterByMaxPrice(String maxPrice) {
        txtMaxPrice.sendKeys(maxPrice);
    }

    public void filterByMinDiagonal(String minDiagonal) {
        ddlMinDiagonal.selectValue(minDiagonal);
    }

    public void filterByMaxDiagonal(String maxDiagonal) {
        ddlMaxDiagonal.selectValue(maxDiagonal);
    }

    public void filterByResolution(String resolution) {
        CheckBox chkResolution = new CheckBox(chkResolutionLocator, resolution, resolution);
        chkResolution.click();
    }
}