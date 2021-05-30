package cn.edu.niit.servlet;

import cn.edu.niit.javabean.Book;
import cn.edu.niit.javabean.User;
import cn.edu.niit.service.BookService;
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

@WebServlet(name = "BorrowHistoryServlet",urlPatterns = "/borrowHistory")
public class BorrowHistoryServlet extends HttpServlet {
    BookService bookService=new BookService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramJson= IOUtils.toString(req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject =
                JSON.parseObject(paramJson,
                        HashMap.class);
        int pageNum = (int) parseObject.get("pageNum");
        int pageSize = (int) parseObject.get("pageSize");
        List<Book> books=new ArrayList<>();
        int count=0;
        books=bookService.selectBorrowHistory(((User)req.getSession().getAttribute("user")).getCard_id(),pageNum,pageSize);
        count=bookService.borrowHistoryCount(((User)req.getSession().getAttribute("user")).getCard_id());
        for (int i=0;i<books.size();i++)
        {
            if (books.get(i).getReturn_date() == null)
            {
                books.get(i).setGuihuan("未归还");
            }else
            {
                books.get(i).setGuihuan(books.get(i).getReturn_date().toString());
            }
        }
        //3. 将结果放入session
        req.getSession().setAttribute("borrowHistoryBooks", books);
        //将count直接作为ajax请求的结果返回
        resp.getWriter().print(count);
    }
}
