package apitest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/main/resources/features",
        plugin ={"json:target/jsonReports/cucumber-report.json",
        "pretty",
        "html:target/cucumber-reports/Cucumber.html",
        "junit:target/cucumber-reports/Cucumber.xml",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        glue= {"neuroNation/apitest/stepDefinitions"},
        tags = "@Regression",
        monochrome = true)
public class TestRunner {

}
