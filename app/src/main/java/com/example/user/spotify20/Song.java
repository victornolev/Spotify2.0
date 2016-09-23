package com.example.user.spotify20;


public class Song {

    private int number;
    private String title;
    private String artist;
    private String songDirectory;

    public Song(){
    }

    public Song(int number, String title, String artist, String songDirectory){
        this.number = number;
        this.title = title;
        this.artist = artist;
        this.songDirectory = songDirectory;
    }

    public int getNumber() {
        return number;
    }
    public void SetNumber(int number){
        this.number = number;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getArtist(){
        return artist;
    }
    public void setArtist(String artist){
        this.artist = artist;
    }

    public String getSongDirectory(){
        return songDirectory;
    }
    public void setSongDirectory(){
        this.songDirectory = songDirectory;
    }

}
