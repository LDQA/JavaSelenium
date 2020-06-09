package me.lukasz.demofiles;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class DemoTests
{

    WebDriver webDriver;

    @BeforeEach
    public void init()
    {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @Test
    public void test()
    {
        final String username = "Jeremy";
        final String password = "YaBoi";
        webDriver.get("http://thedemosite.co.uk/");
        MainPageDemo mainPageDemo = PageFactory.initElements(webDriver, MainPageDemo.class);
        LoginPageDemo loginPageDemo = PageFactory.initElements(webDriver, LoginPageDemo.class);
        CreatePageDemo createPageDemo = PageFactory.initElements(webDriver, CreatePageDemo.class);
        mainPageDemo.createUserPage.click();
        loginPageDemo.username.sendKeys(username);
        loginPageDemo.password.sendKeys(password);
        loginPageDemo.createBtn.click();
        mainPageDemo.loginPage.click();
        createPageDemo.username.sendKeys(username);
        createPageDemo.password.sendKeys(password);
        createPageDemo.loginBtn.click();
        assertEquals("**Successful Login**", createPageDemo.getText());
    }

    @AfterEach
    public void down()
    {
        webDriver.close();
    }

}
