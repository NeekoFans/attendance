package com.example.back2.service;

import com.example.back2.bean.Attendance;
import com.example.back2.bean.Notice;
import com.example.back2.bean.User;

import java.util.Date;
import java.util.Map;

public interface UserServiceI {
    public Map login(String account, String password);
    public Map register(String account, String password);
    public void addAtt(Attendance attendance);
    public Map getNotice(Notice notice);
    public Map getUserInfo();
    public Map nameEdit(String name,String account);
    public Map telEdit(String tel,String account);
    public Map passwordEdit(String password,String account);
    public Map getTableData(Notice notice);
    public Map showById(Integer id);
    public Map getkeyData(String keyWords);

}
