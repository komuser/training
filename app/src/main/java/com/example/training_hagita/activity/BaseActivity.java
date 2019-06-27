package com.example.training_hagita.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.training_hagita.dialog.PhotoErrorDialogFragment;
import com.example.training_hagita.dialog.PhotoProgressDialogFragment;
import com.example.training_hagita.fragment.BaseFragment;
import com.example.training_hagita.R;

/**
 * Activityの基底クラス
 *
 * @author Hagita
 */
public class BaseActivity extends AppCompatActivity {

    private PhotoProgressDialogFragment mProgressDialog;
    private PhotoErrorDialogFragment mErrorDialog;

    public enum Request {
        // TODO:デフォルト値を入れたい
        REQUEST_NONE,
        REQUEST_PHOTO_LIST,
        REQUEST_PHOTO_DETAIL;
    }

    /**
     * Fragment起動処理
     * @param fragment
     */
    protected void startFragmentForResult(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    /**
     * パーミッションチェック
     * @param permission パーミッション
     * @param requestCode リクエストコード
     */
    protected void checkPermission(String permission, int requestCode) {
        String[] list = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
        int[] result = new int[]{PackageManager.PERMISSION_GRANTED};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // TODO:ここのチェックを減らしたい
            if (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
                // パーミッションありの場合
                onRequestPermissionsResult(requestCode, list, result);
            } else {
                // パーミッションなしの場合
                requestPermissions(list, requestCode);
            }
        } else {
            onRequestPermissionsResult(requestCode, list, result);
        }
    }

    public void onFragmentResult(int requestCode, int resultCode, Intent intent) {

    }

    /**
     * ProgressBarの表示
     */
    public void showProgress() {
        mProgressDialog = PhotoProgressDialogFragment.newInstance();
        mProgressDialog.show(this);
    }

    /**
     * ProgressBarを閉じる
     */
    public void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismissAllowingStateLoss();
            mProgressDialog = null;
        }
    }

    /**
     * ErrorDialogの表示
     */
    public void showError() {
        mErrorDialog = PhotoErrorDialogFragment.newInstance();
        mErrorDialog.show(this);
    }
}
