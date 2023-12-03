package com.grp08.capstoneprojectg08.entity.media;

import java.util.Date;

public class CD extends Media{
    private String artist;
    private String recordLabel; // hang ghi am
    private String musicType;
    private Date releasedDate;

    public CD(int id, MediaCategory category, int price, int quantity, String title, int value, String imageUrl, boolean fastShipping, String artist, String recordLabel, String musicType, Date releasedDate) {
        super(id, category, price, quantity, title, value, imageUrl, fastShipping);
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.musicType = musicType;
        this.releasedDate = releasedDate;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getRecordLabel() {
        return recordLabel;
    }

    public void setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
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
