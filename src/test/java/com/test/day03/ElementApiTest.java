package com.test.day03;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/11/22 20:11
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class ElementApiTest {
    public static void main(String[] args) throws InterruptedException {
       WebDriver driver =  openBrowser("chrome");
       driver.get("http://testingpai.com");
        System.out.println(driver.getPageSource());
       /*driver.findElement(By.id("kw")).sendKeys("柠檬班");
       //按键组合操作
        //1、ctrl + a
        driver.findElement(By.id("kw")).sendKeys(Keys.CONTROL,"a");
        //2、ctrl + c
        driver.findElement(By.id("kw")).sendKeys(Keys.CONTROL,"c");
        Thread.sleep(2000);
        //2、ctrl + v
        driver.findElement(By.id("kw")).sendKeys(Keys.CONTROL,"v");
        Thread.sleep(2000);
        driver.findElement(By.id("kw")).sendKeys(Keys.CONTROL,"v");
        Thread.sleep(2000);
        driver.findElement(By.id("kw")).sendKeys(Keys.CONTROL,"v");*/

        /*WebElement webElement =  driver.findElement(By.xpath("//a[text()='地图']"));
        //获取标签名 getTagName
        String tagName = webElement.getTagName();
        System.out.println(tagName);

        //获取元素对应的属性值
        String hrefValue = webElement.getAttribute("href");
        System.out.println(hrefValue);

        //获取元素文本值
        String text = webElement.getText();
        System.out.println(text);*/

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

