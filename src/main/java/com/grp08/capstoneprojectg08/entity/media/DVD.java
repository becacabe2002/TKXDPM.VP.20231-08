package com.grp08.capstoneprojectg08.entity.media;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
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
    //override toString method
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        // no need title, price, quantity, imageUrl, fastShipping
        sb.append("ID: ").append(this.ID).append("\n");
        sb.append("Category: ").append(this.category).append("\n");
        sb.append("Disc Type: ").append(this.discType).append("\n");
        sb.append("Director: ").append(this.director).append("\n");
        sb.append("Runtime: ").append(this.runtime).append("\n");
        sb.append("Studio: ").append(this.studio).append("\n");
        sb.append("Subtitle: ").append(this.subtitle).append("\n");
        sb.append("Release Date: ").append(this.releaseDate.toString()).append("\n");
        return sb.toString();
    }
}