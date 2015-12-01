package com.daemon.pas.utils;

import java.io.File;

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
}
