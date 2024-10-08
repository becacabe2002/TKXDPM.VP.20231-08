package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.media.*;
import com.grp08.capstoneprojectg08.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 * <br> Used for query on tables media, book, cd, dvd
 */
public interface MediaRepo extends BaseRepo{
    public List<Media> findAllMedias();

    public Media findMediaById(int id);

    public List<Media> findAllMediaByTitle(String title);

    // find medias by category and search string
    // * Books
    public List<Book> findBooksFilterByTitle(String title);
    public Book findBookById(int id);

    // * CDs
    public List<CD> findCDsFilterByTitle(String title);
    public CD findCDById(int id);

    // * DVDs
    public List<DVD> findDVDsFilterByTitle(String title);
    public DVD findDVDById(int id);

    public void reduceMediaQuantity(int mediaId, int quantity);

}
