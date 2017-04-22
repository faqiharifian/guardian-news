package com.arifian.udacity.guardiannews.entities;

/**
 * Created by faqih on 22/04/17.
 */

public class News {
    private String thumbnail, title, section, date, link;

    public News(String thumbnail, String title, String section, String date, String link) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.section = section;
        this.date = date;
        this.link = link;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public String getDate() {
        return date;
    }

    public String getLink() {
        return link;
    }
}
