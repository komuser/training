package com.example.training_hagita.fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.training_hagita.R;
import com.example.training_hagita.Util.KeyLock;
import com.example.training_hagita.asynctask.UploadCallback;
import com.example.training_hagita.asynctask.UploadRequester;
import com.example.training_hagita.activity.BaseActivity;
import com.example.training_hagita.db.PhotoDBHelper;

public class PhotoDetailFragment extends BaseFragment {

    private static final String UPLOAD_SERVER = "http://160.16.88.242/photo_server/api/api_upload.php";

    private String mId;
    private String mPath;
    private String mTitle;
    private String mDescription;
    private View mView;
    private PhotoDBHelper mDBHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        super.onCreateView(inflater, container, saveInstanceState);

        mView = inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mId = bundle.getString("ID", "");
            mPath = bundle.getString("PATH", "");
            mTitle = bundle.getString("FILE_NAME", "");
            TextView fileNameText = (TextView) mView.findViewById(R.id.file_name);
            fileNameText.setText(mTitle);
            ImageView imageView = (ImageView) mView.findViewById(R.id.detail);
            Bitmap bitmap = BitmapFactory.decodeFile(mPath);
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
                if (!KeyLock.isKeyHit()) {
                    showProgress();
                    UploadRequester uploadRequester = new UploadRequester(new UploadCallback() {
                        @Override
                        public void onPreExecute() {

                        }

                        @Override
                        public void onProgressUpdate() {

                        }

                        @Override
                        public void onPostExecute(String string) {
                            dismissProgress();
                        }
                    });
                    uploadRequester.addFile(mPath, mTitle);
                    uploadRequester.execute(UPLOAD_SERVER);

                    mDBHelper = new PhotoDBHelper(getActivity());
                    mDBHelper.insertValues(mPath, mTitle, mDescription);

//                Intent intent = new Intent();
//                finishFragment(intent);
                }
            }
        });

        Button deleteButton = view.findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!KeyLock.isKeyHit()) {
                    PhotoDBHelper photoDBHelper = new PhotoDBHelper(getActivity());
                    photoDBHelper.deleteValues(mId);
                    Toast.makeText(getActivity(), "レコードを削除しました", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected int getRequestCode() {
        return BaseActivity.Request.REQUEST_PHOTO_DETAIL.ordinal();
    }
}