package com.framework.listeners;

import com.framework.testcases.TestLogin02;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @Project: day10
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright: java32期 T0123
 * @Author: tian
 * @Create: 2022-01-09 19:05
 * @Desc：
 **/
public class GlobalRetry implements IAnnotationTransformer {
    @Override
    public void transform( ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        // TODO Auto-generated method stub
        IRetryAnalyzer iRetryAnalyzer = annotation.getRetryAnalyzer();
        if (iRetryAnalyzer == null) {
            annotation.setRetryAnalyzer(RetryListener.class);
        }
    }

}
