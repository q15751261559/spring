package cn.edu.niit.servlet;

import cn.edu.niit.javabean.User;
import cn.edu.niit.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "StoreBooksServlet",urlPatterns="/book/store")
public class StoreBooksServlet extends HttpServlet {
    BookService bookService=new BookService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result;
        int book_id=Integer.parseInt(req.getParameter("book_id"));
        String buttonVal="已收藏";
        String button_store=req.getParameter("button_store").trim();
        if (button_store.equals(buttonVal)){
            result=bookService.deleteStoreBook(((User)req.getSession().getAttribute("user")).getCard_id(),book_id);
            buttonVal="收藏";
        }else {
            result=bookService.insertStoreBook(((User)req.getSession().getAttribute("user")).getCard_id(),book_id);
        }
        result=result+" "+buttonVal;
        resp.getWriter().print(result);
    }
}
