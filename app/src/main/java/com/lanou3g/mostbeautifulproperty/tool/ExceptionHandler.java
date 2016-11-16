package com.lanou3g.mostbeautifulproperty.tool;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lanou3g.mostbeautifulproperty.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 */

public class ExceptionHandler implements Thread.UncaughtExceptionHandler{

    public static final String TAG = "ExceptionHandler";
    private static final String ERROR_LOG = "thingError.log";
    private Activity mActivity;

    public ExceptionHandler(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        // 开启线程显示dialog
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                createDialog();
                Looper.loop();
            }
        }.start();

        // 存储异常
        StackTraceElement[] stackTrace = e.getStackTrace();
        File dir = Environment.getExternalStorageDirectory();
        File file = new File(dir, ERROR_LOG);
        OutputStream os = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            os = new FileOutputStream(file, true);
            os.write("--------------------------------\n".getBytes());
            os.write((e.getMessage() + "\n").getBytes());
            for (StackTraceElement element :
                    stackTrace) {
                byte[] bytes = (element.toString() + "\n").getBytes();
                Log.d(TAG, "element.toString()" + element.toString());
                os.write(bytes);
            }
            os.write("-------------------------------\n".getBytes());
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

    private void createDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(mActivity).create();
        View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_remove, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_clear_title);
        TextView tvSubTitle = (TextView) view.findViewById(R.id.tv_clear_search_history);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel_dialog);
        Button btnDetermine = (Button) view.findViewById(R.id.btn_determine_dialog);

        tvTitle.setText("程序崩溃");
        tvSubTitle.setText("关闭程序 / 重启程序");
        btnCancel.setText("关闭");
        btnDetermine.setText("重启");
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());    //获取PID
                System.exit(0);
                dialog.cancel();
            }
        });
        btnDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = mActivity.getPackageManager()
                        .getLaunchIntentForPackage(mActivity.getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mActivity.startActivity(i);

                dialog.cancel();
            }
        });
        dialog.setView(view);
        dialog.show();
    }
}
