package com.grp08.capstoneprojectg08.entity.media;

import org.json.JSONObject;

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
        // no need title, price, quantity, imageUrl, fastShipping
        return "ID: " + this.ID + "\n" +
                "Category: " + this.category + "\n" +
                "Artist: " + this.artist + "\n" +
                "Record Label: " + this.recordLabel + "\n" +
                "Music Type: " + this.musicType + "\n" +
                "Released Date: " + this.releasedDate.toString() + "\n";
    }

    @Override
    public JSONObject toJSON(){
        JSONObject jsonObject = super.toJSON();
        jsonObject.put("artist", this.artist);
        jsonObject.put("recordLabel", this.recordLabel);
        jsonObject.put("musicType", this.musicType);
        jsonObject.put("releasedDate", this.releasedDate.toString());
        return jsonObject;
    }

}
