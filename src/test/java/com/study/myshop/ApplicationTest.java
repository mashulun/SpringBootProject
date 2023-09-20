package com.study.myshop;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 单元测试的主文件
 * @author msl
 * @version 1.0
 * @create 2023-09-18 3:08
 */
@RunWith(SpringJUnit4ClassRunner.class)   //当前类为测试运行类
//@RunWith(SpringRunner.class)//当前类是测试运行类
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration  //互联网项目
public class ApplicationTest {

    //内部有两个主要方法,可以执行AOP
    //测试开始
    @Before
    public void before(){
        System.out.println("-------------Test Start---------------------");
    }



    //测试结束
    @After
    public void aVoid(){
        System.out.println("-------------Test End---------------------");
    }
}
