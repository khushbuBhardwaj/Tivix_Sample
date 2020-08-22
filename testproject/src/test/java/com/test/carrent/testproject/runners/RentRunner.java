package com.test.carrent.testproject.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@SuppressWarnings("deprecation")
@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/featurefiles", 
		glue = {"com.test.carrent.testproject.stepdefinations"},
		plugin = {"pretty", "html:target/cucumber-reports",
			     "json:target/cucumber.json",
			     "rerun:target/rerun.txt"},
		 monochrome = true)
public class RentRunner {
}
