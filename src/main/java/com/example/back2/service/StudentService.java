package com.example.back2.service;

import com.example.back2.bean.Student;
import com.example.back2.dao.StudentDao;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class StudentService implements StudentServiceI{
    @Resource
    private StudentDao studentDao;
    @Override
    public Map add(Student stu) {
        Map map=new HashMap();
        studentDao.save(stu);
        map.put("code",1);
        map.put("msg","新增成功");
        return map;
    }
    public Map delete(String id) {
        Map map=new HashMap();
        studentDao.delete(id.split(","));
        map.put("code",1);
        map.put("msg","删除成功");
        return map;
    }
    public Map update(String id, LocalDate date) {
        Map map=new HashMap();
        studentDao.update(id,date);
        map.put("code",1);
        map.put("msg","更改成功");
        return map;
    }
    public Map query(Student stu) {
        Map map=new HashMap();
        List<Student>list=studentDao.query(stu);
        map.put("code",1);
        map.put("list",list);
        return map;
    }
}
