package com.mike.triggasounds.triggasounds.model;

/**
 * Created by e590436 on 3/18/14.
 */
public class Track {

    private int icon;
    private String name;

    public Track() {
        super();
    }

    public Track(int icon, String namee) {
        super();
        this.icon = icon;
        this.name = namee;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
