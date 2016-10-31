package com.lanou3g.mostbeautifulproperty.dbtool;

;import android.os.Handler;
import android.os.Looper;

import com.lanou3g.mostbeautifulproperty.app.MostBeautifulApp;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dllo on 16/10/27.
 */

public class DBTools {
    private static DBTools sDBTools;
    private LiteOrm mLiteOrm;
    // 线程池
    private ExecutorService threadPool;
    // 用来做线程切换的
    private Handler mHandler;
    private static String DBTOOL_NAME = "mostBeautiful.db";

    private DBTools() {
        mLiteOrm = LiteOrm.newSingleInstance(MostBeautifulApp.getContext(), DBTOOL_NAME);
        threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        // 构建handler的时候, 参数传
        // Looper.getMainLooper()
        // 这样可以保证, 该handler 一定属于主线程
        mHandler = new Handler(Looper.getMainLooper());
        //        Message msg = mHandler.obtainMessage();
    }

    public static DBTools getInstance(){
        if (sDBTools == null) {
            synchronized (DBTools.class){
                if (sDBTools == null) {
                    sDBTools = new DBTools();
                }
            }
        }
        return sDBTools;
    }

    // 插入数据
    public <T>void insertDB(final T t){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.insert(t);
            }
        }).start();
    }

    public <T>void insertDB(final List<T> tList){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.insert(tList);
            }
        }).start();

    }

    // 查询数据
    public <T>void getQueryAll(final Class<T> clazz, final QueryListener<T> queryListener){
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                final ArrayList<T> beanArrayList = mLiteOrm.query(clazz);
                // handler 的post 方法 可以把一个runnable
                // 发送到主线程去执行
                mHandler.post(new HandlerRunnable<T>(beanArrayList, queryListener));
            }
        });
    }
    // 删除数据
    public <T> void deleteAll(final Class<T> tClass){
        if (tClass == null) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.delete(tClass);
            }
        }).start();
    }

    // handler 使用 runnable
    class HandlerRunnable<T> implements Runnable{

        private List<T> mTList;
        private QueryListener<T> mTQueryListener;

        public HandlerRunnable(List<T> mTList, QueryListener<T> mTQueryListener) {
            this.mTList = mTList;
            this.mTQueryListener = mTQueryListener;
        }

        @Override
        public void run() {
            mTQueryListener.onQuery(mTList);
        }
    }

    // 查询完成后, 回调接口
    public interface QueryListener<T>{
        // 当查询完成后了, 将查到的数据作为data 返回给Activity等
        void onQuery(List<T> beanArrayList);
    }
}
