package me.lukasz.demofiles;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class DemoTests
{
    private WebDriver webDriver;
    private static ExtentReports extentReports;
    private ExtentTest test;

    @Before
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
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void test2() throws IOException
    {
        this.test = extentReports.createTest("test2", "Testing ");
        for (Object[] object : data())
        {
            String username = object[0].toString();
            String password = object[1].toString();
            int row = Integer.parseInt(object[2].toString());
            System.out.println("User: " + username + " Pass: " + password + " Row: " + row);
            webDriver.get("http://thedemosite.co.uk/");
            CreatePgDemo loginPageDemo = PageFactory.initElements(webDriver, CreatePgDemo.class);
            LoginPgDemo createPageDemo = PageFactory.initElements(webDriver, LoginPgDemo.class);
            webDriver.get("http://thedemosite.co.uk/addauser.php");
            loginPageDemo.username.sendKeys(username);
            loginPageDemo.password.sendKeys(password);
            loginPageDemo.createBtn.click();
            webDriver.get("http://thedemosite.co.uk/login.php");
            createPageDemo.username.sendKeys(username);
            createPageDemo.password.sendKeys(password);
            createPageDemo.loginBtn.click();

            TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
            File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            final String scrShotPath = "test-output/screenshots/test.png";
            File targetFile = new File(scrShotPath);
            Files.copy(srcFile, targetFile);

            try
            {
                assertEquals("**Successful Login**", createPageDemo.getText());
                writeCellStatus(row, true);
                test.pass("All Elements were Correctly Executed!").addScreenCaptureFromPath(scrShotPath);
            } catch (Exception e)
            {
                writeCellStatus(row, false);
                test.fail("Error has occured!").addScreenCaptureFromPath(scrShotPath);;
            }
        }
    }

    @AfterClass
    public void down()
    {
        webDriver.close();
    }

    @After
    public static void afterFinish()
    {
        extentReports.flush();
    }

    private Collection<Object[]> data() throws IOException
    {
        FileInputStream file = new FileInputStream("files/logins.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Object[][] ob = new Object[sheet.getPhysicalNumberOfRows() - 1][4];
        for (int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++)
        {
            ob[rowNum - 1][0] = sheet.getRow(rowNum).getCell(0).getStringCellValue();
            ob[rowNum - 1][1] = sheet.getRow(rowNum).getCell(1).getStringCellValue();
            ob[rowNum - 1][2] = rowNum;
        }
        workbook.close();
        return Arrays.asList(ob);
    }

    private boolean writeCellStatus(int rownum, boolean bool) throws IOException
    {
        FileInputStream file = new FileInputStream("files/logins.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(rownum);
        XSSFCell cell = row.getCell(2);
        if (cell == null)
        {
            cell = row.createCell(2);
        }
        cell.setCellValue(bool ? "Successful" : "Unsuccessful");
        FileOutputStream out = new FileOutputStream("files/logins.xlsx");
        workbook.write(out);
        return true;
    }

}
