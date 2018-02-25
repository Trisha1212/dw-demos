package com.wwc.sg.dw.representations;

import java.util.concurrent.atomic.AtomicInteger;

public class Book {

    private static AtomicInteger ctr = new AtomicInteger(0);

    private int id;
    private String title;
    private String author;

    public Book(String title, String author) {
        this.id = ctr.incrementAndGet();
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
