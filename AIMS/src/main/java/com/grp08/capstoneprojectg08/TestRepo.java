package com.grp08.capstoneprojectg08;

import com.grp08.capstoneprojectg08.entity.media.Book;
import com.grp08.capstoneprojectg08.entity.media.DVD;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.repository.MediaRepo;
import com.grp08.capstoneprojectg08.repository.MediaRepoImplement;

import java.util.List;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class TestRepo {
    public static void main(String[] args) {
        MediaRepo mediaRepo = new MediaRepoImplement();
        List<DVD> mediaList = mediaRepo.findDVDsFilterByTitle("");
        for(DVD dvd : mediaList){
            System.out.println(dvd.getTitle());
        }

    }
}
