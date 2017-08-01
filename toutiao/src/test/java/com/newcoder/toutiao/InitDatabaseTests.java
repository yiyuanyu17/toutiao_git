package com.newcoder.toutiao;

import com.newcoder.toutiao.Model.User;
import com.newcoder.toutiao.dao.UserDAO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * Created by yiyuan on 2017/7/31.
 * @Sql("/init-schema.sql")
 * //在代码执行之前先执行上述sql语句
 */
@RunWith(SpringRunner.class)
@Sql("/init-schema.sql")
public class InitDatabaseTests {
    @Autowired(required=false)
    //interface 接口注入方式注意
     UserDAO userDAO;
    @Test
    public void contextLoads() {
        Random random = new Random();
        for(int i =0;i<10;i++)
        {
            System.out.println(String.format("%d",i));
            User user = new User();
            user.setHeadUrl(String.format("http://images.newcoder.com/head/%dt.png",random.nextInt(1000)));
            user.setName(String.format("USER%d",i));
            user.setPassword("");
            user.setSalt("");
           // userDAO.addUser(user);
        }
    }

}
