package com.example.training_hagita.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.training_hagita.R;
import com.example.training_hagita.db.PhotoDBHelper;
import com.example.training_hagita.fragment.PhotoDetailFragment;
import com.example.training_hagita.fragment.PhotoListFragment;

public class MainActivity extends BaseActivity {

    public static final int REQUEST_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle saveInstanceStatus) {
        super.onCreate(saveInstanceStatus);
        setContentView(R.layout.activity_main);

        PhotoDBHelper photoDBHelper = new PhotoDBHelper(this);
        SQLiteDatabase sqLiteDatabase = photoDBHelper.getWritableDatabase();
        photoDBHelper.onCreate(sqLiteDatabase);

        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 権限許可
                PhotoListFragment fragment = new PhotoListFragment();
                startFragmentForResult(fragment);
            } else {
                // 権限拒否
                finish();
            }
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_PHOTO_LIST) {
            PhotoDetailFragment fragment = new PhotoDetailFragment();
            fragment.setArguments(intent.getExtras());
            startFragmentForResult(fragment);
        } else if (requestCode == REQUEST_PHOTO_DETAIL) {
            PhotoListFragment fragment = new PhotoListFragment();
            startFragmentForResult(fragment);
        }
    }
}
