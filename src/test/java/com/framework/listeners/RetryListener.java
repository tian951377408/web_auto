package com.framework.listeners;

import com.framework.testcases.TestLogin02;
import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @Project: day10
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright: java32期 T0123
 * @Author: tian
 * @Create: 2022-01-09 17:50
 * @Desc：
 **/
public class RetryListener implements IRetryAnalyzer {
private static Logger logger=Logger.getLogger(RetryListener.class);

    private int maxRetryCount = 3;
    private int currentRetryCount = 0;

    @Override
    public boolean retry ( ITestResult result ) {
        System.out.println("执行到这里来");
//        return true;
        if (maxRetryCount > currentRetryCount) {
            logger.info("开始重试第【"+currentRetryCount+"】次，测试方法为【"+result.getName()+"】");
            currentRetryCount++;
            return true;
        }
        else {
return false;
        }
}
}
