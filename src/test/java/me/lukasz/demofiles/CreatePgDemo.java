package me.lukasz.demofiles;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePgDemo
{

    @FindBy (xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input")
    WebElement username;

    @FindBy (xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input")
    WebElement password;

    @FindBy (xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")
    WebElement createBtn;

    public void createUser(String user, String pass)
    {
        this.username.sendKeys(user);
        this.password.sendKeys(pass);
        this.createBtn.click();
    }
}
