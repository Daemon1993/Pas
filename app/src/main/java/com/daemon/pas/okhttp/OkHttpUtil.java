package com.daemon.pas.okhttp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.socks.library.KLog;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class OkHttpUtil {

    private Handler mainHanlder;
    public OkHttpClient mOkHttpClient;

    public  Gson gson;



    private static class OkHttpUtilHolder{
        public static OkHttpUtil mInstance=new OkHttpUtil();

    }

    private OkHttpUtil() {
        mOkHttpClient = new OkHttpClient();
        // mOkHttpClient.networkInterceptors().add(new StethoInterceptor());

        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(20, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(30, TimeUnit.SECONDS);

        gson=new Gson();

        //更新UI线程
        mainHanlder = new Handler(Looper.getMainLooper());

    }


    public Gson getGson(){
        return gson;
    }

    public static OkHttpUtil getInstance() {
        return OkHttpUtilHolder.mInstance;
    }


    public interface MyCallBack {
        void onFailure(Request request, IOException e);

        void onResponse(String json);
    }

    public static MyCallBack myCallBack;

    public void addGetResult(MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
    }

    public static void setCookie(CookieManager cookieManager) {
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        getInstance().mOkHttpClient.setCookieHandler(cookieManager);
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final String CHARSET_NAME = "UTF-8";

	/*------------------------------------------------Daemon------------------------------------------------------------------------*/

    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType
            .parse("text/x-markdown; charset=utf-8");


    public void getData4Server(Request request, final MyCallBack myCallBack) {

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                KLog.e(e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String json = response.body().string();

                KLog.json(json);

//                File file = new File(BaseRequest.App_Dir);
//                FileUtil.createDir(file.getPath());
//
//                KLog.file(file, json);

                mainHanlder.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallBack.onResponse(json);
                    }
                });
            }
        });

    }

    /**
     * @param localFilePath 上传文件地址 如果想后台上传 开启服务Service来处理
     * @param url           服务器地址
     * @throws Exception
     */
    public static void postFile2Server(String localFilePath, String url)
            throws Exception {
        File file = new File(localFilePath);

        Request request = new Request.Builder().url(url)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file)).build();

        Response response = getInstance().mOkHttpClient.newCall(request).execute();

        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());
    }


    /**
     * 提交数据表单到服务器 异步
     *
     * @param url
     * 服务器地址
     * @param formBody
     * RequestBody formBody = new FormEncodingBuilder() .add("key",
     * "map").build();
     * @param AcceptObjectl
     * 通知 的对象
     * @throws Exception
     */

    public static String sessionId = "";

    /**
     * * @param url
     * 服务器地址
     *
     * @param formBody      RequestBody formBody = new FormEncodingBuilder() .add("key",
     * "map").build();
     * @param AcceptObjectl 通知 的对象
     * @param isGetCookie   是否获取cookie
     * @throws Exception
     * @throws Exception
     */
    public static String COOKIE = "cookie";

    private void getCookie(Response response, String url) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        //获取cookie 然后打印  绑定
        try {

            List<String> values = response.headers().values("Set-Cookie");

            map = getInstance().mOkHttpClient.getCookieHandler().get(URI.create(url), response.headers().toMultimap());
            for (String key : map.keySet()) {
                if ("Cookie".equals(key)) {
                    sessionId = map.get(key).get(0);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 异步回调方式 post请求  自定义回调接口  结果运行在UI线程
     *
     * @param request
     * @throws Exception
     */
    public void postData2Server(Request request, final MyCallBack myCallBack) {
        try {
            getInstance().mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    KLog.e(e.getMessage());
                }

                @Override
                public void onResponse(Response response) {
                    try {
                        final String json = response.body().string();

                        mainHanlder.post(new Runnable() {
                            @Override
                            public void run() {
                                myCallBack.onResponse(json);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 下载显示进度的方法1
     *
     * @param url
     */
    public static void dowloadProgress(Context context, final String url) {

        Request request = new Request.Builder().url(url)
                .build();
        new AsyncDownloader(context).execute(request);

    }

    private static class AsyncDownloader extends AsyncTask<Request, Long, Boolean> {

        private Context context;

        public AsyncDownloader(Context context) {
            this.context = context;
        }

        @Override
        protected Boolean doInBackground(Request... params) {

            Call call = getInstance().mOkHttpClient.newCall(params[0]);
            try {
                Response response = call.execute();
                if (response.code() == 200) {
                    InputStream inputStream = null;
                    OutputStream output = null;
                    try {
                        inputStream = response.body().byteStream();
                        File file = new File(context.getCacheDir(), "download.liubo");
                        output = new FileOutputStream(file);

                        byte[] buff = new byte[1024 * 4];
                        long downloaded = 0;
                        long target = response.body().contentLength();

                        publishProgress(0L, target);
                        while (true) {
                            int readed = inputStream.read(buff);
                            if (readed == -1) {
                                break;
                            }
                            //write buff
                            output.write(buff, 0, readed);

                            downloaded += readed;
                            publishProgress(downloaded, target);

                            if (isCancelled()) {
                                return false;
                            }
                        }
                        output.flush();

                        return downloaded == target;
                    } catch (IOException ignore) {
                        return false;
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (output != null) {
                            output.close();
                        }
                    }
                } else {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onProgressUpdate(Long... values) {


            KLog.e(String.format("%d / %d", values[0], values[1]));

        }

        @Override
        protected void onPostExecute(Boolean result) {
            //下载完成后
        }
    }


    /**
     * 上传文件方法1   进度显示
     */
    public static RequestBody createCustomRequestBody(final MediaType contentType, final File file) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return contentType;
            }

            @Override
            public long contentLength() {
                return file.length();
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                Source source = null;
                try {
                    source = Okio.source(file);
                    //sink.writeAll(source);
                    Buffer buf = new Buffer();
                    Long remaining = contentLength();
                    for (long readCount; (readCount = source.read(buf, 2048)) != -1; ) {
                        sink.write(buf, readCount);
                        KLog.e("source size: " + contentLength() + " remaining bytes: " + (remaining -= readCount));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

}