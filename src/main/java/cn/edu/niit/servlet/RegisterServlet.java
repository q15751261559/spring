package cn.edu.niit.servlet;

import cn.edu.niit.javabean.User;
import cn.edu.niit.service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet",urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
     private RegisterService registerService=new RegisterService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        User user = new User(req.getParameter("username"), req.getParameter("password"), req.getParameter("reader"));
        String result = registerService.Register(user);
        req.getRequestDispatcher("/index.jsp?message="+result).forward(req,resp);
    }
}
