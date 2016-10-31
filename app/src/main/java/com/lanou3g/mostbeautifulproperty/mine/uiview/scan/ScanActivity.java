package com.lanou3g.mostbeautifulproperty.mine.uiview.scan;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.lanou3g.mostbeautifulproperty.R;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

/**
 * Created by dllo on 16/10/29.
 */

public class ScanActivity  extends AppCompatActivity implements QRCodeView.Delegate{
    private static final String TAG = ScanActivity.class.getSimpleName();
    private QRCodeView mQRCodeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanactivity);
        mQRCodeView = (ZBarView) findViewById(R.id.zbarview);
        mQRCodeView.setDelegate(this);
        mQRCodeView.startSpot();

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
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }



    @Override
    public void onScanQRCodeSuccess(String result) {

        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        vibrate();
        mQRCodeView.startSpot();

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.d(TAG, "打开相机出错");

    }
}
