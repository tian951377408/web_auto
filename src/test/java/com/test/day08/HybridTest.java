package com.test.day08;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/6 20:40
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class HybridTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //启动被测的App
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","127.0.0.1:62001");
        caps.setCapability("platformName","Android");
        caps.setCapability("appPackage","com.lemon.lemonban");
        caps.setCapability("appActivity",".activity.WelcomeActivity");
        caps.setCapability("noReset",true);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url,caps);
        Thread.sleep(4000);
        //点击【柠檬社区】
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"柠檬社区\")")).click();
        Thread.sleep(3000);
        //进入到web页面（测试派）
        //切换状态 从原生页面元素状态-->web页面元素状态(context)
        //[NATIVE_APP, WEBVIEW_com.lemon.lemonban]
        //System.out.println(driver.getContextHandles());
        //通过conext来进行切换
        driver.context("WEBVIEW_com.lemon.lemonban");
        //定位web元素信息
        driver.findElement(By.xpath("//a[@href='http://testingpai.com/register']")).click();

    }
}
