//package com.example.training_hagita.dialog;
//
//import android.app.Dialog;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v4.app.DialogFragment;
//import android.util.Log;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//
//import com.example.training_hagita.R;
//import com.example.training_hagita.Util.KeyLock;
//import com.example.training_hagita.activity.BaseActivity;
//
//public class PhotoProgressDialogFragment extends DialogFragment {
//
//    private static final String TAG = PhotoProgressDialogFragment.class.getName();
//    /**
//     * 選択リスナー。
//     */
//    private View.OnClickListener mListener = null;
//
//    /**
//     * ダイアログ作成。
//     * @param savedInstanceState 保存情報。
//     * @return ダイアログ。
//     */
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Log.d(TAG, "onCreateDialog(" + savedInstanceState + ")");
//        Dialog dialog = super.onCreateDialog(savedInstanceState);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCanceledOnTouchOutside(true);
//        setDialog(dialog);
//        Window window = dialog.getWindow();
//        if (window != null) {
//            setWindow(window);
//        }
//        return dialog;
//    }
//
//    /**
//     * ダイアログ設定。
//     * @param dialog ダイアログ。
//     */
//    protected void setDialog(Dialog dialog) {
//        dialog.setContentView(R.layout.dialog_progress);
//        View button = dialog.findViewById(R.id.dialog_progress_cancel);
//        button.setOnClickListener(new View.OnClickListener() {
//            /**
//             * 選択通知。
//             * @param view ビュー。
//             */
//            @Override
//            public void onClick(View view) {
//                Log.e(TAG, "onClick(" + view + ")");
//                if (!KeyLock.isKeyHit()) {
//                    if (mListener != null) {
//                        mListener.onClick(view);
//                    }
//                    dismiss();
//                }
//            }
//        });
//    }
//
//    /**
//     * ウィンドウ設定。
//     * @param window ウィンドウ。
//     */
//    protected void setWindow(Window window) {
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.dimAmount = 0.9f;
//        window.setAttributes(lp);
//    }
//
//    /**
//     * 表示。
//     * @param activity アクティビティー。
//     */
//    public void show(BaseActivity activity) {
//        Log.d(TAG, "show(" + activity + ")");
//        super.show(activity.getSupportFragmentManager(), "progress");
//    }
//
//    /**
//     * 選択リスナー設定。
//     * @param listener 選択リスナー。
//     */
//    public void setOnCancelListener(View.OnClickListener listener) {
//        this.mListener = listener;
//    }
//}
