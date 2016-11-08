package com.lanou3g.mostbeautifulproperty.discover.uiview;

import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.listener.OnBottomLoadMoreTime;
import com.andview.refreshview.listener.OnTopRefreshTime;
import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.bean.DailyBean;
import com.lanou3g.mostbeautifulproperty.discover.presenter.DiscoverPresenter;
import com.lanou3g.mostbeautifulproperty.discover.uiview.adapter.DailyAdapter;
import com.lanou3g.mostbeautifulproperty.okhttp.URLValues;
import com.lanou3g.mostbeautifulproperty.view.LVGhost;
import com.lanou3g.mostbeautifulproperty.view.stickyListHeaders.StickyListHeadersListView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


/**
 *
 */

public class DiscoverDailyFragment extends BaseFragment implements IDiscoverView{

    private AlertDialog mDialog;
    private StickyListHeadersListView mStickyLv;
    private XRefreshView mRefreshView;
    private int mTotalItemCount;
    private long mLd;
    private DailyAdapter mAdapter;
    private long mFirstTime;

    @Override
    protected int setLayout() {
        return R.layout.fragment_discover_daily;
    }

    @Override
    protected void initView() {

        mDialog = createDialog();

        mStickyLv = bindView(R.id.discover_daily_sticky_view);
        mRefreshView = bindView(R.id.discover_daily_xresh_view);

    }

    private AlertDialog createDialog() {
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(true);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loading, null);
        LVGhost mLvGhost = (LVGhost) view.findViewById(R.id.dialog_lvghost);
        mLvGhost.startAnim();
        dialog.setView(view);
        return dialog;

    }

    @Override
    protected void initData() {

        initRefreshViewScroll();
        initInternetDate();

        mAdapter = new DailyAdapter(context);
        mStickyLv.setAdapter(mAdapter);

        final DiscoverPresenter presenter = new DiscoverPresenter(this);
        presenter.startRequest(URLValues.getDaily_HEAD_URL(mLd), DailyBean.class);

        mRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh() {
                mLd = mFirstTime;
                presenter.startRequest(URLValues.getDaily_HEAD_URL(mLd), DailyBean.class);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                mLd = mLd - 86400000;
                presenter.startRequest(URLValues.getDaily_HEAD_URL(mLd), DailyBean.class);
            }
        });
    }

    private void initInternetDate() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url= null;//取得资源对象
                try {
                    url = new URL("http://time.tianqi.com/");
                    URLConnection uc= null;//生成连接对象
                    uc = url.openConnection();
                    uc.connect(); //发出连接
                    //取得时间
                    mLd = uc.getDate();
                    mFirstTime = mLd;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


    @Override
    public void showDialog() {
        mDialog.show();
    }

    @Override
    public void dismissDialog() {
        mDialog.dismiss();
    }

    @Override
    public void onResponse(Object result) {
        if (result instanceof DailyBean) {
            DailyBean dailyBean = (DailyBean) result;
            List<DailyBean.DataBean.ProductsBean> list = dailyBean.getData().getProducts();
            if (mFirstTime == mLd) {
                mAdapter.setDataListRefresh(list);
                mRefreshView.stopRefresh();
            } else {
                mAdapter.setDataListLoad(list);
                mRefreshView.stopLoadMore();
            }

        }
    }

    @Override
    public void onError() {
        Log.d("DiscoverDailyFragment", "请求失败");
    }

    private void initRefreshViewScroll() {
        mRefreshView.setPullLoadEnable(true);
        mRefreshView.setOnTopRefreshTime(new OnTopRefreshTime() {
            @Override
            public boolean isTop() {
                if (mStickyLv.getFirstVisiblePosition() == 0) {
                    View firstVisibleChild = mStickyLv.getListChildAt(0);
                    return firstVisibleChild.getTop() >= 0;
                }
                return false;
            }
        });
        mRefreshView.setOnBottomLoadMoreTime(new OnBottomLoadMoreTime() {
            @Override
            public boolean isBottom() {
                if (mStickyLv.getLastVisiblePosition() == mTotalItemCount - 1) {
                    View lastChild = mStickyLv.getListChildAt(mStickyLv.getListChildCount() - 1);
                    return (lastChild.getBottom() + mStickyLv.getPaddingBottom()) <= mStickyLv.getMeasuredHeight();
                }
                return false;
            }
        });

        mStickyLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                mTotalItemCount = totalItemCount;
            }
        });

    }
}
