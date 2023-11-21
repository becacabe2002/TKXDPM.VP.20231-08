package com.grp08.capstoneprojectg08.entity.media;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CD extends Media{
    private String artist;
    private String recordLabel; // hang ghi am
    private String musicType;
    private Date releasedDate;

    public CD(int id, String category, int price, int quantity, String title, int value, String imageUrl, boolean fastShipping, String artist, String recordLabel, String musicType, Date releasedDate) {
        super(id, category, price, quantity, title, value, imageUrl, fastShipping);
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.musicType = musicType;
        this.releasedDate = releasedDate;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        // no need title, price, quantity, imageUrl, fastShipping
        sb.append("ID: ").append(this.ID).append("\n");
        sb.append("Category: ").append(this.category).append("\n");
        sb.append("Artist: ").append(this.artist).append("\n");
        sb.append("Record Label: ").append(this.recordLabel).append("\n");
        sb.append("Music Type: ").append(this.musicType).append("\n");
        sb.append("Released Date: ").append(this.releasedDate.toString()).append("\n");
        return sb.toString();
    }

}
