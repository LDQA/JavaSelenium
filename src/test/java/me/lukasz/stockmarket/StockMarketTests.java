package me.lukasz.stockmarket;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class StockMarketTests
{

    private WebDriver webDriver;
    private static ExtentReports extentReports;
    private ExtentTest test;

    @Before
    @SuppressWarnings("depracation")
    public static void setup()
    {
        extentReports = new ExtentReports();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/html/extentReport.html");
        htmlReporter.config().setAutoCreateRelativePathMedia(true);
        extentReports.attachReporter(htmlReporter);
    }

    @BeforeClass
    public void init()
    {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void test2()
    {
        webDriver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100");
        StockWebsitePage stockWebsitePage = PageFactory.initElements(webDriver, StockWebsitePage.class);
        stockWebsitePage.cookie.click();
        stockWebsitePage.risersBtn.click();
        System.out.println("Biggest Raisers: " + stockWebsitePage.getText());
        stockWebsitePage.fallersBtn.click();
        System.out.println("Biggest Fallers: " + stockWebsitePage.getText());
    }

    @AfterClass
    public void down()
    {
       //webDriver.close();
    }

    @After
    public static void afterFinish()
    {
        extentReports.flush();
    }

}