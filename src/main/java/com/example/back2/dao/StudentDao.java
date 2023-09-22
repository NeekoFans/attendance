package com.example.back2.dao;

import com.example.back2.bean.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/*
* mybatis执行接口
*
* */
@Repository
public interface StudentDao {
     public void save(Student stu);
     public void delete(String[] id);
     //多参数时使用@Param注解，并设置参数名称
     public void update(@Param("a") String id,@Param("b") LocalDate date);
     public List<Student>query(Student stu);
}
