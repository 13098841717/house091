package com.team.house.utils;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.nio.file.Path;

public class FileUploadUtil {
    private static final String oldpath="f:\\images\\";

    public static String upload(CommonsMultipartFile cp){
        try {
            String oldName=cp.getOriginalFilename();
            String suffix=oldName.substring(oldName.indexOf("."));
            //以时间毫秒值生成文件名
            String prefix=System.currentTimeMillis()+"";
            //拼接形成文件名
            String newName=prefix+suffix;
            String path= oldpath+newName;
            //存储
            File file=new File(path);
            //上传文件
            cp.transferTo(file);
            return newName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static boolean deletePath(String path){
        File file=new File(oldpath+path);
        return file.delete();
    }
}
