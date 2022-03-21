package onliner.cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import onliner.selenium.framework.Browser;
import onliner.selenium.framework.Logger;
import onliner.selenium.framework.utils.FileUtils;

public class Hooks {

    private static Logger logger = Logger.getInstance();

    @Before
    public void setup(Scenario scenario) {
        FileUtils.clearDownloadDir();
        logger.logTestStart(scenario.getName());
        Browser.getInstance().navigateUrl(Browser.urlKey);
        logger.info(String.format(Logger.getLogProperty("locale.navigate.url"), Browser.urlKey));
    }

    @After
    public void teardown(Scenario scenario) {
        Browser.getInstance().closeAndQuit();
        logger.logEndTest(scenario.getName());
    }
}
