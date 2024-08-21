package me.briannguyen.bookstore;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BookstoreServlet", urlPatterns = "/")
public class BookstoreServlet extends HttpServlet {

    private BookDAO bookDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUser = getServletContext().getInitParameter("jdbcUser");
        String jdbcPass = getServletContext().getInitParameter("jdbcPass");
        bookDAO = new BookDAO(jdbcURL, jdbcUser, jdbcPass);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deleteBook(request, response);
                    break;
                case "/insert":
                    insertBook(request, response);
                    break;
                case "/update":
                    updateBook(request, response);
                    break;
                default:
                    listBooks(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        getServletContext().getRequestDispatcher("/BookForm.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDAO.getBook(id);
        request.setAttribute("book", existingBook);
        getServletContext().getRequestDispatcher("/BookForm.jsp").forward(request, response);
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = bookDAO.getBook(id);
        bookDAO.deleteBook(book);
        response.sendRedirect("list");
    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));

        Book newBook = new Book(title, author, price);
        bookDAO.insertBook(newBook);
        response.sendRedirect("list");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));

        Book updatedBook = new Book(id, title, author, price);
        bookDAO.updateBook(updatedBook);
        response.sendRedirect("list");
    }

    private void listBooks(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
        List<Book> bookList = bookDAO.listAllBooks();
        request.setAttribute("bookList", bookList);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}