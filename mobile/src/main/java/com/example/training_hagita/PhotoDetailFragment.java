package com.example.training_hagita;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PhotoDetailFragment extends BaseFragment {

    private static final String UPLOAD_SERVER = "http://160.16.88.242/photo_server/admin/admin_login.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        super.onCreateView(inflater, container, saveInstanceState);
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);

        Button button = view.findViewById(R.id.upload);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadRequester uploadRequester = new UploadRequester(new UploadRequester.UploadCallback() {
                    @Override
                    public void onPreExecute() {

                    }

                    @Override
                    public void onProgressUpdate() {

                    }

                    @Override
                    public void onPostExecute(String string) {

                    }
                });
                uploadRequester.execute(UPLOAD_SERVER);

//                PhotoDetailFragment photoDetailFragment = new PhotoDetailFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.replace(R.id.container, photoDetailFragment);
//                fragmentTransaction.commit();
            }
        });
    }
}