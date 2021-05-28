package cn.edu.niit.servlet;

import cn.edu.niit.javabean.Message;
import cn.edu.niit.javabean.User;
import cn.edu.niit.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MessageInsertServlet",urlPatterns = "/message/insert")
public class MessageInsertServlet extends HttpServlet {
    private MessageService messageService = new MessageService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          String textarea = req.getParameter("message");
          System.out.println(textarea);
        Message message=new Message();
        User user = (User) req.getSession().getAttribute("user");
        message.setReader(user.getReader());
        message.setDetail(textarea);
        messageService.insertMessage(message);
    }
}
