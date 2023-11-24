package com.grp08.capstoneprojectg08.entity.media;


import java.util.Date;

public class Book extends Media{
    private String author;
    private CoverType coverType;
    private String publisher;
    private Date publishedDate;
    private int numOfPages;
    private String language;
    private String bookCategory;

    public Book(int id, MediaCategory category, int price, int quantity, String title, int value, String imageUrl, boolean fastShipping, String author, CoverType coverType, String publisher, Date publishedDate, int numOfPages, String language, String bookCategory) {
        super(id, category, price, quantity, title, value, imageUrl, fastShipping);
        this.author = author;
        this.coverType = coverType;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.numOfPages = numOfPages;
        this.language = language;
        this.bookCategory = bookCategory;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public CoverType getCoverType() {
        return coverType;
    }

    public void setCoverType(CoverType coverType) {
        this.coverType = coverType;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        // no need title, price, quantity, imageUrl, fastShipping
        sb.append("ID: ").append(this.ID).append("\n");
        sb.append("Category: ").append(this.category).append("\n");
        sb.append("Book Category: ").append(this.bookCategory).append("\n");
        sb.append("Author: ").append(this.author).append("\n");
        sb.append("Cover Type: ").append(this.coverType).append("\n");
        sb.append("Publisher: ").append(this.publisher).append("\n");
        sb.append("Published Date: ").append(this.publishedDate.toString()).append("\n");
        sb.append("Number of Pages: ").append(this.numOfPages).append("\n");
        sb.append("Language: ").append(this.language).append("\n");
        return sb.toString();
    }
}
