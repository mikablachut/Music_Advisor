package org.music_advisor.model;

import java.util.Arrays;

public class Album {
    private String albumTitle;
    private String listOfArtist;
    private String link;

    public Album() {

    }

    public Album(String albumTitle, String listOfArtist, String link) {
        this.albumTitle = albumTitle;
        this.listOfArtist = listOfArtist;
        this.link = link;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getListOfArtist() {
        return listOfArtist;
    }

    public void setListOfArtist(String listOfArtist) {
        this.listOfArtist = listOfArtist;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return albumTitle + "\n" + listOfArtist + "\n" + link + "\n";
    }
}
