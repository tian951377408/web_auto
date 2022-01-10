package com.framework.pages;

import com.framework.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/11 20:15
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class LoginPage extends BasePage {
    //元素定位信息-->Java对象里面的属性
    //1、手机号码输入框
    private By phoneBy = By.xpath("//input[@placeholder='请输入手机号/用户名']");
    //2、密码输入框
    private By passwordBy = By.xpath("//input[@placeholder='请输入密码']");
    //3、登录按钮
    private By loginButtonBy = By.xpath("//a[@class='login-button']");

    private By errorAccountBy=By.xpath("//p[text()='账号或密码不正确']");
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    //元素操作-->Java对象里面的行为
    public void inputPhone(String phone){
        //driver？？？
        type(driver,phoneBy,phone,"手机号");
    }

    public void inputPassword(String password){
        type(driver,passwordBy,password,"密码");
    }

    public HomePage clickLogin(){
        waitElementClickable(driver,loginButtonBy).click();
        //进入到首页--新的页面的诞生
        return new HomePage(driver);
    }
    public String geterrorAccountPwdtext(){
        return getText(driver,errorAccountBy,"账号或密码不正确的提示信息");

    };
}
