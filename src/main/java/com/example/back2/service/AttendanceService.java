package com.example.back2.service;

import com.example.back2.bean.Attendance;
import com.example.back2.bean.Notice;
import com.example.back2.dao.AttendanceDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@RestController
@Service
public class AttendanceService implements AttendanceServiceI{
    @Resource
    private AttendanceDao attendanceDao;
    public Map getAttendanceData(Attendance attendance) {
        Map map = new HashMap();
        long total = attendanceDao.total();
        List<Attendance> list = attendanceDao.getList(attendance);
        map.put("code", 1);
        map.put("msg", "执行");
        map.put("rows", list);
        map.put("total", total);
        return map;
    }
}
