package com.music.services;

import com.music.dao.IupFileDao;
import com.music.pojo.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpFileServices {
    @Autowired
    private IupFileDao dao;

    /**
     * 查找当前登录用户 历史上传歌曲
     * @param username
     * @return
     */
    public List<Music> upMusiclists(String username){
        return dao.upMusiclists(username);
    }
}
