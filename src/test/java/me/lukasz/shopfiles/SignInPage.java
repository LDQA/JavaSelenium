package me.lukasz.shopfiles;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage
{

    @FindBy (id = "id_gender1")
    WebElement title;

    @FindBy (id = "customer_firstname")
    WebElement CfirstName;

    @FindBy (id = "customer_lastname")
    WebElement ClastName;

    @FindBy (id = "passwd")
    WebElement password;

    @FindBy (id = "days")
    WebElement day;

    @FindBy (id = "months")
    WebElement month;

    @FindBy (id = "years")
    WebElement year;

    @FindBy (id = "firstname")
    WebElement firstName;

    @FindBy (id = "lastname")
    WebElement lastName;

    @FindBy (id = "address1")
    WebElement address;

    @FindBy (id = "city")
    WebElement city;

    @FindBy (id = "id_state")
    WebElement state;

    @FindBy (id = "postcode")
    WebElement postcode;

    @FindBy (id = "phone_mobile")
    WebElement mobilePhone;

    @FindBy (id = "alias")
    WebElement aliasAddress;

    @FindBy (id = "submitAccount")
    WebElement regBtn;

}
