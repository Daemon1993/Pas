package com.daemon.framework.okhttp;

import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Daemon on 2015/12/23.
 */
public class FileUtils {

   // public static final String dir= Environment.getExternalStorageDirectory()+"/OkHttpDemo";

    /**
     * 保存本地
     * @param response
     * @param dir
     */
    public static File saveFile2Local(Response response, String dir ,String fileName) {

        InputStream inputStream = null;
        OutputStream output = null;
        File file;
        try {
            inputStream = response.body().byteStream();
            file = new File(dir, fileName);
            output = new FileOutputStream(file);
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

            return file;
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
        }

        return null;
    }
}
