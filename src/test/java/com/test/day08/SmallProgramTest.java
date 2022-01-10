package com.test.day08;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/6 21:27
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class SmallProgramTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //启动被测的App--微信
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","127.0.0.1:62001");
        caps.setCapability("platformName","Android");
        caps.setCapability("appPackage","com.tencent.mm");
        caps.setCapability("appActivity",".ui.LauncherUI");
        //一定要切记！！！ 要加这个配置，否则你的微信数据全部都会被Appium干掉
        caps.setCapability("noReset",true);

        // 支持X5内核应用自动化配置
        caps.setCapability("recreateChromeDriverSessions", true);
        // ChromeOptions使用来定制启动选项，因为在appium中切换context识别webview的时候,
        // 把com.tencent.mm:appbrand0的webview识别成com.tencent.mm的webview.
        // 所以为了避免这个问题，加上androidProcess: com.tencent.mm:appbrand0
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("androidProcess", "com.tencent.mm:appbrand0");
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        // 初始化会默认将chrome浏览器打开，需要将Browser置为空
        caps.setBrowserName("");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url,caps);
        Thread.sleep(10000);

        //向下滑动--小程序列表加载出来
        swipeDown(driver,2000);
        Thread.sleep(2000);
        //定位小程序列表中【柠檬班软件测试】
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"柠檬班软件…\")")).click();
        Thread.sleep(10000);
        //进入到小程序页面
        //System.out.println(driver.getContextHandles());
        //切换到小程序的状态中（WEBVIEW_com.tencent.mm:appbrand0）
        driver.context("WEBVIEW_com.tencent.mm:appbrand0");
        //当你的微信小程序打开之后是会有三个web页面(窗口)的-->窗口的切换
        switchWindow(driver,"腾讯课堂柠檬班软件测试");
        //定位元素【课程】
        driver.findElement(By.xpath("//a[contains(text(),'课程')]")).click();

    }

    /**
     * 封装的通用切换窗口的方法-根据对应窗口的标题来切换
     * @param driver 驱动对象
     * @param title 窗口标题
     */
    public static void switchWindow(WebDriver driver, String title){
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String windowHandle: allWindowHandles){
            //根据窗口的URL地址或者标题来进行判断
            if(title.equals(driver.getTitle())){
                break;
            }else {
                driver.switchTo().window(windowHandle);
            }
        }
    }

    /**
     * 向下滑动的通用封装
     * @param driver 驱动对象
     * @param swipeTime 滑动间隔时间
     */
    public static void swipeDown(AndroidDriver driver, int swipeTime){
        //1、确定滑动起始点（屏幕宽度1/2，屏幕高度1/4）、滑动终止点（屏幕宽度1/2，屏幕高度3/4） 坐标系统
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        PointOption pointOption1 = PointOption.point(width/2,height/4);
        PointOption pointOption2 = PointOption.point(width/2,height*3/4);
        //2、通过TouchAction类来模拟滑动过程
        TouchAction touchAction = new TouchAction(driver);
        //先按下-->移动--->手指释放
        //waitAction() 设置滑动的间隔时间
        Duration duration = Duration.ofMillis(swipeTime);
        WaitOptions waitOptions = WaitOptions.waitOptions(duration);
        touchAction.press(pointOption1).waitAction(waitOptions).moveTo(pointOption2).release().perform();
    }
}
