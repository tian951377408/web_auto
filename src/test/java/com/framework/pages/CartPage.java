package com.framework.pages;

import com.framework.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/11 21:59
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class CartPage extends BasePage {
    private By goodsNameBy = By.xpath("//a[@class='name']");
    private By deleteCartBy = By.xpath("//a[text()='删除']");
    private By confirmBy = By.cssSelector(".btn-r");
    private WebDriver driver;
    public CartPage(WebDriver driver){
        this.driver=driver;
    }

    public String getGoodsName(){
        return waitElementVisible(driver,goodsNameBy).getText();
    }

    public void deleteCart(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        click(driver,deleteCartBy,"删除购物车");
//        waitElementClickable(driver,deleteCartBy);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        click(driver,confirmBy,"点击确定");
//        driver.findElement(confirmBy).click();
    }
}
