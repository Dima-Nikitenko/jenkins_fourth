package onliner.cucumber.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import onliner.selenium.framework.Logger;
import onliner.selenium.pages.CatalogPage;
import onliner.selenium.pages.HomePage;
import onliner.selenium.pages.ProductPage;
import onliner.selenium.pages.ProductPageFilterResults;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class CatalogFilteringSteps {

    private HomePage homePage;
    private CatalogPage catalogPage;
    private ProductPage productPage;
    private ProductPageFilterResults productPageFilterResults;
    private static Logger logger = Logger.getInstance();
    private static final HashMap<String, String> filterData = new HashMap<>();

    @Given("Onliner home page is opened")
    public void verifyOnlinerHomePageIsOpened() {
        homePage = new HomePage();
    }

    @When("I navigate {string} section")
    public void navigateSection(String sectionName) {
        homePage.navigateSection(sectionName);
    }

    @And("I navigate menu using {string}, {string} and {string}")
    public void navigateMenu(String menuItem, String subMenuItem, String dropdownMenuItem) {
        catalogPage = new CatalogPage();
        catalogPage.navigateMenu(menuItem, subMenuItem, dropdownMenuItem);
    }

    @And("I filter items by following parameters")
    public void filterItemsByParameters(Map<String, String> filters) {
        productPage = new ProductPage();
        filters.forEach((title, value) -> {
            switch (title.toLowerCase()) {
                case "producer": {
                    productPage.filterByProducer(value);
                    break;
                }
                case "max_price": {
                    productPage.filterByMaxPrice(value);
                    break;
                }
                case "min_diagonal": {
                    productPage.filterByMinDiagonal(value);
                    break;
                }
                case "max_diagonal": {
                    productPage.filterByMaxDiagonal(value);
                    break;
                }
                case "resolution": {
                    productPage.filterByResolution(value);
                    break;
                }
                default: break;
            }
            filterData.put(title.toLowerCase(), value);
        });
    }

    @Then("Following search results displayed on the page are correct")
    public void checkCorrectSearchResults(DataTable table) {
        productPageFilterResults = new ProductPageFilterResults();
        SoftAssert softAssert = new SoftAssert();
        List<String> titles = table.asList();
        ProductPageFilterResults.lblBeautifulScroll.scrollToElementRaw();
        try {
            Assert.assertFalse(ProductPageFilterResults.lblNoSuchProducts.isDisplayed());
        } catch (Throwable exc) {
            logger.info(ProductPageFilterResults.lblNoSuchProducts.getElementName());
            return;
        }
        for (String title: titles) {
                switch (title.toLowerCase()) {
                    case "producer": {
                        softAssert.assertTrue(productPageFilterResults.isCorrectProducer(filterData.get("producer")),
                                "The \"Producer\" parameter does not match the search criteria.");
                        break;
                    }
                    case "max_price": {
                        softAssert.assertTrue(productPageFilterResults.isCorrectPrice(filterData.get("max_price")),
                                "The \"Price\" parameter does not match the search criteria.");
                        break;
                    }
                    case "diagonal": {
                        softAssert.assertTrue(productPageFilterResults.isCorrectDiagonal(filterData.get("min_diagonal"), filterData.get("max_diagonal")),
                                "The \"Diagonal\" parameter does not match the search criteria.");
                        break;
                    }
                    case "resolution": {
                        softAssert.assertTrue(productPageFilterResults.isCorrectResolution(filterData.get("resolution")),
                                "The \"Resolution\" parameter does not match the search criteria.");
                        break;
                    }
                    default: break;
                }
        }
        softAssert.assertAll();
    }
}