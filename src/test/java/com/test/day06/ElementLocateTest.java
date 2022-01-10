package com.test.day06;


import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/11/29 20:44
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class ElementLocateTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //一、启动被测的App
        //1、初始化配置对象，用来存放启动app的四个配置
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","127.0.0.1:62001");
        caps.setCapability("platformName","Android");
        caps.setCapability("appPackage","com.lemon.lemonban");
        caps.setCapability("appActivity",".activity.WelcomeActivity");
        //2、需要与Appium服务建立连接，把我们的配置传给Appium
        //第一个参数：URL appium的工作地址
        //第二个参数：caps配置
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url,caps);
        Thread.sleep(4000);
        //3、定位【首页】的题库元素
        //driver.findElement(MobileBy.id("com.lemon.lemonban:id/navigation_tiku")).click();
        driver.findElement(MobileBy.AccessibilityId("题库")).click();
        Thread.sleep(1000);
        //4、定位去登陆元素
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"去登录\")")).click();
        Thread.sleep(1000);
        //5、输入手机号码
        driver.findElement(MobileBy.xpath("//*[@text='手机号码']")).sendKeys("13323234545");
        //6、输入密码
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/et_password")).sendKeys("123456");
        //7、点击登录按钮
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/btn_login")).click();
    }
}
