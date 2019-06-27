package com.example.training_hagita.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.training_hagita.R;
import com.example.training_hagita.activity.BaseActivity;

/**
 * ErrorDialogを表示するフラグメント
 *
 */
public class PhotoErrorDialogFragment extends DialogFragment {

    private static final String TAG = PhotoErrorDialogFragment.class.getName();

    private DialogInterface.OnClickListener mListener = null;

    /**
     * シングルトン
     * @return
     */
    public static PhotoErrorDialogFragment newInstance() {
        return new PhotoErrorDialogFragment();
    }

    /**
     * ダイアログ作成
     * @param savedInstanceState
     * @return ダイアログ
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.error_dialog_title);
        builder.setMessage(R.string.error);
        builder.setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        this.setCancelable(false);
        return builder.create();
    }

    /**
     * 表示。
     * @param activity アクティビティー。
     */
    public void show(BaseActivity activity) {
        Log.d(TAG, "show(" + activity + ")");
        super.show(activity.getSupportFragmentManager(), "progress");
    }
}
