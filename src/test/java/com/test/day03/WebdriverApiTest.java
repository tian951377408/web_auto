package com.test.day03;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Window;

import java.io.File;
import java.io.IOException;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/11/22 20:22
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class WebdriverApiTest {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = openBrowser("chrome");
        //get方法会等到当前网页加载结束才会返回
        driver.get("http://www.baidu.com/");
        //driver.findElement(By.xpath("//a[text()='地图']")).click();
        //Thread.sleep(2000);
        /*System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());*/
        //System.out.println(driver.getPageSource());

        //quit和 close的区别
        //driver.quit();
        //driver.findElement(By.id("kw")).sendKeys("柠檬班");
        //Thread.sleep(2000);
        //driver.close();

        //导航的操作
        /*Navigation navigation = driver.navigate();
        Thread.sleep(2000);
        //1、跳转到新的页面
        navigation.to("https://www.jd.com");
        Thread.sleep(2000);
        //2、刷新页面
        navigation.refresh();
        Thread.sleep(2000);
        //3、回退
        navigation.back();
        Thread.sleep(2000);
        //4、前进
        navigation.forward();*/

        //窗口的操作 链式调用的语法
        //Window window = driver.manage().window();
        //1、最大化  用得很多
        //window.maximize();
        //2、全屏
        //window.fullscreen();
        //3、获取窗口的大小/位置
        /*System.out.println(window.getSize().getWidth());
        System.out.println(window.getSize().getHeight());
        System.out.println(window.getPosition().getX());
        System.out.println(window.getPosition().getY());*/
        //4、设置窗口的大小/位置
        /*Dimension dimension = new Dimension(500,500);
        window.setSize(dimension);*/
        /*Thread.sleep(2000);
        Point point = new Point(100,100);
        window.setPosition(point);*/

        //截图 getScreenshotAs
        //转换为TakeScreenshot类型来得到截图能力
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        //outputType-->截图类型
        //OutputType.FILE -->文件对象
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        //把文件对象保存到本地文件
        try {
            //工具类的调用--commons.io依赖包里面的工具类
            FileUtils.copyFile(file,new File("D:\\test\\screenshot.jpg"));
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
