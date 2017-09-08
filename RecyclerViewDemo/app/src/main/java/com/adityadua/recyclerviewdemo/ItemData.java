package com.adityadua.recyclerviewdemo;

/**
 * Created by AdityaDua on 08/09/17.
 */

public class ItemData {

    private String title;
    private int imageURL;

    public ItemData(String title, int imageURL) {
        this.title = title;
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageURL() {
        return imageURL;
    }

    public void setImageURL(int imageURL) {
        this.imageURL = imageURL;
    }
}
