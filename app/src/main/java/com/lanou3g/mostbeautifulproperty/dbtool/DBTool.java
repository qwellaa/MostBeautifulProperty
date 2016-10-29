package com.lanou3g.mostbeautifulproperty.dbtool;

;

/**
 * Created by dllo on 16/10/27.
 */

public class DBTool<T> {
    // 需要进一步完善.......


//    private static DBTool sDBTool;
//    private LiteOrm mLiteOrm;
//    private ExecutorService threedPool;
//    private Handler mHandler;
//
//    private DBTool() {
//        mLiteOrm = LiteOrm.newSingleInstance(MostBeautifulApp.getContext(),"MostBeautiful.db");
//        mHandler = new Handler(Looper.getMainLooper());
//
//    }
//    public static DBTool getIntance(){
//        if (sDBTool == null){
//            synchronized (DBTool.class){
//                if (sDBTool == null){
//                    sDBTool = new DBTool();
//                }
//            }
//        }
//        return sDBTool;
//    }
//
//    public void insertData(List<T> date){
//        mLiteOrm.insert(date);
//
//    }
//    public void getAllDate(QueryListener<DailyBean> queryListener ){
//
//        threedPool.execute(new Runnable() {
//            @Override
//            public void run() {
//
//                ArrayList<DailyBean> datas = mLiteOrm.query(DailyBean.class);
//            }
//        });
//
//    }
//    class HandlerRunnable<T> implements  Runnable{
//        private List<T>mTList;
//        private QueryListener<T>mTQueryListener;
//        public HandlerRunnable(QueryListener<T> mTQueryListener, List<T> mTList){
//            this.mTQueryListener = mTQueryListener;
//            this.mTList = mTList;
//
//
//        }
//
//        @Override
//        public void run() {
//            mTQueryListener.onQuery(mTList);
//
//        }
//    }
//
//
//    public  interface QueryListener<T>{
//        // 当查询完成后 将查询点我数据作为tata, 返回给activity
//        void onQuery(List<T> dates);
//
//    }

}
