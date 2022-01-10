package com.test.day04;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/11/26 19:58
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class Homework {
    public static void main(String[] args) throws InterruptedException {
        //打开豌豆荚应用排行版页面，找到【虎牙直播】并进行点击  https://www.wandoujia.com/top/app
        WebDriver driver = openBrowser("chrome");
        driver.get("https://www.wandoujia.com/top/app");
        //怎么判断一个元素是否在当前页面中？？ getPageSource
        //一直不断的点击【查看更多】
        while(true) {
            if (driver.getPageSource().contains("title=\"虎牙XX直播\"")) {
                driver.findElement(By.xpath("//a[text()='虎牙XX直播']")).click();
                break;
            }else {
                //点击【查看更多】
                //怎么判断到了末尾呢？？数据全部加载出来？？
                //根据【查看更多】元素的属性style="display: none;判断
                WebElement webElement = driver.findElement(By.id("j-refresh-btn"));
                if(webElement.getAttribute("style").equals("display: none;")){
                    //数据加载完毕
                    break;
                }
                JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
                javascriptExecutor.executeScript("arguments[0].scrollIntoViewIfNeeded(true)", webElement);
                webElement.click();
                Thread.sleep(1000);
            }
        }
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
}
