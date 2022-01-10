package com.test.day07;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/6 14:32
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class Homework {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //启动被测App
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "127.0.0.1:62001");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "tv.danmaku.bili");
        caps.setCapability("appActivity", ".ui.splash.SplashActivity");
        //在通过代码启动App的时候不清除App的数据
        caps.setCapability("noReset", true);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url, caps);
        Thread.sleep(3000);

        //要能够兼容弹窗出现/弹窗不出现的情况
        try {
            //点击【登录导航栏按钮】
            driver.findElement(MobileBy.id("tv.danmaku.bili:id/drawer_handler")).click();
        } catch (Exception e) {
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
        Thread.sleep(2000);
        driver.findElement(MobileBy.id("tv.danmaku.bili:id/username")).sendKeys("18108457230");
        driver.findElement(MobileBy.id("tv.danmaku.bili:id/userpwd")).sendKeys("lemon123456");
        driver.findElement(MobileBy.id("tv.danmaku.bili:id/btn_login")).click();
        Thread.sleep(3000);
        //点击【首页】
        driver.findElement(MobileBy.xpath("//android.widget.CheckedTextView[@text='首页']")).click();
        Thread.sleep(2000);
        //搜索【柠檬班软件测试】
        driver.findElement(MobileBy.id("tv.danmaku.bili:id/expand_search")).click();
        Thread.sleep(2000);
        driver.findElement(MobileBy.id("tv.danmaku.bili:id/search_src_text")).sendKeys("柠檬班软件测试");
        Thread.sleep(2000);
        //按下enter键
        KeyEvent keyEvent = new KeyEvent();
        keyEvent.withKey(AndroidKey.ENTER);
        driver.pressKey(keyEvent);
        Thread.sleep(2000);
        //点击软件测试柠檬班pro
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"软件测试柠檬班Pro\")")).click();
        Thread.sleep(2000);
        //点关注
        driver.findElement(MobileBy.id("tv.danmaku.bili:id/follow")).click();

    }


    /**
     * 向左滑动通用封装
     *
     * @param driver    驱动对象
     * @param swipeTime 滑动时间
     */
    public void swipeLeft(AndroidDriver driver, int swipeTime) {
        // 获取到屏幕的宽度
        int width = driver.manage().window().getSize().getWidth();
        // 获取到屏幕的高度
        int height = driver.manage().window().getSize().getHeight();

        PointOption startPointOption = PointOption.point(width * 3 / 4, height / 2);
        PointOption endPointOption = PointOption.point(width / 4, height / 2);
        TouchAction touchAction = new TouchAction(driver);

        Duration duration = Duration.ofMillis(swipeTime);
        WaitOptions waitOptions = WaitOptions.waitOptions(duration);

        touchAction.press(startPointOption).waitAction(waitOptions).moveTo(endPointOption).release();
        touchAction.perform();
    }

    /**
     * 向右滑动通用封装
     *
     * @param driver    驱动对象
     * @param swipeTime 滑动时间
     */
    public void swipeRight(AndroidDriver driver, int swipeTime) {
        // 获取到屏幕的宽度
        int width = driver.manage().window().getSize().getWidth();
        // 获取到屏幕的高度
        int height = driver.manage().window().getSize().getHeight();

        PointOption startPointOption = PointOption.point(width / 4, height / 2);
        PointOption endPointOption = PointOption.point(width * 3 / 4, height / 2);
        TouchAction touchAction = new TouchAction(driver);

        Duration duration = Duration.ofMillis(swipeTime);
        WaitOptions waitOptions = WaitOptions.waitOptions(duration);

        touchAction.press(startPointOption).waitAction(waitOptions).moveTo(endPointOption).release();
        touchAction.perform();
    }
}
