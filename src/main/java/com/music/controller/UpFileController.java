package com.music.controller;

import com.mpatric.mp3agic.*;
import com.music.pojo.Music;
import com.music.pojo.User;
import com.music.services.MusicServices;
import com.music.services.UpFileServices;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@SessionAttributes("userSession")
public class UpFileController {

    @Autowired
    private MusicServices musicServices;
    @Autowired
    private UpFileServices upFileServices;

    //静态资源文件夹目录
    String mp3forstatic = "src//main//resources//static//audio//";
    String mp3fortarget = "target//classes//static//audio//";
    //创建music对象
    Music music = new Music();


    @PostMapping("/upMusicfile")
    public String upMusicfile(Map map,MultipartFile file, HttpServletRequest request,String musicName,String singerName,String musicClass,String songArea) throws IOException {

        if(upfile(file,map,request,musicName,singerName,musicClass,songArea) == true){
            System.out.println("添加歌曲成功");
        }


        //查询所有歌手
        map.put("singerAll",musicServices.singerAll());
        //查询所有歌曲信息
        map.put("musiclist",musicServices.fandAll());

        return "admin/musiclist.html";
    }
    @PostMapping("/upfile")
    @ResponseBody
    public boolean upfile(@RequestBody MultipartFile file, Map map, HttpServletRequest request,String musicName,String singerName,String musicClass,String songArea)throws IOException {//MultipartFile 接收前端传过来的文件
        System.out.println("iIiicomein:"+file);

        //获取session
        HttpSession session = request.getSession();
        User username = (User)session.getAttribute("userSession");
        System.out.println("当前登录用户"+username);


        //获取文件名，不带拓展名
        String uuid = getSubString(file.getOriginalFilename(),".mp3");

        //判断是不是mp3文件,是保存临时文件/不是结束
        if(ifmp3(file,uuid) == false)return false;

        //获取mp3歌曲歌手名等信息，重新设置文件名
        analMp3(uuid);

        //保存文件到static和target
        File targetFile = new File(mp3forstatic, music.getMusicName() + ".mp3");
        File targetFile1 = new File(mp3fortarget, music.getMusicName() + ".mp3");
        FileUtils.writeByteArrayToFile(targetFile, file.getBytes());
        FileUtils.writeByteArrayToFile(targetFile1, file.getBytes());

        //获取输入的音乐信息
        if(!"".equals(musicName) && musicName != null)music.setMusicName(musicName);
        if(!"".equals(singerName)&& singerName != null)music.setSingerNameToStr(singerName);
        if(!"".equals(musicClass)&& musicClass != null)music.setMusicClassToStr(musicClass);
        if(!"".equals(songArea)&& songArea != null)music.setSongAreaToStr(songArea);


        //获取临时文件
        File delete = new File(mp3forstatic+uuid+".mp3");

        //添加到数据库
        music.setFilePath("audio/"); //保存路径
        //获取文件大小，保留1位小数
        music.setSize(String.format("%.1f",((double) delete.length())/1000.0/1000.0)+"Mb");
        //设置上传者id
        if(username != null){
            music.setUpLoaderToStr(username.getUserName());
        }
        System.out.println("1:"+music);
        if(musicServices.insOneMusic(music) > 0){
            map.put("message","上传成功");
        }else{
            map.put("message","上传失败");
        }


        //销毁临时文件
        if(delete.delete()){
            System.out.println("删除临时文件成功");
        }else{
            System.out.println("删除临时文件失败");

        }
        return true;
    }

    //是否为mp3文件
    public boolean ifmp3(MultipartFile file,String savename){ //上传的文件，保存的名字
        //解析mp3文件
        if(file != null){
            System.out.println("已接收");
            try {
                // 随机生成文件名称
                //String uuid = UUID.randomUUID().toString();

                // 得到上传文件后缀
                String originalName = file.getOriginalFilename();
                String ext = "." + FilenameUtils.getExtension(originalName);
                if(!".mp3".equals(ext)){ //判断是不是mp3类型文件
                    System.out.println(":"+ext);
                    return false;
                }
                else{//是mp3文件，保存临时文件
                    File targetFile = new File(mp3forstatic, savename + ext);
                    FileUtils.writeByteArrayToFile(targetFile, file.getBytes());
                }

            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    //获取mp3专辑封面等信息
    public void analMp3(String uuid)throws IOException{
        try {
            Mp3File mp3file = new Mp3File(mp3forstatic+uuid+".mp3");

            if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();

                // ---- 设置歌曲名和歌手信息，如果mp3头文件有的话 ----
                if(id3v2Tag.getTitle() != null){//歌曲名
                    music.setMusicName(id3v2Tag.getTitle());
                }
                else if(uuid.contains("-")){ //文件名包含 '-' 时
                    music.setMusicName(uuid.substring(0,getline(uuid)-1)); //设置歌曲名
                }
                else{ //默认文件名为歌曲名
                    music.setMusicName(uuid);
                }

                if(id3v2Tag.getArtist() != null){//歌手名
                    music.setSingerNameToStr(id3v2Tag.getArtist());
                }
                else if(uuid.contains("-")){
                    music.setSingerNameToStr(uuid.substring(getline(uuid)+1));//设置歌手名
                }
                else{//默认歌手名为空
                    music.setSingerNameToStr(null);
                }

                //设置文件名统一
                uuid = music.getMusicName();

                //获取mp3封面
                byte[] imageData = id3v2Tag.getAlbumImage();
                //获取并复制内嵌图片
                if (imageData != null) {
                    System.out.println("有内嵌图片");
                    String mimeType = id3v2Tag.getAlbumImageMimeType();
                    System.out.println("!!:!:!:"+mimeType);
                    RandomAccessFile fileimg = new RandomAccessFile(mp3forstatic+"musicpage//"+uuid+".jpg", "rw");
                    RandomAccessFile fileimg1 = new RandomAccessFile(mp3fortarget+"musicpage//"+uuid+".jpg", "rw");
                    fileimg.write(imageData);
                    fileimg1.write(imageData);
                    fileimg.close();
                    fileimg1.close();
                }

                System.out.println(":hh:\n"+music);

            }

        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }


    }


    //获取歌手名(废弃
    private String getsonger(String uuid) {
        int i = 0;
        while(i < uuid.length())if(uuid.charAt(i++) == '-')break;
        return uuid.substring(i);
    }

    //获取'-'的位置
    private int getline(String uuid) {
        int i = 0;
        while(i < uuid.length())if(uuid.charAt(i++) == '-')break;
        if(i > uuid.length())return 0;
        return i-1;
    }

    //去掉文件拓展名
    public String getSubString(String str1,String str2){
        StringBuffer sb = new StringBuffer(str1);
        while (true) {
            int index = sb.indexOf(str2);
            if (index == -1) {
                break;
            }
            sb.delete(index, index + str2.length());
        }
        return sb.toString();
    }


    @GetMapping("/upMusiclists")
    public String upMusiclists(HttpServletRequest request,Map map){
        System.out.println("i come in");
        //获取session
        HttpSession session = request.getSession();
        User username = (User)session.getAttribute("userSession");
        System.out.println("当前登录用户"+username);

        //
//        Map map = new HashMap<String,Object>();
        map.put("upmusiclist",upFileServices.upMusiclists(username.getUserName()));

        return "upload.html";

    }


}
