package cn.edu.niit.servlet;

import cn.edu.niit.javabean.User;
import cn.edu.niit.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BorrowServlet",urlPatterns = "/book/borrow")
public class BorrowServlet extends HttpServlet {
    BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result;
        int book_id=Integer.parseInt(req.getParameter("book_id"));
        String buttonVal="归还";
        String button_borrow=req.getParameter("button_borrow").trim();
        System.out.println("id:"+book_id);
        if (button_borrow.equals(buttonVal)){
            result=bookService.returnBook(((User)req.getSession().getAttribute("user")).getCard_id(),book_id);
            buttonVal="借阅";
        }else {
            result=bookService.insertBorrowBook(((User)req.getSession().getAttribute("user")).getCard_id(),book_id);
        }
        result=result+" "+buttonVal;
        resp.getWriter().print(result);
    }
}
