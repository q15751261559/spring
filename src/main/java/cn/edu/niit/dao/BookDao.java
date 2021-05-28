package cn.edu.niit.dao;

import cn.edu.niit.db.JDBCUtil;
import cn.edu.niit.javabean.Book;
import cn.edu.niit.javabean.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDao {
    public List<Book> selectAll(int card_id,int pageNum, int pageSize) {
        String sql = "select books.*, book_sort.name as sort " +
                "from books, book_sort where " +
                "books.sort_id=book_sort.id limit ?,?";

        List<Book> books = new ArrayList<>();
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{(pageNum - 1) * pageSize,
                                     pageSize})) {

            while (rs.next()) {
                Book book = new Book(rs.getInt("id"),
                        rs.getString(
                                "name"),
                        rs.getString("author"),
                        rs.getString("sort"),
                        rs.getString("description"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public int count() {
        String sql = "select count(*) as countNum from books";
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{})) {

            while (rs.next()) {
                int count = rs.getInt("countNum");
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean selectStore(int card_id, int bookId) {
        int count=0;
        String sql1 = "select count(*) as countNum FROM book_store where card_id=? and book_id=?";
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql1,
                             new Object[]{
                                     card_id, bookId
                             });) {

            while (rs.next()) {
                 count = rs.getInt("countNum");
            }
            if (count>0){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

//    public boolean selectStore(int card_id, int bookId) {
//        String sql1 = "select EXISTS( SELECT 1 from borrow_books " +
//                "where book_id=? and card_id=?) as store";
//        try (ResultSet rs =
//                     JDBCUtil.getInstance().executeQueryRS(sql1,
//                             new Object[]{
//                                     bookId, card_id
//                             });) {
//
//            while (rs.next()) {
//                return rs.getBoolean("store");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }

//    public int insertStoreBook(String username, String bookId) {
//        String sql = "insert into borrow_books(book_id, card_id, " +
//                "borrow_date) values(?,?,?)";
//        int result = JDBCUtil.getInstance().executeUpdate(sql,
//                new Object[]{
//                        bookId, username,
//                        new Date(System.currentTimeMillis())
//                });
//        return result;
//    }

    public int insertStoreBook(int card_id, int bookId) {
        String sql = "insert into book_store(card_id,book_id) values(?,?)";
        int result = JDBCUtil.getInstance().executeUpdate(sql,
                new Object[]{
                        card_id, bookId});
        return result;
    }

    public int deleteStoreBook(int card_id, int bookId) {
        String sql = "DELETE FROM book_store WHERE card_id=? and book_id=?";
        int result = JDBCUtil.getInstance().executeUpdate(sql,
                new Object[]{
                        card_id, bookId});
        return result;
    }
}
