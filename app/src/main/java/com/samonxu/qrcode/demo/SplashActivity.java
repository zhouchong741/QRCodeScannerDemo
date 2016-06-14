package com.samonxu.qrcode.demo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {
    private PermissionChecker mPermissionChecker;
    private static final int REQUEST_CODE = 0;
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //权限获取
        mPermissionChecker = new PermissionChecker(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPermissionChecker.lackPermission(PERMISSIONS)) {
            startPermissionActivity();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void startPermissionActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && requestCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
