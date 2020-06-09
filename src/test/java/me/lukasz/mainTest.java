package me.lukasz;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class mainTest
{

    WebDriver webDriver;

    @BeforeEach
    public void init()
    {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @Test
    public void test2() throws InterruptedException
    {

    }

    @AfterEach
    public void down()
    {
        webDriver.close();
    }

}
