package com.test.day07;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/1 21:35
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class ElementUnableLocateTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //启动被测App
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","127.0.0.1:62001");
        caps.setCapability("platformName","Android");
        caps.setCapability("appPackage","tv.danmaku.bili");
        caps.setCapability("appActivity",".ui.splash.SplashActivity");
        //在通过代码启动App的时候不清除App的数据
        caps.setCapability("noReset",true);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url,caps);
        Thread.sleep(3000);

        //要能够兼容弹窗出现/弹窗不出现的情况
        try {
            //点击【登录导航栏按钮】
            driver.findElement(MobileBy.id("tv.danmaku.bili:id/drawer_handler")).click();
        }catch (Exception e){
            //可能会有弹窗,处理弹窗
            //Text定位弹窗【我知道了】
            driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"我知道了\")")).click();
            Thread.sleep(1000);
            driver.findElement(MobileBy.id("tv.danmaku.bili:id/drawer_handler")).click();
        }
        Thread.sleep(2000);
        //点击【登录按钮】
        driver.findElement(MobileBy.id("tv.danmaku.bili:id/login")).click();
        Thread.sleep(2000);
        //点击【密码登录】
        driver.findElement(MobileBy.id("android:id/button1")).click();

    }
}
