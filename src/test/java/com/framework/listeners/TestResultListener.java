package com.framework.listeners;

import com.framework.common.BaseTest;
import com.framework.testcases.TestLogin02;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

/**
 * @Project: day10
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright: java32期 T0123
 * @Author: tian
 * @Create: 2022-01-08 18:31
 * @Desc：
 **/
public class TestResultListener implements IHookable {

    @Override
    public void run ( IHookCallBack iHookCallBack, ITestResult iTestResult ) {
//        System.out.println("这是替换的");
        iHookCallBack.runTestMethod(iTestResult);
        if (iTestResult.getThrowable() != null){
            System.out.println("出问题了");
            BaseTest BaseTest= (BaseTest) iTestResult.getInstance();
            takeScreenshot(BaseTest.driver,"testLogin"+System.currentTimeMillis());

        }
    }
    public void takeScreenshot ( WebDriver driver, String fileName ){
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
