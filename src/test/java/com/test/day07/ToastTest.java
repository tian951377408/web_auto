package com.test.day07;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/1 21:14
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class ToastTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //启动被测App
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","127.0.0.1:62001");
        caps.setCapability("platformName","Android");
        caps.setCapability("appPackage","com.lemon.lemonban");
        caps.setCapability("appActivity",".activity.WelcomeActivity");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url,caps);
        //设置隐式等待
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);

        driver.findElement(MobileBy.AccessibilityId("题库")).click();
        Thread.sleep(1000);
        //4、定位去登陆元素
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"去登录\")")).click();
        Thread.sleep(1000);
        //5、输入手机号码
        driver.findElement(MobileBy.xpath("//*[@text='手机号码']")).sendKeys("");
        //6、输入密码
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/et_password")).sendKeys("");
        //7、点击登录按钮
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/btn_login")).click();
        //获取toast元素【手机号码或密码不能为空】
        //等待
        //显式等待，toast元素：不可见的，在页面中存在
        /*WebDriverWait webDriverWait = new WebDriverWait(driver,10);
        By by = MobileBy.xpath("//*[contains(@text,'手机号码或密码不能为空')]");
        WebElement webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));*/
        WebElement webElement = driver.findElement(MobileBy.xpath("//*[contains(@text,'手机号码或密码不能为空')]"));
        System.out.println(webElement.getText());

    }
}
