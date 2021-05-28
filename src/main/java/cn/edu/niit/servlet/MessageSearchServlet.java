package cn.edu.niit.servlet;

import cn.edu.niit.javabean.Message;
import cn.edu.niit.service.MessageService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "MessageSearchServlet",urlPatterns = "/message/search")
public class MessageSearchServlet extends HttpServlet {
    private MessageService messageService=new MessageService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String paramJson= IOUtils.toString(req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject =
                JSON.parseObject(paramJson,
                        HashMap.class);
        int pageNum = (int) parseObject.get("pageNum");
        int pageSize = (int) parseObject.get("pageSize");
        List<Message> messages=new ArrayList<>();
        int count=0;
        messages = messageService.searchAllMessage(pageNum, pageSize);
        count=messageService.countNum();
        req.getSession().setAttribute("messages",messages);
        resp.getWriter().println(count);
    }
}
