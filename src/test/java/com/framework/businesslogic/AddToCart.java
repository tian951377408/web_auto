package com.framework.businesslogic;

import com.framework.pages.GoodsDetailPage;
import com.framework.pages.GoodsListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/11 21:47
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class AddToCart {
    private WebDriver driver;
    public AddToCart(WebDriver driver){
        this.driver=driver;
    }

    /**
     * 添加到购物车业务逻辑
     * @return hashmap数据，包含了商品名称、商品价格、商品的数量
     */
    public HashMap<String, Object> doAction(){
        GoodsListPage goodsListPage = new GoodsListPage(driver);
        //点击商品列表
        goodsListPage.clickGoodsList();
        //点击第一个商品
        GoodsDetailPage goodsDetailPage = goodsListPage.clickFirstGoods();
        //点击添加到购物车
        goodsDetailPage.addToCart();
        //获取商品的名称
        String goodsTitle = goodsDetailPage.getGoodsName();

        //获取商品的价格
        //double price
        //获取商品的数量
        //int amount
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("goodsTitle",goodsTitle);
        return hashMap;
    }
}
