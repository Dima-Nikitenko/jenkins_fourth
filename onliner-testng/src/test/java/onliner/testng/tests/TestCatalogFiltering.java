package onliner.testng.tests;

import onliner.selenium.framework.BaseTest;
import onliner.selenium.pages.CatalogPage;
import onliner.selenium.pages.HomePage;
import onliner.selenium.pages.ProductPage;
import onliner.selenium.pages.ProductPageFilterResults;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestCatalogFiltering extends BaseTest {

    @Parameters({ "Producer", "MaxPrice", "MinDiagonal", "MaxDiagonal", "Resolution" }) // specified in the testng.xml file
    @Test
    void testCatalogFiltering(String producer, String maxPrice, String minDiagonal, String maxDiagonal, String resolution) {
        HomePage homePage = new HomePage();
        homePage.navigateSection("Каталог");
        CatalogPage catalogPage = new CatalogPage();
        catalogPage.navigateMenu("Электроника", "Телевидение и видео", "Телевизоры");
        ProductPage productPage = new ProductPage();
        productPage.filterByParameters(producer, maxPrice, minDiagonal, maxDiagonal, resolution);
        ProductPageFilterResults productPageFilterResults = new ProductPageFilterResults();
        productPageFilterResults.checkCorrectSearchResults(producer, maxPrice, minDiagonal, maxDiagonal, resolution);
    }
}