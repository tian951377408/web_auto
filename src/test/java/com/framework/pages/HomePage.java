package com.framework.pages;

import com.framework.common.BasePage;
import com.framework.common.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/11 20:36
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class HomePage extends BasePage {
    //1、登录链接
    private By loginBy = By.xpath("//a[text()='登录']");
    //2、购物车
    private By cartBy = By.xpath("//span[@data-route='cart']");
    //3、登录成功的提示语
    private By tipsBy = By.xpath("//span[text()='欢迎来到柠檬班']");
    //4、用户名
    private By nicknameBy = By.xpath("//a[text()='waiwai']");
    //5、退出登录
    private By quitBy = By.xpath("//a[text()='退出登录']");

    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver=driver;
    }

    public LoginPage enterLoginPage(){
        click(driver,loginBy,"进入登陆页面");
        return new LoginPage(driver);
    }

    public CartPage enterCartPage(){
        click(driver,cartBy,"进入购物车");
        return new CartPage(driver);
    }

    public void quitLogin(){
        Actions actions = new Actions(driver);
        WebElement webElement = waitElementVisible(driver,By.xpath("//a[text()='waiwai']"));
        actions.moveToElement(webElement).perform();
        click(driver,quitBy,"退出登录");
    }

    public boolean isTipsExist(){
       return waitElementVisible(driver,tipsBy).isDisplayed();
    }

    public boolean isNicknameExist(){
        return waitElementVisible(driver,nicknameBy).isDisplayed();
    }

}
