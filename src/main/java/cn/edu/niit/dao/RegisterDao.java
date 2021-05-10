package cn.edu.niit.dao;

import cn.edu.niit.db.JDBCUtil;
import cn.edu.niit.javabean.User;

public class RegisterDao {
    public int register(User user){
        int i= JDBCUtil.getInstance().executeUpdate("insert borrow_card(`username`,`reader`,`password`,`status`) values(?,?,?,'1')",
                new Object[]{user.getUsername(),user.getReader(),user.getPassword()});
        return i;
    }
}
