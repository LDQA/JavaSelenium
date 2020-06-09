package me.lukasz.shopfiles;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.fail;

public class ShopTests
{

    private WebDriver webDriver;
    private static ExtentReports extentReports;
    private ExtentTest test;

    @BeforeAll
    @SuppressWarnings("depracation")
    public static void setup()
    {
        extentReports = new ExtentReports();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/html/extentReport.html");
        htmlReporter.config().setAutoCreateRelativePathMedia(true);
        extentReports.attachReporter(htmlReporter);
    }

    @BeforeEach
    public void init()
    {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void test2()
    {
        String email = UUID.randomUUID().toString().substring(0, 8) + "@gd.dw.com";
        webDriver.get("http://automationpractice.com/index.php");

        this.test = extentReports.createTest("test2", "Testing ");
        try
        {
            ElementBuilder builder = new ElementBuilder(webDriver);
            //Finds a Dress
            builder.typeKeysxPath("/html/body/div/div[1]/header/div[3]/div/div/div[2]/form/input[4]", "Dress" + Keys.ENTER);
            //Clicks on the Dress and Adds to checkout
            builder.clickxPath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]").clickxPath("//*[@id=\"add_to_cart\"]/button/span").clickxPath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span");
            //Checkout
            builder.clickxPath("//*[@id=\"center_column\"]/p[2]/a[1]/span");
            //Create Account
            builder.typeKeysxPath("//*[@id=\"email_create\"]", email + Keys.ENTER);
            //
            SignInPage signInPage = PageFactory.initElements(webDriver, SignInPage.class);
            signInPage.title.click();
            signInPage.CfirstName.sendKeys("Luk");
            signInPage.ClastName.sendKeys("D");
            signInPage.password.sendKeys("YazBoi");
            signInPage.day.sendKeys("10" + Keys.ENTER);
            signInPage.month.sendKeys("Jan" + Keys.ENTER);
            signInPage.year.sendKeys("1998" + Keys.ENTER);
            signInPage.firstName.sendKeys("Luk");
            signInPage.lastName.sendKeys("D");
            signInPage.address.sendKeys("35 My House Rd");
            signInPage.city.sendKeys("Florida");
            signInPage.state.sendKeys("Florida" + Keys.ENTER);
            signInPage.postcode.sendKeys("15485");
            signInPage.mobilePhone.sendKeys("12345612315");
            signInPage.aliasAddress.sendKeys("Home Boi");
            signInPage.regBtn.click();
            webDriver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button/span")).click();
            webDriver.findElement(By.id("cgv")).click();
            webDriver.findElement(By.xpath("//*[@id=\"form\"]/p/button/span")).click();
            webDriver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")).click();
            webDriver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span")).click();
            test.pass("Correctly Found all Elements and Executed them!");
        } catch (Exception e)
        {
            e.printStackTrace();
            test.fail("An error has occurred while finding an element.");
            fail();
        }
    }

    @AfterEach
    public void down()
    {
        webDriver.close();
    }

    @AfterAll
    public static void afterFinish()
    {
        extentReports.flush();
    }

}
