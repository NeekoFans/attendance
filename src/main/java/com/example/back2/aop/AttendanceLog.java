package com.example.back2.aop;

import com.example.back2.bean.Attendance;
import com.example.back2.dao.UserDao;
import com.example.back2.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/*@Component
@Aspect*/
public class AttendanceLog {
    @Resource
    private UserDao userDao;
    @Resource
    private UserService userService;

    @Pointcut(value = "execution(* com.example.back2.controller.UserController.login(..))")
    public void loginPoint() {
    }

    @Pointcut(value = "execution(* com.example.back2.controller.UserController.sign_in(..))")
    public void sign_inPoint() {
    }

    @Pointcut(value = "execution(* com.example.back2.controller.UserController.sign_out(..))")
    public void sign_outPoint() {
    }

    List<Attendance> list = new ArrayList<>();

    @Around("loginPoint()")
    public Map LoginAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String userId = (String) args[0];
        //String signIn= (String) args[2];
        Map map = null;
        try {
            map = (Map) joinPoint.proceed();
            Attendance attendance = new Attendance(userId);
            list.add(attendance);
            //userService.addAtt(attendance);
            return map;
        } catch (Throwable throwable) {
            throw throwable;
        }
    }

    @Around("sign_inPoint()")
    public Map sign_inAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String userId = (String) args[0];
        Map map = null;
        try {
            map = (Map) joinPoint.proceed();
            Attendance attendance = new Attendance(userId);
            list.add(attendance);
            return map;
        } catch (Throwable throwable) {
            throw throwable;
        }
    }

//    @Around("sign_outPoint()")
//    public Map sign_outAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object[] args = joinPoint.getArgs();
//        String userId = (String) args[0];
//        Map map = null;
//        try {
//            map = (Map) joinPoint.proceed();
//            Attendance attendance = new Attendance(userId);
//            list.add(attendance);
//            userService.addAtt(list.get(0).getUserId(),list.get(1).getUserId(),list.get(2).getUserId());
//            return map;
//        } catch (Throwable throwable) {
//            throw throwable;
//        }
//    }
}