package com.example.back2.service;

import com.example.back2.bean.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public interface StudentServiceI {
    public Map add(Student stu);
    public Map delete(String id);
    public Map update(String id, LocalDate date);
    public Map query(Student stu);
}
