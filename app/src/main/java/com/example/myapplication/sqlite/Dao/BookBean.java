package com.example.myapplication.sqlite.Dao;

public class BookBean {
    // 常量
    public final static String FILED_ID = "_id";
    public final static String FILED_BOOKNAME = "bookname";
    public final static String FILED_WRITER = "writer";
    public final static String FILED_PRESS = "press";
    public final static String FILED_PRICE = "price";
    /**
     * id号
     */
    private int id;
    /**
     * 书名
     */
    private String bookname;
    /**
     * 作者
     */
    private String writer;
    /**
     * 出版社
     */
    private String press;
    /**
     * 价格
     */
    private double price;

    public BookBean() {
    }

    public BookBean(int id, String bookname, String writer, String press, double price) {
        this.id = id;
        this.bookname = bookname;
        this.writer = writer;
        this.press = press;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
