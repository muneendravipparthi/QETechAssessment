package com.asses.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
    
@CucumberOptions(tags = "", features = {"src/test/java/Features/UISpec.feature"}, glue = {"com.asses.definitions"},
                 plugin = {})
public class TestsRunner extends AbstractTestNGCucumberTests {

}
