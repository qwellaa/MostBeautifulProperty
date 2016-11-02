package com.lanou3g.mostbeautifulproperty.designer.uiview;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.MenuItem;
import android.widget.ListView;

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


    @Override
    protected int setLayout() {
        return R.layout.fragment_designer;
    }

    @Override
    protected void initView() {
        mListView = bindView(R.id.design_fragemrnt_listview);
        mSensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        sensorEventListner = new JCVideoPlayer.JCAutoFullscreenListener();


    }
    @Override
    protected void initData() {
        mDesignerPresenter = new DesignerPresenter<>(this);
        mDesignerPresenter.startRequest(URLValues.VIDEO_URL,DesignerBean.class);


    }

    @Override
    public void onResume() {
        super.onResume();
        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(sensorEventListner, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
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
        DesignAdapter designAdapter = new DesignAdapter(context);
        designAdapter.setDesignerBean(designerBeen);
        mListView.setAdapter(designAdapter);




    }

    @Override
    public void onError() {

    }
}
