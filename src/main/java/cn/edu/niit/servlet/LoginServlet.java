package cn.edu.niit.servlet;

import cn.edu.niit.javabean.Login;
import cn.edu.niit.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns="/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService=new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        Login login=new Login(req.getParameter("username"),req.getParameter("password"));
        String result=loginService.login(login,req.getSession());
        req.getSession().setAttribute("isLogin",true);
        if (loginService.LOGIN_SUCCESS.equals(result)){
            resp.sendRedirect("/main.jsp");
        }else {
            req.getRequestDispatcher("/index.jsp?message="+result).forward(req,resp);
        }
    }
}
