package cn.edu.niit.service;

import cn.edu.niit.dao.BookDao;
import cn.edu.niit.javabean.Book;

import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();

    public List<Book> searchBookFromName(int card_id,String name, int pageNum, int pageSize) {

        List<Book> books = bookDao.selectBookFromName(name,pageNum, pageSize);
        for (Book book : books) {
            System.out.println(book.getId());
            book.setStore(isStore(card_id, book.getId()));
            System.out.println(book.isStore());
        }
        return books;
    }

    public List<Book> searchAllBooks(int card_id, int pageNum, int pageSize) {

        List<Book> books = bookDao.selectAll(card_id,pageNum, pageSize);
        for (Book book : books) {
            System.out.println(book.getId());
            book.setStore(isStore(card_id, book.getId()));
            System.out.println(book.isStore());
        }
        return books;
    }

    public int countNum() {
        return bookDao.count();
    }
    public int countNumFromName(String name){
        return bookDao.countFromName(name);
    }

    public boolean isStore(int card_id, int bookId) {
        return bookDao.selectStore(card_id, bookId);
    }

    public String insertStoreBook(int card_id, int bookId) {
        int result = bookDao.insertStoreBook(card_id, bookId);
        System.out.println(result);
        if (result > 0) {
            return "收藏成功";
        } else {
            return "借阅失败";
        }
    }

    public String deleteStoreBook(int card_id, int bookId) {
        int result = bookDao.deleteStoreBook(card_id, bookId);
        System.out.println(result);
        if (result > 0) {
            return "删除收藏成功";
        } else {
            return "删除失败";
        }
    }
}
