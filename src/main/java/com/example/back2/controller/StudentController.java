package com.example.back2.controller;

import com.example.back2.bean.Student;
import com.example.back2.service.StudentServiceI;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentServiceI studentService;
    @RequestMapping("/add")
    public Map add(Student student){
        Map map=studentService.add(student);
        return map;
    }
    @RequestMapping("/delete")
    public Map delete(String id){
        Map map=studentService.delete(id);
        return map;
    }
    @RequestMapping("/update")
    public Map update(String id,@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        Map map=studentService.update(id,date);
        return map;
    }
    @RequestMapping("/query")
    public Map query(Student student){
        Map map=studentService.query(student);
        return map;
    }
}
