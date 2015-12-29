package com.daemon.framework.okhttp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DOkHttp {

    private Handler mainHanlder;
    public OkHttpClient mOkHttpClient;

    public  Gson gson;


    private static class OkHttpUtilHolder{
        public static DOkHttp mInstance=new DOkHttp();

    }

    private DOkHttp() {
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

    public static DOkHttp getInstance() {
        return OkHttpUtilHolder.mInstance;
    }


    public interface MyCallBack {
        void onFailure(Request request, IOException e);

        void onResponse(String json);

    }

    public interface MyCallBack_Progress {
        void onFailure(Request request, IOException e);

        void onResponse(Response response);


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

    public void getData4Server(Request request, final MyCallBack myCallBack) {

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String json = response.body().string();

                //DLog.e(json);

                mainHanlder.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallBack.onResponse(json);
                    }
                });
            }
        });
    }






    public static String sessionId = "";


    /**
     * 获取cookie
     * @param response
     * @param url
     */
    private Map getCookie(Response response, String url) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        //获取cookie 然后打印  绑定
        try {
            List<String> values = response.headers().values("Set-Cookie");
            map = getInstance().mOkHttpClient.getCookieHandler().get(URI.create(url), response.headers().toMultimap());

//            for (String key : map.keySet()) {
//                if ("Cookie".equals(key)) {
//                    sessionId = map.get(key).get(0);
//                }
//            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  map;
    }

    /**
     * 异步回调方式 post请求  自定义回调接口  结果运行在UI线程
     *json 也可以
     * @param request
     * @throws Exception
     */
    public void postData2Server(Request request, final MyCallBack myCallBack) {
        try {
            getInstance().mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

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


    public interface UIchangeListener{
        void progressUpdate(long bytesWrite, long contentLength, boolean done);
    }

    /**
     * 上传文件
     * 也可以和数据post一起提交 监听progress
     * @param requestBody
     * @param uIchangeListener
     */
    public void uploadPost2ServerProgress(Context context,String url, RequestBody requestBody,
                                          MyCallBack myCallBack, final UIchangeListener uIchangeListener){

        ProgressRequestBody progressRequestBody=ProgressHelper.addProgressRequestListener(requestBody, new UIProgressRequestListener() {
            @Override
            public void onUIRequestProgress(long bytesWrite, long contentLength, boolean done) {
                uIchangeListener.progressUpdate(bytesWrite,contentLength,done);
            }
        });

        Request request=new Request.Builder()
                .tag(context)
                .post(progressRequestBody)
                .url(url)
                .build();

        postData2Server(request,myCallBack);

    }

    /**
     * 下载监听
     * @param request
     * @param myCallBack
     * @param uIchangeListener
     */
    public void download4ServerListener(Request request, final MyCallBack_Progress myCallBack,
                                        final UIchangeListener uIchangeListener){

        ProgressHelper.addProgressResponseListener(getInstance().mOkHttpClient, new UIProgressResponseListener() {
            @Override
            public void onUIResponseProgress(long bytesRead, long contentLength, boolean done) {
                uIchangeListener.progressUpdate(bytesRead,contentLength,done);
            }
        }).newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                myCallBack.onResponse(response);
            }
        });


    }


}