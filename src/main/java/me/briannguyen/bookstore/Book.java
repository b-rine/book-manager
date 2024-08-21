package me.briannguyen.bookstore;

public class Book {

    private int id;
    private float price;
    private String title;
    private String author;

    public Book() {}

    public Book(int id) {
        this.id = id;
    }

    public Book(String title, String author, float price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public Book(int id, String title, String author, float price) {
        this(title, author, price);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
