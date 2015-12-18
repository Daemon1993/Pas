package com.daemon.framework.dutils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by user on 2014/12/10.
 */
public class FileUtil {

    private final static String TAG = FileUtil.class.getName();

    /**
     * 创建文件夹
     *
     * @param dirPath
     */
    public synchronized static boolean createDir(String dirPath) {
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return true;
    }

    /**
     * 删除文件
     *
     * @param path
     */
    public synchronized static void delete(final String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(path);
                if (!file.exists()) {
                    return;
                }
                // 若是文件则删除
                if (file.isFile()) {
                    file.delete();
                    return;
                }
                // 若是目录递归删除
                if (file.isDirectory()) {
                    File[] childFiles = file.listFiles();
                    for (File f : childFiles) {
                        f.delete();
                    }
                }
            }
        }).start();
    }


    /**
     * 本地文件转移
     * ImageLoader的缓存文件 抽取出来下载图片
     * @param context
     * @param file
     * @param file1
     */
    public static void saveFile(Context context,File file , File file1){

        OutputStream output = null;
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(file);
            output = new FileOutputStream(file1);
            byte[] buff = new byte[1024 * 4];
            while (true) {
                int readed = inputStream.read(buff);
                if (readed == -1) {
                    break;
                }
                //write buff
                output.write(buff, 0, readed);
            }
            output.flush();

            Toast.makeText(context,"下载完成",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            file = null;
            e.printStackTrace();

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //通知系统相册  更新
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file1);
            intent.setData(uri);
            context.sendBroadcast(intent);

        }
    }
}
