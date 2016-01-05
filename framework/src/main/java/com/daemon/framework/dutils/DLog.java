package com.daemon.framework.dutils;

import android.util.Log;

/**
 * Created by Daemon on 2015/12/28.
 */
public class DLog {
    public static final String NULL_TIPS = "Log with null object";
    public static final String PARAM = "Param";
    public static final String NULL = "null";

    public static final String TAG = "Daemon- ";
    public static boolean ENABLE = true;

//    //每次只有第一次加上前缀 后续不加
//    private static int countOfSub;


    public static void e(String msg) {

        printLog(TAG, msg);
    }

    private static void printLog(String tagStr, Object... objects) {

        if (!ENABLE) {
            return;
        }

        String[] contents = wrapperContent(tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

        printDefault(tag, headString + msg);

    }

    public static void printDefault(String tag, String msg) {

        int index = 0;
        int maxLength = 4000;
        int countOfSub = msg.length() / maxLength;

        if (countOfSub > 0) {
            int countOfSub1 = countOfSub;
            for (int i = 0; i < countOfSub1; i++) {
                String sub = msg.substring(index, index + maxLength);
                printSub(tag, sub);
                index += maxLength;
            }
            printSub(tag, msg.substring(index, msg.length()));
        } else {
            //4000以内
            printSub(tag, msg);
        }
    }

    private static void printSub(String tag, String sub) {

        Log.e(tag, sub);

    }

    private static String[] wrapperContent(String tagStr, Object... objects) {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int index = 5;
        String className = stackTrace[index].getFileName();
        String methodName = stackTrace[index].getMethodName();
        int lineNumber = stackTrace[index].getLineNumber();
        String methodNameShort = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ (").append(className).append(":").append(lineNumber).append(")#").append(methodNameShort).append(" ] ");

        String tag = (tagStr == null ? className : tagStr);
        String msg = (objects == null) ? NULL_TIPS : getObjectsString(objects);
        String headString = stringBuilder.toString();

        return new String[]{tag, msg, headString};
    }

    private static String getObjectsString(Object... objects) {

        if (objects.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            for (int i = 0; i < objects.length; i++) {
                Object object = objects[i];
                if (object == null) {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(NULL).append("\n");
                } else {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(object.toString()).append("\n");
                }
            }
            return stringBuilder.toString();
        } else {
            Object object = objects[0];
            return object == null ? NULL : object.toString();
        }
    }

}
