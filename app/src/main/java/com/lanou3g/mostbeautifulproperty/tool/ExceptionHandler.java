package com.lanou3g.mostbeautifulproperty.tool;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 */

public class ExceptionHandler implements Thread.UncaughtExceptionHandler{

    private static final String ERROR_LOG = "thingError.log";
    private Activity mActivity;

    public ExceptionHandler(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        File dir = Environment.getExternalStorageDirectory();
        File file = new File(dir, ERROR_LOG);
        OutputStream os = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            os = new FileOutputStream(file, true);
            os.write("----------\n".getBytes());
            os.write((e.getMessage() + "\n").getBytes());
            for (StackTraceElement element :
                    stackTrace) {
                byte[] bytes = (element.toString() + "\n").getBytes();
                Log.d("ExceptionHandler", "bytes:" + bytes);
                os.write(bytes);
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
