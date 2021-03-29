package cn.edu.niit.service;

import cn.edu.niit.dao.LoginDao;
import cn.edu.niit.javabean.Login;
import cn.edu.niit.javabean.User;

import javax.servlet.http.HttpSession;

public class LoginService {
    public final String LOGIN_SUCCESS="1";
    private LoginDao loginDao=new LoginDao();
    public String login(Login login, HttpSession session){
        User user=loginDao.selectOne(login.getUsername());
        if (user!=null){
            if (login.getPassword().equals(user.getPassword()))
            {
                session.setAttribute("user",user);
                session.setAttribute("isLogin",true);
                return LOGIN_SUCCESS;
            }else {
                return "密码错误";
            }
        }
        else {
            return "用户不存在";
        }
    }
}
