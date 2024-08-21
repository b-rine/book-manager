package me.briannguyen.bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private String jdbcURL;
    private String jdbcUser;
    private String jdbcPass;
    private Connection jdbcConnection;

    public BookDAO(String jdbcURL, String jdbcUser, String jdbcPass) {
        this.jdbcURL = jdbcURL;
        this.jdbcUser = jdbcUser;
        this.jdbcPass = jdbcPass;
    }

    private void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
        }
    }

    private void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
        connect();

        PreparedStatement prepState = jdbcConnection.prepareStatement(sql);
        prepState.setString(1, book.getTitle());
        prepState.setString(2, book.getAuthor());
        prepState.setFloat(3, book.getPrice());

        boolean rowInserted = prepState.executeUpdate() > 0;
        prepState.close();
        disconnect();
        return rowInserted;
    }

    public boolean updateBook(Book book) throws SQLException{
        String sql = "UPDATE books SET title = ?, author = ?, price = ? WHERE book_id = ?";
        connect();

        PreparedStatement prepState = jdbcConnection.prepareStatement(sql);
        prepState.setString(1, book.getTitle());
        prepState.setString(2, book.getAuthor());
        prepState.setFloat(3, book.getPrice());
        prepState.setInt(4, book.getId());

        boolean rowUpdated = prepState.executeUpdate() > 0;
        prepState.close();
        disconnect();
        return rowUpdated;
    }

    public boolean deleteBook(Book book) throws SQLException {
        String sql = "DELETE FROM books WHERE book_id = ?";
        connect();

        PreparedStatement prepState = jdbcConnection.prepareStatement(sql);
        prepState.setInt(1, book.getId());

        boolean rowDeleted = prepState.executeUpdate() > 0;
        prepState.close();
        disconnect();
        return rowDeleted;
    }

    public Book getBook(int id) throws SQLException {
        Book book = null;
        String sql = "SELECT * FROM books WHERE book_id = ?";
        connect();

        PreparedStatement prepState = jdbcConnection.prepareStatement(sql);
        prepState.setInt(1, id);

        ResultSet resultSet = prepState.executeQuery();
        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");
            book = new Book(id, title, author, price);
        }

        resultSet.close();
        prepState.close();
        disconnect();
        return book;
    }

    public List<Book> listAllBooks() throws SQLException{
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM books";
        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");

            Book book = new Book(id, title, author, price);
            bookList.add(book);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return bookList;
    }

}
