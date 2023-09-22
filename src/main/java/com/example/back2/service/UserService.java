package com.example.back2.service;

import com.example.back2.bean.Attendance;
import com.example.back2.bean.Notice;
import com.example.back2.bean.User;
import com.example.back2.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@RestController
@Service
public class UserService implements UserServiceI {
    @Resource
    private HttpServletRequest request;
    @Resource
    private UserDao userDao;
    User userAll=new User();
    @Override
    public Map login(String account, String password) {
        Map map=new HashMap();
        User user=userDao.login(account);
        userAll=user;
        if (user==null){
            map.put("code",0);
            map.put("msg","账户名或密码错误");
            return map;
        }
        if (!user.getPassword().equals(password)){
            map.put("code",0);
            map.put("msg","账户名或密码错误");
            return map;
        }
        if (!user.getAccount().matches("^.{0,15}$")){
            map.put("code",0);
            map.put("msg","账号长度不能超过15个字符");
            return map;
        }
        if (!user.getPassword().matches("^.{0,8}$")){
            map.put("code",0);
            map.put("msg","密码长度不能超过8个字符");
            return map;
        }
        map.put("code",1);
        map.put("msg","登录成功");
        HttpSession session=request.getSession();
        session.setAttribute("loginUser",user);
        return map;

    }
    public Map getNotice(Notice notice){
        Map map=new HashMap();
        Notice notice1=userDao.getNotice();
        HttpSession session=request.getSession();
        session.setAttribute("NoticeInfo",notice1);
        return map;
    }
    public Map getUserInfo(){
        Map map=new HashMap();
        HttpSession session=request.getSession();
        session.setAttribute("UserInfo",userAll);
        return map;
    }

    @Override
    public Map register(String account, String password) {
        Map map=new HashMap();
        User user=userDao.login(account);
        if (user!=null){
            map.put("code",0);
            map.put("msg","账户名已存在");
            return map;
        }
        User user1=new User();
        user1.setAccount(account);
        user1.setPassword(password);
         userDao.add(user1);
        map.put("code",1);
        map.put("msg","注册成功");
        return map;
    }

    @Override
    public void addAtt(Attendance attendance) {

        userDao.addAtt(attendance);
    }
    public Map nameEdit(String name,String account){
        userDao.nameEdit(name,account);
        return null;
    }
    public Map telEdit(String tel,String account){
        userDao.telEdit(tel,account);
        return null;
    }
    public Map passwordEdit(String password,String account){
        userDao.passwordEdit(password,account);
        return null;
    }
    int rowId=0;
    public Map getTableData(Notice notice) {
        Map map = new HashMap();
       long total = userDao.total();
        List<Notice> list = userDao.getList(notice);
        map.put("code", 1);
        map.put("msg", "执行");
        map.put("rows", list);
        map.put("total", total);
        rowId=list.get(0).getId();
        return map;
    }
    public Map showById(Integer id) {
        Map map=new HashMap();
        Notice notice=userDao.getEdit(id);
        map.put("code",1);
        map.put("data",notice);
        return map;
    }
    public Map getkeyData(String keyWords) {
        Map map = new HashMap();
        long total = userDao.total();
        List<Notice> list = userDao.getkeyList(keyWords);
        map.put("code", 1);
        map.put("msg", "执行");
        map.put("rows", list);
        map.put("total", total);
        rowId=list.get(0).getId();
        return map;
    }
}
