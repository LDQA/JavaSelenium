package me.lukasz.stockmarket;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StockWebsitePage
{

    @FindBy (xpath = "//*[@id=\"cookie-policy-overlay\"]/div/a[2]")
    WebElement cookie;

    @FindBy (xpath = "//*[@id=\"view-constituents\"]/ul/li[2]")
    WebElement risersBtn;

    @FindBy (xpath = "//*[@id=\"content_div_40583\"]/ul/li[3]")
    WebElement fallersBtn;

    @FindBy (xpath = "/html/body/main/div/div/div[3]/div[4]/div[1]/div[2]/table/tbody/tr[1]")
    WebElement firstRow;

    public String getText()
    {
        return firstRow.getAttribute("id").replace("ls-row", "").replace("-L", "");
    }

}
