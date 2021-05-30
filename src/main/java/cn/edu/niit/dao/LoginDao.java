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
                user=new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("reader"),rs.getString("describe"),rs.getString("email"),rs.getInt("sex"),rs.getString("cellphone"));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        if (user.getSex()==0)
        {
            user.setXingbie("男");
        }
        else {
            user.setXingbie("女");
        }
        return user;
    }

    public User selectUserInfo(int card_id)
    {
        User user=null;
        try (ResultSet rs= JDBCUtil.getInstance().executeQueryRS("select * from borrow_card where card_id=?",new Object[]{card_id})){
            while (rs.next())
            {
                user=new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("reader"),rs.getString("describe"),rs.getString("email"),rs.getInt("sex"),rs.getString("cellphone"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
