package com.test.day03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/11/24 20:02
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class Homework {
    public static void main(String[] args) throws InterruptedException {
        //WebDriver driver = openBrowser("chrome");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        FirefoxDriver driver = new FirefoxDriver(options);
/*        ChromeOptions options = new ChromeOptions();
        //设置无头模式
        options.addArguments("--headless");
        ChromeDriver driver = new ChromeDriver(options);*/
        driver.get("http://testingpai.com/login");
        driver.findElement(By.id("nameOrEmail")).sendKeys("shakebabe");
        driver.findElement(By.id("loginPassword")).sendKeys("lemon123456");
        driver.findElement(By.id("loginBtn")).click();
        //确保登录能够成功-显式等待
        //等待首页的某个特征的出现从而判定是否进入到首页（登录成功）
        WebDriverWait driverWait = new WebDriverWait(driver,10);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='欢迎来到 测试派 社区']")));
        driver.get("http://testingpai.com/article/1601464528256");
        //点击回帖
        driver.findElement(By.xpath("//span[text()='请输入回帖内容...']")).click();
        driver.findElement(By.xpath("//div[@class='vditor-ir']//pre[@placeholder='请输入回帖内容']"))
                .sendKeys("自动化回帖-歪歪");
        Thread.sleep(1000);
        driver.findElement(By.id("commentSubmitBtn")).click();
        Thread.sleep(1000);
        WebElement webElement = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-author='shakebabe']/p")));
        System.out.println(webElement.getText());
        //返回首页，刷新首页，关闭浏览器
        WebDriver.Navigation navigation = driver.navigate();
        navigation.back();
        Thread.sleep(1000);
        navigation.refresh();
        Thread.sleep(1000);
        driver.quit();
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
