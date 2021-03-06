package cn.edu.niit.service;

import cn.edu.niit.dao.BookDao;
import cn.edu.niit.javabean.Book;

import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();

    public List<Book> searchBookFromName(int card_id,String name, int pageNum, int pageSize) {

        List<Book> books = bookDao.selectBookFromName(name,pageNum, pageSize);
        for (Book book : books) {
            book.setStore(isStore(card_id, book.getId()));
            book.setBorrow(isBorrow(card_id, book.getId()));
        }
        return books;
    }

    public List<Book> searchStoreFromId(int card_id,int pageNum, int pageSize) {

        return bookDao.selectStoreFromId(card_id,pageNum, pageSize);
    }

    public List<Book> searchBorrowFromId(int card_id,int pageNum, int pageSize) {

        return bookDao.selectBorrowFromId(card_id,pageNum, pageSize);
    }


    public List<Book> selectBorrowHistory(int card_id, int pageNum, int pageSize) {
        return bookDao.selectBorrowHistory(card_id,pageNum,pageSize);
    }

    public List<Book> searchAllBooks(int card_id, int pageNum, int pageSize) {

        List<Book> books = bookDao.selectAll(card_id,pageNum, pageSize);
        for (Book book : books) {
            book.setStore(isStore(card_id, book.getId()));
            book.setBorrow(isBorrow(card_id, book.getId()));
        }
        return books;
    }

    public int borrowHistoryCount(int card_id){return bookDao.borrowHistoryCount(card_id);}
    public int countNum() {
        return bookDao.count();
    }
    public int countNumFromName(String name){
        return bookDao.countFromName(name);
    }
    public int countStoreNumFromId(int card_id) {
        return bookDao.countFromStore(card_id);
    }
    public int borrowCountFromId(int card_id) {
        return bookDao.borrowCountFromId(card_id);
    }
    public boolean isStore(int card_id, int bookId) {
        return bookDao.selectStore(card_id, bookId);
    }
    public boolean isBorrow(int card_id, int bookId) {
        return bookDao.selectBorrow(card_id, bookId);
    }

    public String insertStoreBook(int card_id, int bookId) {
        int result = bookDao.insertStoreBook(card_id, bookId);
        if (result > 0) {
            return "????????????";
        } else {
            return "????????????";
        }
    }

    public String deleteStoreBook(int card_id, int bookId) {
        int result = bookDao.deleteStoreBook(card_id, bookId);
        if (result > 0) {
            return "??????????????????";
        } else {
            return "????????????";
        }
    }

    public String insertBorrowBook(int card_id, int bookId) {
        int result = bookDao.insertBorrowBook(card_id, bookId);
        if (result > 0) {
            return "????????????";
        } else {
            return "????????????";
        }
    }

    public String returnBook(int card_id, int bookId) {
        int result = bookDao.returnBook(card_id, bookId);
        if (result > 0) {
            return "????????????";
        } else {
            return "????????????";
        }
    }
}
