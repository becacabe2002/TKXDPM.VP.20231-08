package com.grp08.capstoneprojectg08.entity.media;

import org.json.JSONObject;

import java.util.Date;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class DVD extends Media{
    private DiscType discType;
    private String director;
    private int runtime;
    private String studio;
    private String subtitle;
    private Date releaseDate;

    public DVD(int id, MediaCategory category, int price, int quantity, String title, int value, String imageUrl, boolean fastShipping, DiscType discType, String director, int runtime, String studio, String subtitle, Date releaseDate) {
        super(id, category, price, quantity, title, value, imageUrl, fastShipping);
        this.discType = discType;
        this.director = director;
        this.runtime = runtime;
        this.studio = studio;
        this.subtitle = subtitle;
        this.releaseDate = releaseDate;
    }

    public DiscType getDiscType() {
        return discType;
    }

    public void setDiscType(DiscType discType) {
        this.discType = discType;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    //override toString method
    @Override
    public String toString(){
        // no need title, price, quantity, imageUrl, fastShipping
        return "ID: " + this.ID + "\n\n" +
                "Category: " + this.category + "\n\n" +
                "Disc Type: " + this.discType + "\n\n" +
                "Director: " + this.director + "\n\n" +
                "Runtime: " + this.runtime + "\n\n" +
                "Studio: " + this.studio + "\n\n" +
                "Subtitle: " + this.subtitle + "\n\n" +
                "Release Date: " + this.releaseDate.toString() + "\n\n";
    }

    @Override
    public JSONObject toJSON(){
        JSONObject jsonObject = super.toJSON();
        jsonObject.put("discType", this.discType);
        jsonObject.put("director", this.director);
        jsonObject.put("runtime", this.runtime);
        jsonObject.put("studio", this.studio);
        jsonObject.put("subtitle", this.subtitle);
        jsonObject.put("releaseDate", this.releaseDate.toString());
        return jsonObject;
    }
}