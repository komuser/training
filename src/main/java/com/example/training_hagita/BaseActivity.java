package com.example.training_hagita;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public static final int REQUEST_NONE = 0;
    public static final int REQUEST_PHOTO_LIST = 1;
    public static final int REQUEST_PHOTO_DETAIL = 2;

    protected void startFragmentForResult(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    protected void checkPermission(String permission, int requestCode) {
        String[] list = new String[] {Manifest.permission.READ_EXTERNAL_STORAGE};
        int[] result = new int[] {PackageManager.PERMISSION_GRANTED};
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED){
                // パーミッションありの場合
                onRequestPermissionsResult(requestCode, list, result);
            } else {
                // パーミッションなしの場合
                if (shouldShowRequestPermissionRationale(permission)) {
                    // 以前にパーミッションを拒否している場合
                    requestPermissions(list, requestCode);
                } else {
                    // 最初のパーミッションリクエストの場合
                    requestPermissions(list, requestCode);
                }
            }
        } else {
            onRequestPermissionsResult(requestCode, list, result);
        }
    }

    protected void onFragmentResult(int requestCode, int resultCode, Intent intent) {

    }
}
