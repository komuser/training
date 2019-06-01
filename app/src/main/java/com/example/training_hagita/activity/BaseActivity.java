package com.example.training_hagita.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.training_hagita.fragment.BaseFragment;
import com.example.training_hagita.R;

public class BaseActivity extends AppCompatActivity {

    public enum Request {
        REQUEST_NONE,
        REQUEST_PHOTO_LIST,
        REQUEST_PHOTO_DETAIL;
    }

    protected void startFragmentForResult(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    protected void checkPermission(String permission, int requestCode) {
        String[] list = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
        int[] result = new int[]{PackageManager.PERMISSION_GRANTED};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
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

    public void onFragmentResult(int requestCode, int resultCode, Intent intent) {

    }
}
