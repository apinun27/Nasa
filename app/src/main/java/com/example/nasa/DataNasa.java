package com.example.nasa;

public class DataNasa {
    public DataNasa(String date, String explanation, String hdurl, String copyright, String media_type, String title) {
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.copyright = copyright;
        this.media_type = media_type;
        this.title = title;
    }

    private String date;

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getTitle() {
        return title;
    }

    private String explanation;
    private String hdurl;
    private String copyright;
    private String media_type;
    private String title;
}
