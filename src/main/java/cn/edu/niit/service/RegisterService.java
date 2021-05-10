package cn.edu.niit.service;

import cn.edu.niit.dao.RegisterDao;
import cn.edu.niit.javabean.User;

public class RegisterService {
    public final int REGISTER_SUCCESS=1;
    public final int REGISTER_FAILURE=0;
    private RegisterDao registerDao=new RegisterDao();

    public String Register(User user){
        int i=registerDao.register(user);
        if (REGISTER_SUCCESS==i)
        {
            return "注册成功";
        }else {
            return "注册失败";
        }
    }
}
