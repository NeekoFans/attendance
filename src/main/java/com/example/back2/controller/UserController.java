package com.example.back2.controller;

import com.example.back2.bean.Attendance;
import com.example.back2.bean.Notice;
import com.example.back2.bean.User;
import com.example.back2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@Transactional
@Controller
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private HttpServletRequest request;

      @RequestMapping("/login")
    public Map login(String account,String password){
       Map map=userService.login(account,password);
       map.put("msgaccount",account);
       return map;
    }
    @RequestMapping("/register")
    public Map register(String account,String password){
        Map map=userService.register(account,password);
        return map;
    }
    @RequestMapping("/sign_in")
    public Map sign_in(String sin){
        Map map=new HashMap();
        map.put("msg",sin);
        return map;
    }
    @RequestMapping("/sign_out")
    public Map addAtt(String sout, String uid, String st){
        Attendance attendance=new Attendance();
        attendance.setUserId(uid);
        attendance.setSignIn(st);
        attendance.setSignOut(sout);
        attendance.setCreateTime(LocalDate.now());

        Map map=new HashMap();
        userService.addAtt(attendance);
        map.put("msg",sout);
        return map;
    }

    @RequestMapping("/getLoginInfo")
    public Map getLoginInfo(){
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("loginUser");
        Map map=new HashMap();
        if (user==null){
            map.put("code",0);
        }else {
            map.put("code",998);
            map.put("data",user.getAccount());
        }
        return map;
    }
    @RequestMapping("/getNotice")
    @ResponseBody
    public Map getNotice(){
          Notice notice=new Notice();
        Map map=userService.getNotice(notice);
        HttpSession session=request.getSession();
        Notice notice1= (Notice) session.getAttribute("NoticeInfo");
        if (notice1==null){
            map.put("code",0);
        }else {
            map.put("code",666);
            map.put("data",notice1);
        }
        return map;
    }

    @RequestMapping("/getInfo")
    @ResponseBody
    public Map getInfo(){
        Map map=new HashMap();
        userService.getUserInfo();
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("UserInfo");
        if (user==null){
            map.put("code",0);
        }else {
            map.put("code",666);
            map.put("data",user);
        }
        return map;
    }
    @RequestMapping("/nameEdit")
    public Map nameEdit(String name,String account){
        Map map=userService.nameEdit(name,account);

        return map;
    }
    @RequestMapping("/telEdit")
    public Map telEdit(String tel,String account){
        Map map=userService.telEdit(tel,account);

        return map;
    }
    @RequestMapping("/passwordEdit")
    public Map passwordEdit(String password,String account){
        Map map=userService.passwordEdit(password,account);

        return map;
    }
    @RequestMapping("/getTableData")
    public Map getTableData(Notice notice){
        return userService.getTableData(notice);
    }
    @RequestMapping("/showById")
    public Map showById(Integer id){
        return userService.showById(id);
    }
    @RequestMapping("/getkeyData")
    public Map getkeyData(String keyWords){
        return userService.getkeyData(keyWords);
    }
}
