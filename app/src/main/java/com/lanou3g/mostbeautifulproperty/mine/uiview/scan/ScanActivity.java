package com.lanou3g.mostbeautifulproperty.mine.uiview.scan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lanou3g.mostbeautifulproperty.R;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

/**
 * Created by dllo on 16/10/29.
 */

public class ScanActivity  extends AppCompatActivity implements QRCodeView.Delegate, View.OnClickListener {
    private static final String TAG = ScanActivity.class.getSimpleName();
    private QRCodeView mQRCodeView;
    private Button btnTwoQR;
    private Button btnItem;
    private Button btnOpen;
    private Button btnStop;
    private ImageView mReturnImg;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanactivity);
        initView();
        mQRCodeView = (ZBarView) findViewById(R.id.zbarview);
        mQRCodeView.setDelegate(this);
        mQRCodeView.startSpot();




    }

    private void initView() {
        mReturnImg = (ImageView) findViewById(R.id.app_bar_renturn_img);
        btnTwoQR = (Button) findViewById(R.id.scan_two);
        btnItem = (Button) findViewById(R.id.scan_item);
        btnOpen = (Button) findViewById(R.id.scan_open_lamp);
        btnStop = (Button) findViewById(R.id.scan_stop_lamp);
        btnTwoQR.setOnClickListener(this);
        btnItem.setOnClickListener(this);
        btnOpen.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        mReturnImg.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }
    private void vibrate(){
        // 控制手机震动
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }



    @Override
    public void onScanQRCodeSuccess(String result) {

        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        vibrate();
        mQRCodeView.startSpot();
        Intent intent = new Intent(this,ScanWebViewActivity.class);
        intent.putExtra("hh",result);
        startActivity(intent);

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.d(TAG, "打开相机出错");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scan_two:
               mQRCodeView.changeToScanQRCodeStyle();
                mQRCodeView.startSpot();
                break;
            case R.id.scan_item:
                mQRCodeView.changeToScanBarcodeStyle();
                mQRCodeView.startSpot();
                break;
            case R.id.scan_open_lamp:
                mQRCodeView.openFlashlight();
                break;
            case R.id.scan_stop_lamp:
                mQRCodeView.closeFlashlight();
                break;
            case R.id.app_bar_renturn_img:
                this.finish();
                break;
        }

    }
}
