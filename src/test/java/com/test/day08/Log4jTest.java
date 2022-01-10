package com.test.day08;

import org.apache.http.ExceptionLogger;
import org.apache.log4j.Logger;

/**
 * @Project: day10
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright: java32期 T0123
 * @Author: tian
 * @Create: 2022-01-04 21:53
 * @Desc：
 **/
public class Log4jTest {
    private static Logger logger = Logger.getLogger(Log4jTest.class);
    public static void main(String[] args) {
        logger.debug("debug级别的日志");
        logger.info("info级别的日志");
        logger.warn("warn级别的日志");
        logger.error("error级别的日志");
    }

}
