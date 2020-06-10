package me.lukasz.cucumbertest2;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import me.lukasz.demofiles.CreatePgDemo;
import me.lukasz.demofiles.LoginPgDemo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Task5StepsTests
{

    private WebDriver webDriver;

    @Before
    public void init()
    {
        ChromeOptions opts = new ChromeOptions();
        opts.setHeadless(true);
        this.webDriver = new ChromeDriver(opts);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown()
    {
        this.webDriver.quit();
    }

    @Given("^the correct web address$")
    public void the_correct_web_address() throws Throwable {
        webDriver.get("http://www.practiceselenium.com/welcome.html");
    }

    @When("^I navigate to the 'Menu' page$")
    public void i_navigate_to_the_Menu_page() throws Throwable {
        webDriver.findElement(By.xpath("//*[@id=\"wsb-nav-00000000-0000-0000-0000-000450914915\"]/ul/li[3]/a")).click();
    }

    @Then("^I can browse a list of the available products\\.$")
    public void i_can_browse_a_list_of_the_available_products() throws Throwable {
        String loc = webDriver.findElement(By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000450914921\"]/div/h1")).getText();
    }

    @Given("^the correct web address for checkout$")
    public void the_correct_web_address_for_checkout() throws Throwable {
        webDriver.get("http://www.practiceselenium.com/menu.html");
    }

    @When("^I click the checkout button$")
    public void i_click_the_checkout_button() throws Throwable {
        webDriver.findElement(By.xpath("//*[@id=\"wsb-button-00000000-0000-0000-0000-000451955160\"]/span")).click();
    }

    @Then("^I am taken to the checkout page$")
    public void i_am_taken_to_the_checkout_page() throws Throwable {
        String url = webDriver.getCurrentUrl();
        assertTrue(url.contains("check-out"));
    }


}
