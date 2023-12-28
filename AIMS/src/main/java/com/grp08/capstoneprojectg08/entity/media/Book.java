package com.grp08.capstoneprojectg08.entity.media;


import org.json.JSONObject;

import java.util.Date;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
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
        // no need title, price, quantity, imageUrl, fastShipping
        return "ID: " + this.ID + "\n" +
                "Category: " + this.category + "\n" +
                "Book Category: " + this.bookCategory + "\n" +
                "Author: " + this.author + "\n" +
                "Cover Type: " + this.coverType + "\n" +
                "Publisher: " + this.publisher + "\n" +
                "Published Date: " + this.publishedDate.toString() + "\n" +
                "Number of Pages: " + this.numOfPages + "\n" +
                "Language: " + this.language + "\n";
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put("author", this.author);
        jsonObject.put("coverType", this.coverType);
        jsonObject.put("publisher", this.publisher);
        jsonObject.put("publishedDate", this.publishedDate.toString());
        jsonObject.put("numOfPages", this.numOfPages);
        jsonObject.put("language", this.language);
        jsonObject.put("bookCategory", this.bookCategory);
        return jsonObject;
    }
}
