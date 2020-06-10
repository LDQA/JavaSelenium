package me.lukasz.cucumbertest;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import me.lukasz.demofiles.LoginPgDemo;
import me.lukasz.demofiles.CreatePgDemo;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class TestDemoCucumber
{

    private RemoteWebDriver webDriver;
    private CreatePgDemo loginPageDemo;
    private LoginPgDemo createPageDemo;

    @Before
    public void init()
    {
        ChromeOptions opts = new ChromeOptions();
        opts.setHeadless(true);
        this.webDriver = new ChromeDriver(opts);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        this.loginPageDemo = PageFactory.initElements(this.webDriver, CreatePgDemo.class);
        this.createPageDemo = PageFactory.initElements(this.webDriver, LoginPgDemo.class);
    }

    @After
    public void tearDown()
    {
        this.webDriver.quit();
    }

    @Given("^I have created an account with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_have_created_an_account_with_and(String username, String password) throws Throwable
    {
        webDriver.get("http://thedemosite.co.uk/addauser.php");
        loginPageDemo.createUser(username, password);
    }

    @Given("^I am on the login page$")
    public void i_am_on_the_login_page() throws Throwable
    {
        webDriver.get("http://thedemosite.co.uk/login.php");
    }

    @When("^I enter the \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_enter_the_and(String username, String password) throws Throwable
    {
        createPageDemo.loginToSystem(username, password);
    }

    @Then("^I verify the login \"([^\"]*)\"$")
    public void i_verify_the_login(String status) throws Throwable
    {
        assertTrue(this.createPageDemo.getText().contains(status));
    }

}
