package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.media.*;
import com.grp08.capstoneprojectg08.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Use for query on tables media, book, cd, dvd
public interface MediaRepo extends BaseRepo{
    public List<Media> findAllMedias();

    public Media findMediaById(int id);

    public Media findMediaByTitle(String title);

    // find medias by category and search string
    // * Books
    public List<Book> findBooksFilterByTitle(String title);
    // * CDs
    public List<CD> findCDsFilterByTitle(String title);
    // * DVDs
    public List<DVD> findDVDsFilterByTitle(String title);

    // TODO: add media (for admin), also set mediaUrl to under MediaImages folder
    // * Book
    // * CD
    // * DVD


    // TODO: delete media by id (for admin)

}
