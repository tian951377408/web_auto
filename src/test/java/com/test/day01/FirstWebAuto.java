package com.test.day01;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Set;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/11/17 20:41
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class FirstWebAuto {
    public static void main(String[] args) {
        WebDriver driver = openBrowser("chrome");
        driver.get("https://www.baidu.com");
    }


    /**
     * 打开所有浏览器通用方法封装
     *
     * @param browserName 浏览器名
     */
    public static WebDriver openBrowser(String browserName) {
        WebDriver webDriver = null;
        if ("chrome".equalsIgnoreCase(browserName)) {
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
            webDriver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browserName)) {
            System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
            webDriver = new FirefoxDriver();
        } else if ("ie".equalsIgnoreCase(browserName)) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            //取消IE安全设置（忽略IE的Protected Mode的设置）
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            //忽略浏览器缩放设置
            capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            System.setProperty("webdriver.ie.driver", "src\\test\\resources\\IEDriverServer.exe");
            webDriver = new InternetExplorerDriver(capabilities);
        }
        return webDriver;
    }

    public static void openFirefox() {
        //异常二： The path to the driver executable must be set by the webdriver.gecko.driver system property;
        //解决方案：
        System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
        //1、打开浏览器
        FirefoxDriver firefoxDriver = new FirefoxDriver();
        //2、访问对应的网址
        firefoxDriver.get("https://www.baidu.com");
    }

    public static void openChrome() {
        //异常一：The path to the driver executable must be set by the webdirver.chrome.driver system properity
        //解决方案：设置驱动的位置，让代码知道驱动在哪里
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        //1、打开浏览器
        ChromeDriver chromeDriver = new ChromeDriver();
        //2、访问对应的网址
        chromeDriver.get("https://www.baidu.com");
    }

    public static void openIE() {
        //取消IE安全设置（忽略IE的Protected Mode的设置）
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        //忽略浏览器缩放设置
        capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        //异常三：The path to the driver executable must be set by the webdriver.ie.driver system property
        //解决方案
        System.setProperty("webdriver.ie.driver", "src\\test\\resources\\IEDriverServer.exe");
        //异常四：Unexpected error launching Internet Explorer. Protected Mode settings are not the same for all zones
        //1、打开浏览器
        InternetExplorerDriver ieDriver = new InternetExplorerDriver(capabilities);
        //2、访问对应的网址
        ieDriver.get("https://www.baidu.com");
    }

}
