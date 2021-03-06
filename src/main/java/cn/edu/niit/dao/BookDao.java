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

    public List<Book> selectBorrowHistory(int card_id, int pageNum, int pageSize) {
        String sql = "select books.* , book_sort.name as sort,borrow_books.borrow_date,borrow_books.return_date from book_sort,books INNER JOIN borrow_books ON books.id = borrow_books.book_id INNER JOIN borrow_card ON borrow_card.id = borrow_books.card_id where books.sort_id=book_sort.id and  borrow_books.card_id =? LIMIT ?,?";
        List<Book> books = new ArrayList<>();
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{card_id,(pageNum - 1) * pageSize,
                                     pageSize})) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("sort"),
                        rs.getTimestamp("borrow_date"),
                        rs.getTimestamp("return_date")
                );
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> selectBorrowFromId(int card_id,int pageNum, int pageSize) {
        String sql = "select books.* , book_sort.name as sort from book_sort,books INNER JOIN borrow_books ON books.id = borrow_books.book_id INNER JOIN borrow_card ON borrow_card.id = borrow_books.card_id where books.sort_id=book_sort.id and  borrow_books.card_id =? and borrow_books.return_date IS NULL LIMIT ?,?";

        List<Book> books = new ArrayList<>();
        try (ResultSet rs = JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{card_id,(pageNum - 1) * pageSize,
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

    public List<Book> selectStoreFromId(int card_id,int pageNum, int pageSize) {
        String sql = "select books.* , book_sort.name as sort from book_sort,books INNER JOIN book_store ON books.id = book_store.book_id INNER JOIN borrow_card ON borrow_card.id = book_store.card_id where books.sort_id=book_sort.id and  book_store.card_id = ? LIMIT ?,?";

        List<Book> books = new ArrayList<>();
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{card_id,(pageNum - 1) * pageSize,
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

    public List<Book> selectBookFromName(String name,int pageNum, int pageSize) {
        String sql = "select books.*, book_sort.name as sort from books, book_sort where books.sort_id=book_sort.id  and (books.name like ? or books.author like ? or book_sort.name like ? or books.description like ?)  limit ?,?";
        name="%"+name+"%";
        List<Book> books = new ArrayList<>();
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{name,name,name,name,(pageNum - 1) * pageSize,
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

    public int borrowHistoryCount(int card_id) {
        String sql = "select count(*) as countNum from book_sort,books INNER JOIN borrow_books ON books.id = borrow_books.book_id INNER JOIN borrow_card ON borrow_card.id = borrow_books.card_id where books.sort_id=book_sort.id and borrow_books.card_id =?";
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{card_id})) {

            while (rs.next()) {
                int count = rs.getInt("countNum");
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int borrowCountFromId(int card_id) {
        String sql = "select count(*) as countNum from book_sort,books INNER JOIN borrow_books ON books.id = borrow_books.book_id INNER JOIN borrow_card ON borrow_card.id = borrow_books.card_id where books.sort_id=book_sort.id and  borrow_books.card_id =? and borrow_books.return_date IS NULL";
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{card_id})) {

            while (rs.next()) {
                int count = rs.getInt("countNum");
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countFromName(String name) {
        String sql = "select count(*) as countNum from books, book_sort where books.sort_id=book_sort.id  and (books.name like ? or books.author like ? or book_sort.name like ? or books.description like ?)";
        name="%"+name+"%";
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{name,name,name,name})) {

            while (rs.next()) {
                int count = rs.getInt("countNum");
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countFromStore(int card_id) {
        String sql = "select count(*) as countNum from book_sort,books INNER JOIN book_store ON books.id = book_store.book_id INNER JOIN borrow_card ON borrow_card.id = book_store.card_id where books.sort_id=book_sort.id and  book_store.card_id = ?";
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{card_id})) {

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

    public boolean selectBorrow(int card_id, int bookId) {
        String sql1 = "select EXISTS( SELECT 1 from borrow_books " +
                "where book_id=? and card_id=? and return_date IS NULL) as store";
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql1,
                             new Object[]{
                                     bookId, card_id
                             });) {

            while (rs.next()) {
                return rs.getBoolean("store");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int insertBorrowBook(int card_id, int book_id) {
        String sql = "INSERT into borrow_books(card_id,book_id,borrow_date) VALUES(?,?,now())";
        return JDBCUtil.getInstance().executeUpdate(sql,
                new Object[]{card_id,book_id});
    }

    public int returnBook(int card_id, int book_id) {
        String sql = "UPDATE borrow_books SET return_date = now() WHERE card_id=? and book_id=? and return_date IS NULL";
        return JDBCUtil.getInstance().executeUpdate(sql,
                new Object[]{card_id,book_id});
    }


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
