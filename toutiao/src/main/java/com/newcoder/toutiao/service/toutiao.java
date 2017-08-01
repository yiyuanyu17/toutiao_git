package com.newcoder.toutiao.service;

import org.springframework.stereotype.Service;

/**
 * Created by yiyuan on 2017/7/31.
 */
@Service
//定义成service之后 spring 会自动注入该类型 不需要在使用的地方人为创建一个新的对象 去调用方法
public class toutiao {
    public String say()
    {
        return "Hello from toutiao";
    }
}
