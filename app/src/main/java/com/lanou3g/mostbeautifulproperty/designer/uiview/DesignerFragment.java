package com.lanou3g.mostbeautifulproperty.designer.uiview;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.bean.DesignerBean;
import com.lanou3g.mostbeautifulproperty.designer.presenter.DesignerPresenter;
import com.lanou3g.mostbeautifulproperty.designer.uiview.designeradapter.DesignAdapter;
import com.lanou3g.mostbeautifulproperty.okhttp.URLValues;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

import static android.content.Context.SENSOR_SERVICE;


/**
 *
 */

public class DesignerFragment extends BaseFragment implements IDesignerView<DesignerBean>{
    private ListView mListView;
    private DesignerPresenter<DesignerBean> mDesignerPresenter;
    private SensorManager mSensorManager;
    private JCVideoPlayer.JCAutoFullscreenListener sensorEventListner;
    private XRefreshView mRefreshView;
    int Page = 0;
    private DesignAdapter mDesignAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_designer;
    }

    @Override
    protected void initView() {
        mListView = bindView(R.id.design_fragemrnt_listview);
        mSensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        sensorEventListner = new JCVideoPlayer.JCAutoFullscreenListener();
        mRefreshView = bindView(R.id.design_fragment_xefreshview);
        mRefreshView.setPullRefreshEnable(true);
        mRefreshView.setPullLoadEnable(true);


    }
    @Override
    protected void initData() {
        mDesignerPresenter = new DesignerPresenter<>(this);

        mDesignerPresenter.startRequest(URLValues.getVIDEO_URL(Page),DesignerBean.class);

        mDesignAdapter = new DesignAdapter(context);
        mListView.setAdapter(mDesignAdapter);
        onRefresh();


    }

    private void onRefresh() {
        mRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
                                                 @Override
                                                 public void onRefresh() {
                                                     super.onRefresh();
                                                     Page = 0;
                                                     mDesignerPresenter.startRequest(URLValues.getVIDEO_URL(Page),DesignerBean.class);




                                                 }

                                                 @Override
                                                 public void onLoadMore(boolean isSilence) {
                                                     super.onLoadMore(isSilence);
                                                     mDesignerPresenter.startRequest(URLValues.getVIDEO_URL(Page),DesignerBean.class);


                                                 }
                                             }
        );

    }

    @Override
    public void onResume() {
        super.onResume();
        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(sensorEventListner, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (JCVideoPlayer.backPress()) {
                        return true;
                    }


                }
                return false;
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(sensorEventListner);
        JCVideoPlayer.releaseAllVideos();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void onResponse(DesignerBean designerBeen) {
      if (Page == 0){
          mDesignAdapter.setDesignerBean(designerBeen);
          mRefreshView.stopRefresh();
          Page = (int) designerBeen.getInfo().getNp();
      } else {
          mDesignAdapter.setMoreDesignerBean(designerBeen);
          mRefreshView.stopLoadMore();
          Page = (int) designerBeen.getInfo().getNp();
      }






    }

    @Override
    public void onError() {

    }
}
