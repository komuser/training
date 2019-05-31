package com.example.training_hagita.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.training_hagita.R;
import com.example.training_hagita.asynctask.UploadRequester;
import com.example.training_hagita.activity.BaseActivity;

public class PhotoDetailFragment extends BaseFragment {

    private static final String UPLOAD_SERVER = "http://160.16.88.242/photo_server/api/api_upload.php";

    private String mPath;
    private String mTitle;
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        super.onCreateView(inflater, container, saveInstanceState);

        mView = inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTitle = bundle.getString("PATH", "");
            ImageView imageView = (ImageView) mView.findViewById(R.id.detail);
            Bitmap bitmap = BitmapFactory.decodeFile(mTitle);
            imageView.setImageBitmap(bitmap);
        }
        return mView;
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
                Bundle bundle = getArguments();
                if (bundle != null) {
                    mPath = bundle.getString("PATH","");
                    mTitle = bundle.getString("FILE_NAME", "");
                }
                uploadRequester.addFile(mPath, mTitle);
                uploadRequester.execute(UPLOAD_SERVER);
                Intent intent = new Intent();
                finishFragment(intent);
            }
        });
    }

    protected int getRequestCode() {
        return BaseActivity.REQUEST_PHOTO_DETAIL;
    }
}