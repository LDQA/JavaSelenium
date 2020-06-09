package me.lukasz.demofiles;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePageDemo
{

    @FindBy (xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input")
    WebElement username;

    @FindBy (xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input")
    WebElement password;

    @FindBy (xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")
    WebElement loginBtn;

    @FindBy (xpath = "/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")
    WebElement text;

    public String getText()
    {
        return text.getText();
    }

}
