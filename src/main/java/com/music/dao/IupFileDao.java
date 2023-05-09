package com.music.dao;

import com.music.pojo.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IupFileDao {

    List<Music> upMusiclists(@Param("username") String username);
}
