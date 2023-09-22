package com.example.back2.dao;

import com.example.back2.bean.Attendance;
import com.example.back2.bean.Notice;
import com.example.back2.bean.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class UserDao {
    @Resource
    private JdbcTemplate jdbc;
    public User login(String account){
//        String sql="select account,password from user where account=?";
//        List<User> list=jdbc.query(sql, new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//                User u=new User();
//                u.setAccount(rs.getString("account"));
//                u.setPassword(rs.getString("password"));
//                return u;
//            }
//        },account);
//        if (list.size()==0){
//            return null;
//        }
//        return list.get(0);
        String sql="select account,name,tel,password from user where account=? ";
        List<User> list=jdbc.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User u=new User();
                u.setAccount(rs.getString("account"));
                u.setName(rs.getString("name"));
                u.setTel(rs.getString("tel"));
                u.setPassword(rs.getString("password"));

                return u;
            }
        },account);
        if (list.size()==0){
            return null;
        }
        return list.get(0);
    }

    public void add(User user){
        String sql="insert into user(account,password) values(?,?)";
        jdbc.update(sql,user.getAccount(),user.getPassword());
    }
    public void addAtt(Attendance attendance){
        String sql="insert into attendance(user_id,sign_in,sign_out,create_time) values(?,?,?,?)";
        jdbc.update(sql,attendance.getUserId(),attendance.getSignIn(),attendance.getSignOut(),
                attendance.getCreateTime());
    }

    public Notice getNotice(){
        String sql="select title,content,create_time from notice where 1=1";
        sql = sql + " limit ?,? ";
        List<Notice> list=jdbc.query(sql, new RowMapper<Notice>() {
            @Override
            public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
                Notice u=new Notice();
                u.setTitle(rs.getString("title"));
                u.setContent(rs.getString("content"));
                Date createTime = rs.getDate("create_time");
                if (createTime != null) {
                    //这里要将前面import java.util.Date;改成import java.sql.Date才会出来toLocalDate()
                    u.setCreateTime(createTime.toLocalDate());
                }
                return u;
            }
        },0,1);
        if (list.size()==0){
            return null;
        }
        return list.get(0);
    }

    public void nameEdit(String name,String account){
        String sql="update user set name=? where account=?";
        jdbc.update(sql,name,account);
    }
    public void telEdit(String tel,String account){
        String sql="update user set tel=? where account=?";
        jdbc.update(sql,tel,account);
    }
    public void passwordEdit(String password,String account){
        String sql="update user set password=? where account=?";
        jdbc.update(sql,password,account);
    }
    public long total(){
        String sql="select count(*) as total from notice where 1=1";
        Map<String,Object> map=jdbc.queryForMap(sql);
        long total= (long) map.get("total");
        return total;
    }
    public List<Notice> getList(Notice notice){
        List<Object>param=new ArrayList<>();
        String sql="select id, title,content,create_time from notice where 1=1 ";
        //sql=sql+" and content like %+keyWords+% ";

        if(!StringUtils.isEmpty(notice.getKeywords())){
            sql+=" and content like ? ";
            param.add("%"+notice.getKeywords()+"%");
        }
        sql=sql+" order by create_time desc ";

        List<Notice>list=jdbc.query(sql, new RowMapper<Notice>() {
            @Override
            public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
                Notice u=new Notice();
                u.setId(rs.getInt("id"));
                u.setTitle(rs.getString("title"));
                u.setContent(rs.getString("content"));
                Date createTime = rs.getDate("create_time");
                if (createTime != null) {
                    //这里要将前面import java.util.Date;改成import java.sql.Date才会出来toLocalDate()
                    u.setCreateTime(createTime.toLocalDate());
                }
                return u;
            }
        },param.toArray());

        return list;
    }
    public List<Notice> getkeyList(String keyWords){
        List<Object>param=new ArrayList<>();
        String sql="select id, title,content,create_time from notice where 1=1 ";
        //sql=sql+" and content like %+keyWords+% ";

        if(!StringUtils.isEmpty(keyWords)){
            sql+=" and content like ? ";
            param.add("%"+keyWords+"%");
        }
        sql=sql+" order by create_time desc ";
        List<Notice>list=jdbc.query(sql, new RowMapper<Notice>() {
            @Override
            public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
                Notice u=new Notice();
                u.setId(rs.getInt("id"));
                u.setTitle(rs.getString("title"));
                u.setContent(rs.getString("content"));
                Date createTime = rs.getDate("create_time");
                if (createTime != null) {
                    //这里要将前面import java.util.Date;改成import java.sql.Date才会出来toLocalDate()
                    u.setCreateTime(createTime.toLocalDate());
                }
                return u;
            }
        },param.toArray());

        return list;
    }
    public Notice getEdit(Integer id){
        String sql="select title,content,create_time from notice where id=? ";
        List<Notice> list=jdbc.query(sql, new RowMapper<Notice>() {
            @Override
            public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
                Notice u=new Notice();
                u.setTitle(rs.getString("title"));
                u.setContent(rs.getString("content"));
                Date createTime = rs.getDate("create_time");
                if (createTime != null) {
                    //这里要将前面import java.util.Date;改成import java.sql.Date才会出来toLocalDate()
                    u.setCreateTime(createTime.toLocalDate());
                }
                return u;
            }
        },id);
        if (list.size()==0){
            return null;
        }
        return list.get(0);
    }

}
