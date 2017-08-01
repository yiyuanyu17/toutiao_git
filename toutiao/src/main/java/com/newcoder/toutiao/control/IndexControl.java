package com.newcoder.toutiao.control;

import com.newcoder.toutiao.Model.User;
import com.newcoder.toutiao.ToutiaoApplication;
import com.newcoder.toutiao.accept.LogAspect;
import com.newcoder.toutiao.service.toutiao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by yiyuan on 2017/7/31.
 * 通过注解的方式  指定类的作用
 * RequestMapping 指定URL
 * ResponseBody 表明是返回主页形式
 * postman 查看post get
 */
@Controller
public class IndexControl {
    @Autowired
    private  static final Logger logger = LoggerFactory.getLogger(IndexControl.class);
    @Autowired
    private toutiao tou1;
    //依靠注入的方式 不需要人为的创建对象  否则则需要在构造函数中 对private 中tou1进行初始化操作 IOC 控制反转 Spring注入
    //AOP 面向切面，所有业务都要处理的业务
    @RequestMapping(path = {"/","/index","/index/"})
    @ResponseBody
    public  String Index(HttpSession session)
    {
        logger.info("visit Index");
        return "Hello World "+session.getAttribute("msg")+"<br>"+tou1.say();

    }
    @RequestMapping(value = {"/profile/{groupId}/{userId}"})
    @ResponseBody
    /*
    PathVariable  传递进入参数 按照RequestMapping中的格式进行
    RequestParam 请求参数 ？key = &value =
     */
    public  String profile(@PathVariable("groupId") int groupId,
                           @PathVariable("userId") int userId,
                           @RequestParam(value = "type",defaultValue = "12")int type,
                           @RequestParam(value =  "key",defaultValue = "34")int key)
    {
        return String.format("%d,%d,%d,%d",groupId,userId,type,key);
    }

    @RequestMapping(path = "/vm")
    //@ResponseBody  这边不需要respose body 返回的是一个文件 注意点
    /*
    public  String news()
    {
        return "news";
    }
    */
    public  String news(Model model)
    {
        //Model 后端和前端渲染的一个数据模型
        //向freemaker中传递一个参数 参数名为value 值为yiyuan
        model.addAttribute("value","yiyuan");
        List<String> colors = Arrays.asList(new String[] {"RED","YELLOW","BLUE"});
        Map<String ,String> map = new HashMap<String,String>();
        for(int i =0;i<3;i++)
            map.put(String.valueOf(i),String.valueOf(i*i));
        model.addAttribute("Colors",colors);
        model.addAttribute("Maps",map);
        model.addAttribute("User",new User("yiyuan"));
        return "news";
    }
    @RequestMapping(path = "/request")
    @ResponseBody
    public String request(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession session)
    {
        StringBuilder sb = new StringBuilder();
        Enumeration<String>headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements())
        {
            String name = headerNames.nextElement();
            sb.append(name+":"+request.getHeader(name)+"<br>");
        }
        for(Cookie cookie:request.getCookies())
        {
            sb.append("Cookie:");
            sb.append(cookie.getName());
            sb.append(":");
            sb.append(cookie.getValue());
            sb.append("<br>");
        }
        sb.append("getMethod:"+request.getMethod()+"<br>");
        sb.append("getPathInfo:"+request.getPathInfo()+"<br>");
        sb.append("getQueryString:"+request.getQueryString()+"<br>");
        sb.append("getRequestURI:"+request.getRequestURI()+"<br>");
        return sb.toString();
    }
    @RequestMapping(value = "/response")
    @ResponseBody
    public  String response(@CookieValue(value = "nowcoderid",defaultValue = "a")String newvoderid,
                            @RequestParam(value = "key",defaultValue = "value")String key,
                            @RequestParam(value = "value",defaultValue = "value")String value,
                            HttpServletResponse response)
    {
        newvoderid = key;
        response.addCookie(new Cookie(newvoderid,value));
        response.addHeader(key,value);
        return "NowCoderId From Cookie:"+newvoderid;
    }
    //301 永久转移  302 临时转移  session 的意义是同一个有效的连接 作为一个session  这种不需要responsebody
    @RequestMapping("/redirect/{code}")
    public RedirectView redirect(@PathVariable("code")int code,
                                 HttpSession session)
    {
        session.setAttribute("msg","jump from redirect");
        RedirectView red = new RedirectView("/",true);
        if(code == 301)
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return red;
    }

    @RequestMapping(value = "/admin")
    @ResponseBody
    public  String admin(@RequestParam(value = "key",required = false)String key)
    {
        if("admin".equals(key))
            return "hello admin";
        throw  new IllegalArgumentException("Key 错误");
    }
    //自定义错误处理函数  这样就可以代替spring 自己实现的错误显示  统一错误页面处理 优化用户交互

    @ExceptionHandler
    @ResponseBody
    public String error(Exception e)
    {
        return "error:"+e.getMessage();
    }
}
