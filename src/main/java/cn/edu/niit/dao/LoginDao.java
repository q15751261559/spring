package cn.edu.niit.dao;

import cn.edu.niit.db.JDBCUtil;
import cn.edu.niit.javabean.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    public User selectOne(String username){
        User user=null;
        try (ResultSet rs= JDBCUtil.getInstance().executeQueryRS("select * from borrow_card where username=?",new Object[]{username})){
            while (rs.next())
            {
                user=new User(rs.getString("username"),rs.getString("password"),rs.getString("reader"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}
