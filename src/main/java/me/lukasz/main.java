package me.lukasz;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.java2d.windows.GDIRenderer;

public class main
{

    WebDriver webDriver;

    @BeforeEach
    public void init()
    {
        webDriver = new ChromeDriver();
    }

    @Test
    public void test()
    {
        webDriver.get("http://www.bing.co.uk");
    }

    @AfterEach
    public void down()
    {
        webDriver.close();
    }

}
