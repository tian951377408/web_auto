package com.framework.pages;

import com.framework.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/11 21:37
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class GoodsListPage extends BasePage {
    //商品列表
    private By goodsListBy = By.xpath("//a[text()='商品列表']");
    //第一个商品
    private By firstGoodsBy = By.xpath("//div[@class='goods-img']");
    private WebDriver driver;

    public GoodsListPage(WebDriver driver){
        this.driver=driver;
    }

    public void clickGoodsList(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        click(driver,goodsListBy,"商品列表");
    }

    public GoodsDetailPage clickFirstGoods() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        click(driver,firstGoodsBy,"第一个商品");
        return new GoodsDetailPage(driver);
    }
}
