package com.example.back2.bean;

import java.time.LocalDate;
import java.util.Date;

public class Attendance {
    private String id;
    private String userId;
    private String signIn;
    private String signOut;
    private String keyWords;
    //private Date createTime=new Date();
    private LocalDate createTime=LocalDate.now();
    public Attendance() {
    }

    public Attendance(String userId) {
        this.userId = userId;
    }

    public Attendance(String userId, String signIn) {
        this.userId = userId;
        this.signIn=signIn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSignIn() {
        return signIn;
    }

    public void setSignIn(String signIn) {
        this.signIn = signIn;
    }

    public String getSignOut() {
        return signOut;
    }

    public void setSignOut(String signOut) {
        this.signOut = signOut;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
}
