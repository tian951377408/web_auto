package com.test.day05;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/11/26 20:34
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class FileuploadTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = openBrowser("chrome");
        driver.get("file:///C:/Users/86180/Desktop/fileupload.html");
        Thread.sleep(2000);
        //1、当你的文件上传按钮元素标签名为input并且type属性为file的情况下
        //driver.findElement(By.id("fu")).sendKeys("D:\\logs\\traces.txt");
        //2、如果非上述情况的话，我们就需要操作windows系统的元素（AutoIT）
        //driver.findElement(By.id("fu")).click();
        JavascriptExecutor javascriptExecutor=(JavascriptExecutor) driver;
        javascriptExecutor.executeScript("document.getElementById('fu').click();");
        Thread.sleep(2000);
        //执行autoit转换过来的exe文件，完成上传文件的功能
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("src\\test\\resources\\test_autoit.exe");
        } catch (IOException e) {
            e.printStackTrace();
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
