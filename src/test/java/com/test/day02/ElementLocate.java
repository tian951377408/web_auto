package com.test.day02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/11/19 20:08
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class ElementLocate {
    public static void main(String[] args) {
        WebDriver driver = openBrowser("chrome");
        driver.get("https://www.baidu.com");
        //============================六大基本元素定位=============================
        //1、id定位
        /*driver.findElement(By.id("kw")).sendKeys("柠檬班");
        driver.findElement(By.id("su")).click();*/

        //2、name定位
        //driver.findElement(By.name("wd")).sendKeys("柠檬班");

        //3、tagName 标签名定位 基本不用
        /*List<WebElement> allElements = driver.findElements(By.tagName("a"));
        System.out.println(allElements.size());*/

        //4、className 样式名定位
        //坑：通过自动化代码打开的浏览器是一个全新的浏览器
        //注意：定位元素的时候保证页面跟自动化代码跑的页面是一样
        //driver.findElement(By.className("s_ipt")).sendKeys("柠檬班");

        //5、linkText定位  根据超链接元素的文本值（完整）定位
        //driver.findElement(By.linkText("更多")).click();

        //6、partialLinkText定位 根据超链接元素的文本值（部分）定位
        //driver.findElement(By.partialLinkText("hao123")).click();

        //===================================css选择器定位==============================
        //1、标签名定位
        /*List<WebElement> allElements = driver.findElements(By.cssSelector("a"));
        System.out.println(allElements.size());*/

        //2、id定位
        //driver.findElement(By.cssSelector("#kw")).sendKeys("柠檬班");

        //3、class定位
        //driver.findElement(By.cssSelector(".s_ipt")).sendKeys("柠檬班");

        //4、单属性选择
        driver.findElement(By.cssSelector("input[autocomplete='off']")).sendKeys("柠檬班");
        driver.findElement(By.cssSelector("input[autocomplete=\"off\"]")).sendKeys("柠檬班");

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
