package com.duma.liudong.meiye.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.zhy.http.okhttp.utils.L;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Thinkpad on 2016/6/12.
 */
public class GetFileUtil {
    public static File getFile() {
        // 创建一个媒体文件的名字
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
//                .format(new Date());
        File mediaFile = new File(getPhonoFilePath() + File.separator
                + "IMG_demo.jpg");

        return mediaFile;
    }

    //返回电影文件路径
    @NonNull
    public static String getMovieFileName(String FileName) {
        File mediaFile = new File(getMovieFilePath() + File.separator
                + FileName);
        return mediaFile.getPath();
    }

    //返回图片目录路径
    @NonNull
    public static String getPhonoFilePath() {
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MeiYe");
        if (initFileDir(mediaStorageDir)) return null;

        return mediaStorageDir.getPath();
    }

    public static boolean initFileDir(File mediaStorageDir) {
        // 如果它不存在创建存储目录
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                // 在SD卡上创建文件夹需要权限：
                // <uses-permission
                // android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
                L.e("无法创建目录,检查如果你有WRITE_EXTERNAL_STORAGE许可");
                return true;
            }
        }
        return false;
    }

    //返回电影目录路径
    @NonNull
    public static String getMovieFilePath() {
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES),
                "ZiMuKouMovie");
        if (initFileDir(mediaStorageDir)) return null;
        return mediaStorageDir.getPath();
    }

    /**
     * 生成图片到指定的路径
     */
    public static String getFilePath(Bitmap bitmap, String path) {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(path);
        } catch (FileNotFoundException e) {

        } finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
        return path;
    }
}
