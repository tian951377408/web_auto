package com.framework.businesslogic;

import com.framework.config.GlobalDatas;
import com.framework.pages.HomePage;
import com.framework.pages.LoginPage;
import org.openqa.selenium.WebDriver;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/11 21:13
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class LoginFlow {
    private WebDriver driver;
    public LoginFlow(WebDriver driver){
        this.driver=driver;
    }
    public HomePage login(String phone,String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputPhone(phone);
        loginPage.inputPassword(password);
        HomePage homePage = loginPage.clickLogin();
        return homePage;
    }
}
