package com.framework.testcases;

import com.framework.businesslogic.LoginFlow;
import com.framework.common.BaseTest;
import com.framework.config.GlobalDatas;
import com.framework.listeners.RetryListener;
import com.framework.pages.HomePage;
import com.framework.pages.LoginPage;
import io.qameta.allure.Description;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.*;
import org.testng.xml.dom.ParentSetter;

import javax.swing.text.TabableView;
import java.io.File;
import java.io.IOException;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/11 20:24
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class TestLogin02 extends BaseTest {
    @BeforeMethod
    @Parameters({"browserName"})
    public void setup(String browserName){
        //用例前置
        //1、打开浏览器
         openBrowser(browserName);
        driver.manage().window().maximize();
        //2、进入登录页面
        driver.get(GlobalDatas.INDEX_URL);
        HomePage homePage = new HomePage(driver);
        homePage.enterLoginPage();

    }

    @Test
    public void test_login_success(){
        //用例步骤
        /*driver.findElement(By.xpath("//input[@placeholder='请输入手机号/用户名']")).sendKeys(GlobalDatas.USER_NAME);
        driver.findElement(By.xpath("//input[@placeholder='请输入密码']")).sendKeys(GlobalDatas.USER_PASSWORD);
        driver.findElement(By.xpath("//a[@class='login-button']")).click();*/
        //通过调用页面层已经封装好的操作组成业务逻辑-登录
        //两层po
        /*LoginPage loginPage = new LoginPage(driver);
        loginPage.inputPhone(GlobalDatas.USER_NAME);
        loginPage.inputPassword(GlobalDatas.USER_PASSWORD);
        HomePage homePage = loginPage.clickLogin();*/
        //三层po
        LoginFlow loginFlow = new LoginFlow(driver);
        HomePage homePage = loginFlow.login(GlobalDatas.USER_NAME,GlobalDatas.USER_PASSWORD);
        //用例断言,测试结果是否符合预期
        //1、根据主页的提示【欢迎来到柠檬班】,根据它是否有显示？？
//        myAssertTrue();
        //2、根据主页的用户名
        Assert.assertTrue(homePage.isNicknameExist());
        takeScreenshot("登录");
        homePage.quitLogin();


//        driver.quit();
        driver.close();
    }
    @Test(priority = 1,dataProvider = "getLoginFailureDatas")
    @Description("手机号码10位、手机号码12位、手机号码未注册")
    public void test_login_failure(String phone,String password){
        LoginFlow loginFlow = new LoginFlow(driver);
        HomePage homePage = loginFlow.login(phone,password);
        LoginPage loginPage=new LoginPage(driver);
        String actual = loginPage.geterrorAccountPwdtext();
        myAssertEqual(actual,"！！！！账号或密码不正确","账号或密码不正确的提示");
    };
    @DataProvider
    public Object[][] getLoginFailureDatas(){
        Object[][] datas={
            {"1334545323","123456"},
            {"133454532331","123456"},
//            {"13345453234","123456"},
//            {"13345453235","123456"}
        };
        return datas;
    };

    @AfterMethod
    public void teardown(){
        //用例后置

        //关闭浏览器
        driver.quit();
    }
    public void takeScreenshot(String fileName){
        TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
        File srcFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
        File desFile=new File(System.getProperty("user.dir")+"\\screenShot\\"+fileName+".png");
        try {
            FileUtils.copyFile(srcFile,desFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    };
}
