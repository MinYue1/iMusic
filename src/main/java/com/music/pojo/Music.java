package com.music.pojo;

import java.sql.Date;

public class Music {
    private int id;
    private String musicName; //歌名
    private int singerName; //歌手名
    private int musicClass; //分类
    private String size; //大小
    private int songArea; //地区
    private Date upLoadDate; //上传日期
    private int upLoader; //上传者
    private String filePath; //文件存储路径

    //多表查询
    private String singerNameToStr; //歌手名
    private String musicClassToStr; //分类
    private String songAreaToStr; //地区
    private String upLoaderToStr; //上传者


    public Music() {
    }

    public Music(int id, String musicName, int singerName, int musicClass, String size, int songArea, Date upLoadDate, int upLoader, String filePath, String singerNameToStr, String musicClassToStr, String songAreaToStr, String upLoaderToStr) {
        this.id = id;
        this.musicName = musicName;
        this.singerName = singerName;
        this.musicClass = musicClass;
        this.size = size;
        this.songArea = songArea;
        this.upLoadDate = upLoadDate;
        this.upLoader = upLoader;
        this.filePath = filePath;
        this.singerNameToStr = singerNameToStr;
        this.musicClassToStr = musicClassToStr;
        this.songAreaToStr = songAreaToStr;
        this.upLoaderToStr = upLoaderToStr;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", musicName='" + musicName + '\'' +
                ", singerName=" + singerName +
                ", musicClass=" + musicClass +
                ", size='" + size + '\'' +
                ", songArea=" + songArea +
                ", upLoadDate=" + upLoadDate +
                ", upLoader=" + upLoader +
                ", filePath='" + filePath + '\'' +
                ", singerNameToStr='" + singerNameToStr + '\'' +
                ", musicClassToStr='" + musicClassToStr + '\'' +
                ", songAreaToStr='" + songAreaToStr + '\'' +
                ", upLoaderToStr='" + upLoaderToStr + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public int getSingerName() {
        return singerName;
    }

    public void setSingerName(int singerName) {
        this.singerName = singerName;
    }

    public int getMusicClass() {
        return musicClass;
    }

    public void setMusicClass(int musicClass) {
        this.musicClass = musicClass;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSongArea() {
        return songArea;
    }

    public void setSongArea(int songArea) {
        this.songArea = songArea;
    }

    public Date getUpLoadDate() {
        return upLoadDate;
    }

    public void setUpLoadDate(Date upLoadDate) {
        this.upLoadDate = upLoadDate;
    }

    public int getUpLoader() {
        return upLoader;
    }

    public void setUpLoader(int upLoader) {
        this.upLoader = upLoader;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSingerNameToStr() {
        return singerNameToStr;
    }

    public void setSingerNameToStr(String singerNameToStr) {
        this.singerNameToStr = singerNameToStr;
    }

    public String getMusicClassToStr() {
        return musicClassToStr;
    }

    public void setMusicClassToStr(String musicClassToStr) {
        this.musicClassToStr = musicClassToStr;
    }

    public String getSongAreaToStr() {
        return songAreaToStr;
    }

    public void setSongAreaToStr(String songAreaToStr) {
        this.songAreaToStr = songAreaToStr;
    }

    public String getUpLoaderToStr() {
        return upLoaderToStr;
    }

    public void setUpLoaderToStr(String upLoaderToStr) {
        this.upLoaderToStr = upLoaderToStr;
    }
}
