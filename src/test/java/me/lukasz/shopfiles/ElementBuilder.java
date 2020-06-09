package me.lukasz.shopfiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementBuilder
{

    private WebDriver webDriver;

    public ElementBuilder(WebDriver webDriver)
    {
        this.webDriver = webDriver;
    }

    public ElementBuilder clickID(String id)
    {
        webDriver.findElement(By.id(id)).click();
        return this;
    }

    public ElementBuilder clickxPath(String xpath)
    {
        webDriver.findElement(By.xpath(xpath)).click();
        return this;
    }


    public ElementBuilder typeKeysID(String id, String keys)
    {
        webDriver.findElement(By.id(id)).sendKeys(keys);
        return this;
    }

    public ElementBuilder typeKeysxPath(String xpath, String keys)
    {
        webDriver.findElement(By.xpath(xpath)).sendKeys(keys);
        return this;
    }

}
