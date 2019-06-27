package com.example.training_hagita.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.training_hagita.activity.BaseActivity;

/**
 * Fragmentの基底クラス
 *
 * @author Hagita
 */
public class BaseFragment extends Fragment {

    private int mResult = Activity.RESULT_OK;

    /**
     * Fragmentの処理結果をActivityに渡す
     * @param intent
     */
    protected void finishFragment(Intent intent) {
        Activity base = getActivity();
        if (base instanceof BaseActivity) {
            ((BaseActivity)base).onFragmentResult(getRequestCode(), getResult(), intent);
        }
    }

    /**
     * Dialogの表示をActivityに渡す
     */
    protected void showProgress() {
        Activity base = getActivity();
        if (base instanceof BaseActivity) {
            ((BaseActivity)base).showProgress();
        }
    }

    /**
     * Dialogを閉じる
     */
    protected void dismissProgress() {
        Activity base = getActivity();
        if (base instanceof BaseActivity) {
            ((BaseActivity)base).dismissProgress();
        }
    }

    /**
     * ErrorDialogの表示をActivityに渡す
     */
    protected void showError() {
        Activity base = getActivity();
        if (base instanceof BaseActivity) {
            ((BaseActivity)base).showError();
        }
    }
    protected int getRequestCode() {
        return BaseActivity.Request.REQUEST_NONE.ordinal();
    }

    protected void setResult(int result) {
        mResult = result;
    }

    protected int getResult() {
        return mResult;
    }
}
