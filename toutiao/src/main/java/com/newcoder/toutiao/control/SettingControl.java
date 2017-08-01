package com.newcoder.toutiao.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yiyuan on 2017/7/31.
 */
@Controller
public class SettingControl {
    @RequestMapping("/setting")
    @ResponseBody
    public  String setting()
    {
        return "Setting OK";
    }
}
