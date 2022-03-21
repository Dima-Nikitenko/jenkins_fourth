package onliner.selenium.pages;

import onliner.selenium.framework.BasePage;
import onliner.selenium.framework.Logger;
import onliner.selenium.framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class ProductPageFilterResults extends BasePage {

    private static String txtUniqueElement = String.format("//div[@class = 'schema-header']//h1[contains(%s, 'Телевизоры')]", translateText);

    private Label lblItemTitle = new Label(By.xpath("//div[@id = 'schema-products']//div[@class = 'schema-product__title']//span"), "Название продукта");
    private Label lblItemPrice = new Label(By.xpath("//div[@id = 'schema-products']//div[@class = 'schema-product__price']//span[contains(@data-bind, 'price')]"), "Цена продукта");
    private Label lblItemDescription = new Label(By.xpath("//div[@id = 'schema-products']//div[@class = 'schema-product__description']//span"), "Описание продукта");
    public static Label lblBeautifulScroll = new Label(By.id("schema-tags"), "Для красивого скролла");
    public static Label lblNoSuchProducts = new Label(By.xpath(String.format("//div[(@class = 'schema-products__message') and contains(%s, 'Упс! У нас нет таких товаров')]", translateText)), "No products that meet the search criteria (Упс! У нас нет таких товаров, попробуйте изменить условия поиска).");

    private boolean isCorrectParameter;
    private String getTextFromParameter;
    private List<WebElement> itemElement;
    private ArrayList<WebElement> tags = new ArrayList<>();
    private static Logger logger = Logger.getInstance();

    public ProductPageFilterResults() {
        super(By.xpath(txtUniqueElement), "Отфильтрованные телевизоры");
    }

    public void checkCorrectSearchResults(String producer, String maxPrice, String minDiagonal, String maxDiagonal, String resolution) {
        SoftAssert softAssert = new SoftAssert();
        lblBeautifulScroll.scrollToElementRaw();
        try {
            Assert.assertFalse(lblNoSuchProducts.isDisplayed());
        } catch (Throwable exc) {
            logger.info("There are no products that meet the criteria.");
            return;
        }
            softAssert.assertTrue(isCorrectProducer(producer),"The \"Producer\" parameter does not match the search criteria.");
            softAssert.assertTrue(isCorrectPrice(maxPrice),"The \"Price\" parameter does not match the search criteria.");
            softAssert.assertTrue(isCorrectDiagonal(minDiagonal, maxDiagonal),"The \"Diagonal\" parameter does not match the search criteria.");
            softAssert.assertTrue(isCorrectResolution(resolution),"The \"Resolution\" parameter does not match the search criteria.");
            softAssert.assertAll();
    }

    public boolean isCorrectProducer(String producer) {
        itemElement = lblItemTitle.findListOfElements();
        tags.addAll(itemElement);

        for (WebElement tag : tags) {
            getTextFromParameter = tag.getText();
            isCorrectParameter = getTextFromParameter.contains(producer);
            if(!isCorrectParameter) {
                tags.clear();
                return false;
            }
        }
        tags.clear();
        return true;
    }

    public boolean isCorrectPrice(String maxPrice) {
        String isolatedPrice;
        String priceWithDot;
        double priceToDouble;

        itemElement = lblItemPrice.findListOfElements();
        tags.addAll(itemElement);

        for (WebElement tag : tags) {
            getTextFromParameter = tag.getText();
            isolatedPrice = getTextFromParameter.substring(0, tag.getText().length()-3);
            priceWithDot = isolatedPrice.replace(',', '.');
            priceToDouble = Double.parseDouble(priceWithDot);
            isCorrectParameter = priceToDouble < Integer.parseInt(maxPrice);
            if(!isCorrectParameter) {
                tags.clear();
                return false;
            }
        }
        tags.clear();
        return true;
    }

    public boolean isCorrectDiagonal(String minDiagonal, String maxDiagonal) {
        String soughtDiagonal;
        double soughtDiagonalToDouble;
        double minDiagonalToDouble = Double.parseDouble(minDiagonal.replace("\"", ""));
        double maxDiagonalToDouble = Double.parseDouble(maxDiagonal.replace("\"", ""));

        itemElement = lblItemDescription.findListOfElements();
        tags.addAll(itemElement);

        for (WebElement tag : tags) {
            getTextFromParameter = tag.getText();
            soughtDiagonal = getTextFromParameter.substring(0, getTextFromParameter.indexOf("\""));
            soughtDiagonalToDouble = Double.parseDouble(soughtDiagonal);
            isCorrectParameter = soughtDiagonalToDouble >= minDiagonalToDouble & soughtDiagonalToDouble <= maxDiagonalToDouble;
            if(!isCorrectParameter) {
                tags.clear();
                return false;
            }
        }
        tags.clear();
        return true;
    }

    public boolean isCorrectResolution(String resolution) {
        itemElement = lblItemDescription.findListOfElements();
        tags.addAll(itemElement);

        for (WebElement tag : tags) {
            getTextFromParameter = tag.getText();
            isCorrectParameter = getTextFromParameter.contains(resolution);
            if(!isCorrectParameter) {
                tags.clear();
                return false;
            }
        }
        tags.clear();
        return true;
    }
}
