package com.lanou3g.mostbeautifulproperty.mine.uiview.scan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.bingoogolapple.qrcode.core.QRCodeView;

/**
 * Created by dllo on 16/10/29.
 */

public class ScanActivity  extends AppCompatActivity implements QRCodeView.Delegate{
    private static final String TAG = ScanActivity.class.getSimpleName();
    private QRCodeView mQRCodeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
