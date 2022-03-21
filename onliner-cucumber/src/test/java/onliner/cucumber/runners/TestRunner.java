package onliner.cucumber.runners;

import onliner.cucumber.hooks.Hooks;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/onliner/cucumber/features",
        glue= { "onliner.cucumber.stepdefinitions", "onliner.cucumber.hooks" },
        plugin = { "html:test-reports/cucumber-reports/cucumber.html",
                   "json:test-reports/cucumber-reports/cucumber.json",
                   "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" })

public class TestRunner extends Hooks {}
