package com.study.example.rest.demo;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    /**
     * 删除文件
     *
     * @param pathname
     * @return
     * @throws IOException
     */
    public static boolean deleteFile(String pathname){
        boolean result = false;
        File file = new File(pathname);
        if (file.exists()) {
            file.delete();
            if(!file.exists()){
                result = true;
                System.out.println("文件已经被成功删除");
            }
        }
        return result;
    }
}
