package com.framework.common;

import com.framework.listeners.RetryListener;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author 歪歪欧巴
 * @Description 用例的共性操作、共有方法
 * @date 2021/12/11 20:08
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class BaseTest {
    private static Logger logger=Logger.getLogger(BaseTest.class);
    public WebDriver driver;


    /**
     * 打开所有浏览器通用方法封装
     *
     * @param browserName 浏览器名
     */
    public WebDriver openBrowser(String browserName) {
        WebDriver webDriver = null;
        if ("chrome".equalsIgnoreCase(browserName)) {
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
            webDriver = new ChromeDriver();
            logger.info("=============================打开谷歌浏览器=================================");
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
        driver=webDriver;
        return webDriver;
    }
public void myAssertTrue(boolean condition,String assertDes){
        logger.info("断言：【"+assertDes+"】条件表达式【" +condition+"】");
        Assert.assertTrue(condition);
    }
    public void myAssertEqual(String actual,String expcted,String assertDes){
        logger.info("断言：【"+assertDes+"】实际值：【"+actual+"】期望值【" +expcted+"】");
        Assert.assertEquals(actual,expcted);
    }

}
