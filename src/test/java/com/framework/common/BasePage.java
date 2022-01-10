package com.framework.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;


import static java.util.logging.Logger.*;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/11 21:31
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class BasePage {
    private static Logger logger= Logger.getLogger(BasePage.class);


    /**
     * 显式等待元素可见二次封装
     *
     * @param driver
     * @param by
     */
    public WebElement waitElementVisible ( WebDriver driver, By by ) {
        WebElement webElement=null;
        try{
        //1、实例化WebDriverWait 超时时间10s
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        //2、通过until方法等到某个条件满足时为止
        webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }catch (Exception e){
            logger.error("定位元素异常：【"+by+"】异常");
        }return webElement;
    }

    /**
     * 显式等待元素可被点击二次封装
     *
     * @param driver
     * @param by
     */
    public WebElement waitElementClickable ( WebDriver driver, By by ) {
        WebElement webElement=null;
        try {
            //1、实例化WebDriverWait 超时时间10s
            WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
            //2、通过until方法等到某个条件满足时为止
            webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        }catch (Exception e){
            logger.error("定位元素【"+by+"】异常");
        }
        return webElement;
    }

    public void type ( WebDriver driver, By by, String data ,String elementName) {
        logger.info("往元素【"+elementName+"】输入数据【"+data+"】");
        waitElementVisible(driver, by).sendKeys(data);
    }

    ;

    public void click ( WebDriver driver, By by ,String elementName) {
        logger.info("对元素【"+elementName+"】进行点击");
        waitElementClickable(driver, by).click();
    }
    public String getText(WebDriver driver,By by,String elementName){
        String text=waitElementVisible(driver,by).getText();
        logger.info("获取元素：【"+by+"】文本【"+text+"】");
        return text;
    };
};