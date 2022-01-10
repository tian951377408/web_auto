package com.test.day03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/11/22 21:32
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class ElementWaitTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = openBrowser("chrome");
        /*driver.get("https://movie.douban.com/");
        //设置隐式等待 超时时间10S
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //【选电影】
        driver.findElement(By.xpath("//div[@id='db-nav-movie']//a[text()='选电影']")).click();
        //Thread.sleep(2000);
        //选择【红色通缉令】
        driver.findElement(By.xpath("//p[contains(text(),'红色通缉令')]")).click();
        driver.findElement(By.xpath("//p[contains(text(),'红色通缉令')]")).click();
        driver.findElement(By.xpath("//p[contains(text(),'红色通缉令')]")).click();
        driver.findElement(By.xpath("//p[contains(text(),'红色通缉令')]")).click();
        driver.findElement(By.xpath("//p[contains(text(),'红色通缉令')]")).click();
        driver.findElement(By.xpath("//p[contains(text(),'红色通缉令')]")).click();*/

        /*driver.get("https://www.baidu.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.findElement(By.id("s-usersetting-top")).click();
        driver.findElement(By.xpath("//a[text()='高级搜索']")).click();*/

        driver.get("https://movie.douban.com/");
        driver.findElement(By.xpath("//div[@id='db-nav-movie']//a[text()='选电影']")).click();
        //显式等待
        //1、实例化WebDriverWait 超时时间10s
        WebDriverWait webDriverWait = new WebDriverWait(driver,10);
        By by = By.xpath("//p[contains(text(),'红色通缉令')]");
        //2、通过until方法等到某个条件满足时为止
        WebElement webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        webElement.click();
    }

    /**
     * 显式等待元素可见二次封装
     * @param driver
     * @param by
     */
    public WebElement waitElementVisible(WebDriver driver, By by ){
        //1、实例化WebDriverWait 超时时间10s
        WebDriverWait webDriverWait = new WebDriverWait(driver,10);
        //2、通过until方法等到某个条件满足时为止
        WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return webElement;
    }

    /**
     * 显式等待元素可被点击二次封装
     * @param driver
     * @param by
     */
    public WebElement waitElementClickable(WebDriver driver, By by ){
        //1、实例化WebDriverWait 超时时间10s
        WebDriverWait webDriverWait = new WebDriverWait(driver,10);
        //2、通过until方法等到某个条件满足时为止
        WebElement webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        return webElement;
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
