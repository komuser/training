package com.example.training_hagita;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PhotoListFragment extends BaseFragment {

    private static final String TAG = PhotoListFragment.class.getSimpleName();

    private View mView;
    private RecyclerView mRecyclerView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        super.onCreateView(inflater, container, saveInstanceState);
        mView = inflater.inflate(R.layout.fragment_list, container, false);

        // ListViewにAdapterを設置
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.list_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);

        List<PhotoDao> photoDao = getList();

        // Adapterを準備
        PhotoAdapter photoAdapter = new PhotoAdapter(photoDao) {
            @Override
            protected void onRecyclerViewClicked(PhotoDao photoDao) {
                super.onRecyclerViewClicked(photoDao);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new PhotoDetailFragment());
                fragmentTransaction.commit();
            }
        };
        mRecyclerView.setAdapter(photoAdapter);
    }

    private List<PhotoDao> getList() {
        List<PhotoDao> list = new ArrayList<>();
        ContentResolver contentResolver = getActivity().getContentResolver();
        try {
            Cursor cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    PhotoDao dao = new PhotoDao();
                    dao.setId(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media._ID)));
                    dao.setTitle(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.TITLE)));
                    dao.setPath(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
                    long image = cursor.getLong(cursor.getColumnIndex("_id"));
                    Bitmap thumbnail = MediaStore.Images.Thumbnails.getThumbnail(contentResolver, image, MediaStore.Images.Thumbnails.MICRO_KIND, null);
                    dao.setImage(thumbnail);
                    list.add(dao);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "例外 = " + e);
        }
        return list;
    }
}
