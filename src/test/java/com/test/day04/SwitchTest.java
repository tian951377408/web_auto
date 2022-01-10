package com.test.day04;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Set;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/11/24 20:28
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class SwitchTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = openBrowser("chrome");
        //driver.get("https://www.baidu.com");
        //System.out.println("点击前当前窗口的标识"+driver.getWindowHandle());
        //driver.findElement(By.xpath("//a[text()='hao123']")).click();
        //System.out.println("点击后当前窗口的标识"+driver.getWindowHandle());
        //Set<String> allWindowHandles = driver.getWindowHandles();
        //System.out.println("点击后所有窗口的标识"+driver.getWindowHandles());
        //思路：遍历窗口句柄的集合，判断当前的窗口标识是不是对的，不是的话就进行切换
        /*for (String windowHandle: allWindowHandles){
            //根据窗口的URL地址或者标题来进行判断
            if(("hao123_上网从这里开始").equals(driver.getTitle())){
                break;
            }else {
                driver.switchTo().window(windowHandle);
            }
        }*/
        //switchWindow(driver,"hao123_上网从这里开始");
        //driver.findElement(By.xpath("//a[text()='星座运势']")).click();

        //2、iframe切换
        //例子一：腾讯课堂
        /*driver.get("https://ke.qq.com/");
        driver.findElement(By.id("js_login")).click();
        Thread.sleep(2000);
        //iframe元素定位
        WebElement iframeElement = driver.findElement(By.xpath("//iframe[contains(@src,'xlogin')]"));
        //切换iframe
        driver.switchTo().frame(iframeElement);
        //账号密码登录
        driver.findElement(By.id("switcher_plogin")).click();
        //输入QQ号码
        driver.findElement(By.id("u")).sendKeys("1425301899");
        Thread.sleep(2000);
        //关闭弹窗回到首页
        driver.switchTo().parentFrame();
        driver.findElement(By.xpath("//a[@title='关闭']")).click();*/

        //例子二：多层iframe嵌套
        /*driver.get("C:\\Users\\86180\\Desktop\\iframe\\a.html");
        Thread.sleep(2000);
        //1、切换到b.html中
        WebElement bframeElement = driver.findElement( By.id("bframe"));
        driver.switchTo().frame(bframeElement);
        driver.findElement(By.id("bb")).sendKeys("b输入框");
        Thread.sleep(2000);
        //2、切换到c.html中
        WebElement cframeElement = driver.findElement( By.id("cframe"));
        driver.switchTo().frame(cframeElement);
        driver.findElement(By.id("cc")).sendKeys("c输入框");
        Thread.sleep(2000);*/
        //3、返回到默认的页面
        //3-1、parentFrame() 一级一级的返回
        /*driver.switchTo().parentFrame();
        driver.switchTo().parentFrame();
        driver.findElement(By.id("aa")).sendKeys("a输入框");*/
        //3-2、defaultContent() 一次性回到默认页面
        /*driver.switchTo().defaultContent();
        driver.findElement(By.id("aa")).sendKeys("a输入框");*/

        //3、alert弹框的处理
        /*driver.get("C:\\Users\\86180\\Desktop\\alert.html");
        Thread.sleep(2000);
        driver.findElement(By.id("abtn")).click();
        Thread.sleep(2000);
        //切换alert
        Alert alert = driver.switchTo().alert();
        //alert.accept();
        //alert.dismiss();
        System.out.println(alert.getText());*/

        //JavaScript的操作
        //1、移除元素的属性
        //driver.get("https://www.12306.cn/index/");
        //Thread.sleep(2000);
        //driver.findElement(By.id("train_date")).sendKeys("2021-11-26");
        //在当前去调用JavaScript的代码
        //强制转换,得到JavaScript的执行能力
        //JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
        //设置元素的属性值
        //javascriptExecutor.executeScript("document.getElementById('train_date').setAttribute('value','2020-1-1');");
        //移除掉元素的属性值
        //javascriptExecutor.executeScript("document.getElementById('train_date').removeAttribute('value');");

        //2、页面滚动
        //2-1、滚动到页面的最底部
        /*driver.get("https://www.12306.cn/index/");
        Thread.sleep(2000);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
        //javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        //2-2、滚动到指定的元素上去
        javascriptExecutor.executeScript("document.getElementById('index_ads').scrollIntoViewIfNeeded(true);");*/

        //3、特殊元素无法被点击的情况
        driver.get("https://www.ketangpai.com/#/login");
        driver.findElement(By.xpath("//input[@placeholder='请输入邮箱/手机号/账号']")).sendKeys("1425301899@qq.com");
        driver.findElement(By.xpath("//input[@placeholder='请输入密码']")).sendKeys("123456");
        //ElementClickInterceptedException 元素点击中断异常
        //element click intercepted: Element <span>...</span> is not clickable at point (754, 372).
        // Other element would receive the click: <div data-v-5bca919b="" class="login-tab">...</div>
        //driver.findElement(By.xpath("//span[text()='登录']")).click();
        //通过JavaScript来点击元素
        //传参的使用 ，Java中的变量传递给JavaScript
        /*WebElement webElement = driver.findElement(By.xpath("//span[text()='登录']"));
        String s = "hello";
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
        javascriptExecutor.executeScript("arguments[0].arguments[1]click();",webElement,s);
        driver.findElement(By.xpath("//div[@class='login-tab']")).click();*/
    }

    /**
     * 封装的通用切换窗口的方法-根据对应窗口的标题来切换
     * @param driver 驱动对象
     * @param title 窗口标题
     */
    public static void switchWindow(WebDriver driver, String title){
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String windowHandle: allWindowHandles){
            //根据窗口的URL地址或者标题来进行判断
            if(title.equals(driver.getTitle())){
                break;
            }else {
                driver.switchTo().window(windowHandle);
            }
        }
    }

    /**
     * 打开所有浏览器通用方法封装
     *
     * @param browserName 浏览器名
     */
    public static WebDriver openBrowser(String browserName) {
        WebDriver webDriver = null;
        if ("chrome".equalsIgnoreCase(browserName)) {
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
            webDriver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browserName)) {
            System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
            webDriver = new FirefoxDriver();
        } else if ("ie".equalsIgnoreCase(browserName)) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            //取消IE安全设置（忽略IE的Protected Mode的设置）
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            //忽略浏览器缩放设置
            capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            System.setProperty("webdriver.ie.driver", "src\\test\\resources\\IEDriverServer.exe");
            webDriver = new InternetExplorerDriver(capabilities);
        }
        return webDriver;
    }
}
