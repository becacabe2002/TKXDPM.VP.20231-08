package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.media.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Use for query on tables media, book, cd, dvd
public class MediaRepo extends BaseRepo{
    public List<Media> findAllMedias(){
        List<Media> listMedia = new ArrayList<>();
        String script = "Select * from Media;";
        try{
            statement = dbConnection.prepareStatement(script);
            resultSet = statement.executeQuery(script);
            while(resultSet.next()){
                listMedia.add(new Media(
                        resultSet.getInt("id"),
                        MediaCategory.valueOf(resultSet.getString("category")),
                        resultSet.getInt("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("title"),
                        resultSet.getInt("value"),
                        resultSet.getString("imageUrl"),
                        resultSet.getBoolean("fastShipping")
                ));
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return listMedia;
    }

    public Media findMediaById(int id){
        String script = "Select * from Media where id = ? limit 1;";
        try{
            ppStatement = dbConnection.prepareStatement(script);
            ppStatement.setInt(1, id);
            resultSet = ppStatement.executeQuery();
            if(resultSet.next()){
                return new Media(
                        resultSet.getInt("id"),
                        MediaCategory.valueOf(resultSet.getString("category")),
                        resultSet.getInt("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("title"),
                        resultSet.getInt("value"),
                        resultSet.getString("imageUrl"),
                        resultSet.getBoolean("fastShipping")
                );
            } else return null;
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    // TODO: find medias by category and search string
    // * Books
    public List<Book> findBooksFilterByTitle(String title){
        List<Book> listBook = new ArrayList<>();
        String lowerTitle = title.toLowerCase();
        // merge 2 tables: Media and Book by id
        String script = "Select * from Media inner join Book on Media.id = Book.id where lower(Media.title) like ?;";
        try{
            ppStatement = dbConnection.prepareStatement(script);
            ppStatement.setString(1, "'%" + lowerTitle + "%'");
            resultSet = ppStatement.executeQuery();
            while(resultSet.next()){
                listBook.add(new Book(
                        resultSet.getInt("id"),
                        MediaCategory.valueOf(resultSet.getString("category")),
                        resultSet.getInt("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("title"),
                        resultSet.getInt("value"),
                        resultSet.getString("imageUrl"),
                        resultSet.getBoolean("fastShipping"),
                        resultSet.getString("author"),
                        CoverType.valueOf(resultSet.getString("coverType")),
                        resultSet.getString("publisher"),
                        resultSet.getDate("publishedDate"),
                        resultSet.getInt("numOfPages"),
                        resultSet.getString("language"),
                        resultSet.getString("bookCategory")
                ));
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());

        }
        return listBook;
    }
    // * CDs
    public List<CD> findCDsFilterByTitle(String title){
        List<CD> listCD = new ArrayList<>();
        String lowerTitle = title.toLowerCase();
        // merge 2 tables: Media and CD by id
        String script = "Select * from Media inner join CD on Media.id = CD.id where lower(Media.title) like ?;";
        try{
            ppStatement = dbConnection.prepareStatement(script);
            ppStatement.setString(1, "'%" + lowerTitle + "%'");
            resultSet = ppStatement.executeQuery();
            while(resultSet.next()){
                listCD.add(new CD(
                        resultSet.getInt("id"),
                        MediaCategory.valueOf(resultSet.getString("category")),
                        resultSet.getInt("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("title"),
                        resultSet.getInt("value"),
                        resultSet.getString("imageUrl"),
                        resultSet.getBoolean("fastShipping"),
                        resultSet.getString("artist"),
                        resultSet.getString("recordLabel"),
                        resultSet.getString("musicType"),
                        resultSet.getDate("releasedDate")
                ));
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());

        }
        return listCD;
    }
    // * DVDs
    public List<DVD> findDVDsFilterByTitle(String title){
        List<DVD> listDVD = new ArrayList<>();
        String lowerTitle = title.toLowerCase();
        // merge 2 tables: Media and DVD by id
        String script = "Select * from Media inner join DVD on Media.id = DVD.id where lower(Media.title) like ?;";
        try{
            ppStatement = dbConnection.prepareStatement(script);
            ppStatement.setString(1, "'%" + lowerTitle + "%'");
            resultSet = ppStatement.executeQuery();
            while(resultSet.next()){
                listDVD.add(new DVD(
                        resultSet.getInt("id"),
                        MediaCategory.valueOf(resultSet.getString("category")),
                        resultSet.getInt("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("title"),
                        resultSet.getInt("value"),
                        resultSet.getString("imageUrl"),
                        resultSet.getBoolean("fastShipping"),
                        DiscType.valueOf(resultSet.getString("discType")),
                        resultSet.getString("director"),
                        resultSet.getInt("runtime"),
                        resultSet.getString("studio"),
                        resultSet.getString("subtitle"),
                        resultSet.getDate("releaseDate")
                ));
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());

        }
        return listDVD;
    }

    // TODO: add media (for admin)
    // * Book
    // * CD
    // * DVD


    // TODO: delete media by id (for admin)

}
