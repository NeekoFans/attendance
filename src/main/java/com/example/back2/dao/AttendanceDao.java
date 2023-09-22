package com.example.back2.dao;

import com.example.back2.bean.Attendance;
import com.example.back2.bean.Notice;
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
public class AttendanceDao {
    @Resource
    private JdbcTemplate jdbc;

    public long total(){
        String sql="select count(*) as total from attendance where 1=1";
        Map<String,Object> map=jdbc.queryForMap(sql);
        long total= (long) map.get("total");
        return total;
    }
    public List<Attendance> getList(Attendance attendance){
        List<Object>param=new ArrayList<>();
        String sql="select id, user_id,sign_in,sign_out,create_time from attendance where 1=1";
        if(!StringUtils.isEmpty(attendance.getKeyWords())){
            sql+=" and create_time like ? ";
            param.add("%"+attendance.getKeyWords()+"%");
        }
        //把输入的内容弄过来代替下划线
        //sql=sql+" and title like %_% ";
//        sql=sql+" and content like %% ";
        sql=sql+" order by create_time desc ";

        List<Attendance>list=jdbc.query(sql, new RowMapper<Attendance>() {
            @Override
            public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
                Attendance u=new Attendance();
                u.setId(rs.getString("id"));
                u.setUserId(rs.getString("user_id"));
                u.setSignIn(rs.getString("sign_in"));
                u.setSignOut(rs.getString("sign_out"));
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
}
