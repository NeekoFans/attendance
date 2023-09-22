package com.example.back2.controller;

import com.example.back2.bean.Attendance;
import com.example.back2.bean.Notice;
import com.example.back2.service.AttendanceService;
import com.example.back2.service.AttendanceServiceI;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@Transactional
@Controller
public class AttendanceController {
    @Resource
    private AttendanceService attendanceService;
    @RequestMapping("/getAttendanceData")
    public Map getAttendanceData(Attendance attendance){
        return attendanceService.getAttendanceData(attendance);
    }
}
