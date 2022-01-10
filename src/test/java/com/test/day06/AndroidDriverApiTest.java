package com.test.day06;


import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/11/29 20:44
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class AndroidDriverApiTest {
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
        //获取首页的页面名
        //System.out.println( driver.currentActivity());
        //获取设备时间信息
        /*System.out.println(driver.getDeviceTime());
        //获取设备DPI，注意不是分辨率
        System.out.println(driver.getDisplayDensity());
        //获取automation name，默认为null，如果有指定automation name为uiautomator2就为对应的值
        System.out.println(driver.getAutomationName());
        //获取设备横竖屏状态，有PORTRAIT(竖屏)与LANDSCAPE(横屏)
        System.out.println(driver.getOrientation());*/

        driver.findElement(MobileBy.AccessibilityId("题库")).click();
        Thread.sleep(1000);
        //4、定位去登陆元素
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"去登录\")")).click();
        Thread.sleep(1000);

        //发送按键事件
        //返回
        //1、实例化KeyEvent的对象
        /*KeyEvent keyEvent = new KeyEvent();
        //2、设置按键操作为：返回
        keyEvent.withKey(AndroidKey.VOLUME_DOWN);
        //3、执行对应的事件
        driver.pressKey(keyEvent);*/

        //截图
        File file = driver.getScreenshotAs(OutputType.FILE);
        //保存到本地
        File file2 = new File("D:\\test\\screenshot_app.png");
        try {
            FileUtils.copyFile(file,file2);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
