package com.framework.testcases;

import com.framework.businesslogic.AddToCart;
import com.framework.businesslogic.LoginFlow;
import com.framework.common.BaseTest;
import com.framework.config.GlobalDatas;
import com.framework.pages.CartPage;
import com.framework.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/8 21:26
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class TestCart extends BaseTest {

    @BeforeMethod
    @Parameters({"browserName"})
    public void setup(String browserName){
        //用例前置
        //1、打开浏览器
        openBrowser(browserName);
        driver.manage().window().maximize();
        //2、进入登录页面
        driver.get(GlobalDatas.INDEX_URL);
        HomePage homePage = new HomePage(driver);
        homePage.enterLoginPage();
        LoginFlow loginFlow = new LoginFlow(driver);
        loginFlow.login(GlobalDatas.USER_NAME,GlobalDatas.USER_PASSWORD);
    }
    @Test
    public void test_addtocart_success() throws InterruptedException {
        //测试步骤
        //添加商品到购物车
        AddToCart addToCart = new AddToCart(driver);
        HashMap<String,Object> datas = addToCart.doAction();
        //断言
        //进入到购物车
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = homePage.enterCartPage();
        //1、商品的名称
       myAssertEqual(cartPage.getGoodsName(), (String) datas.get("goodsTitle"),"根据商品名称断言");
        //2、商品的价格
        //3、商品的数量
    }

    @AfterTest
    public void teardown() throws InterruptedException {
        //测试后置
        //删除购物车商品
        CartPage cartPage = new CartPage(driver);
        cartPage.deleteCart();
        //1、退出登录
        HomePage homePage = new HomePage(driver);
        homePage.quitLogin();
        //2、关闭浏览器
        driver.quit();
    }

}
