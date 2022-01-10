package com.test.day08;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/6 20:40
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class Homework {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //启动被测的App
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","127.0.0.1:62001");
        caps.setCapability("platformName","Android");
        caps.setCapability("appPackage","com.lemon.lemonban");
        caps.setCapability("appActivity",".activity.WelcomeActivity");
        //caps.setCapability("noReset",true);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url,caps);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Thread.sleep(4000);
        //点击【柠檬社区】
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"柠檬社区\")")).click();
        Thread.sleep(3000);
        driver.context("WEBVIEW_com.lemon.lemonban");
        //点击【注册】
        driver.findElement(By.xpath("//a[@href='http://testingpai.com/register']")).click();
        //点击【登录】
        driver.findElement(By.xpath("//a[@href='http://testingpai.com/login']")).click();
        //输入手机号+密码
        driver.findElement(By.id("nameOrEmail")).sendKeys("shakebabe");
        driver.findElement(By.id("userPassword")).sendKeys("lemon123456");
        driver.findElement(By.id("verifyLogin")).click();
        Thread.sleep(3000);
        driver.get("http://testingpai.com/article/1601464528256");
        //点击详细信息
        driver.findElement(By.xpath("//details[@class='details articleMenuBtn']")).click();
        //点击回帖
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("details-menu.fn__layer > div")).click();
        Thread.sleep(1000);
        //输入回帖内容
        driver.findElement(By.xpath("//div[@class='vditor-ir']//pre[@placeholder='请输入回帖内容']")).sendKeys("自动化回帖");
        //点击提交
        driver.findElement(By.id("commentSubmitBtn")).click();
    }
}
