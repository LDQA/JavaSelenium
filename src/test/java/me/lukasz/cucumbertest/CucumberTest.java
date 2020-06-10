package me.lukasz.cucumbertest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "E:\\Frost_Development\\SelProject\\src\\test\\java\\resources/DemoSite.feature" })
public class CucumberTest {

}
