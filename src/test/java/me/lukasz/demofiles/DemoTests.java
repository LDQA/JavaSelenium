package me.lukasz.demofiles;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class DemoTests
{
    private WebDriver webDriver;

    @BeforeEach
    public void init()
    {
        webDriver = new ChromeDriver();
       webDriver.manage().window().maximize();
       webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void test2() throws IOException
    {
        for (Object[] object : data())
        {
            String username = object[0].toString();
            String password = object[1].toString();
            int row = Integer.parseInt(object[2].toString());
            System.out.println("User: " + username + " Pass: " + password + " Row: " + row);
            webDriver.get("http://thedemosite.co.uk/");
            MainPageDemo mainPageDemo = PageFactory.initElements(webDriver, MainPageDemo.class);
            LoginPageDemo loginPageDemo = PageFactory.initElements(webDriver, LoginPageDemo.class);
            CreatePageDemo createPageDemo = PageFactory.initElements(webDriver, CreatePageDemo.class);
            mainPageDemo.createUserPage.click();
            loginPageDemo.username.sendKeys(username);
            loginPageDemo.password.sendKeys(password);
            loginPageDemo.createBtn.click();
            mainPageDemo.loginPage.click();
            createPageDemo.username.sendKeys(username);
            createPageDemo.password.sendKeys(password);
            createPageDemo.loginBtn.click();
            try
            {
                assertEquals("**Successful Login**", createPageDemo.getText());
                writeCellStatus(row, true);
            }
            catch (Exception e)
            {
                writeCellStatus(row, false);
            }
        }
    }

//    @Test
//    public void test()
//    {
//        final String username = "Jeremy";
//        final String password = "YaBoi";
//        webDriver.get("http://thedemosite.co.uk/");
//        MainPageDemo mainPageDemo = PageFactory.initElements(webDriver, MainPageDemo.class);
//        LoginPageDemo loginPageDemo = PageFactory.initElements(webDriver, LoginPageDemo.class);
//        CreatePageDemo createPageDemo = PageFactory.initElements(webDriver, CreatePageDemo.class);
//        mainPageDemo.createUserPage.click();
//        loginPageDemo.username.sendKeys(username);
//        loginPageDemo.password.sendKeys(password);
//        loginPageDemo.createBtn.click();
//        mainPageDemo.loginPage.click();
//        createPageDemo.username.sendKeys(username);
//        createPageDemo.password.sendKeys(password);
//        createPageDemo.loginBtn.click();
//        assertEquals("**Successful Login**", createPageDemo.getText());
//    }

    @AfterEach
    public void down()
    {
        //webDriver.close();
    }

    private Collection<Object[]> data() throws IOException
    {
        FileInputStream file = new FileInputStream("E:\\Frost_Development\\SelProject\\logins.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Object[][] ob = new Object[sheet.getPhysicalNumberOfRows() - 1][4];
        for (int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
            ob[rowNum - 1][0] = sheet.getRow(rowNum).getCell(0).getStringCellValue();
            ob[rowNum - 1][1] = sheet.getRow(rowNum).getCell(1).getStringCellValue();
            ob[rowNum - 1][2] = rowNum;
        }
        workbook.close();
        return Arrays.asList(ob);
    }

    private boolean writeCellStatus(int rownum, boolean bool) throws IOException
    {
        FileInputStream file = new FileInputStream("E:\\Frost_Development\\SelProject\\logins.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(rownum);
        XSSFCell cell = row.getCell(2);
        if(cell == null)
        {
            cell = row.createCell(2);
        }
        cell.setCellValue(bool ? "Successful" : "Unsuccessful");
        FileOutputStream out = new FileOutputStream("E:\\Frost_Development\\SelProject\\logins.xlsx");
        workbook.write(out);
        return true;
    }

}
