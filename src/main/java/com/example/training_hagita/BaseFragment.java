package com.example.training_hagita;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    private int mResult = Activity.RESULT_OK;

    protected void finishFragment(Intent intent) {
        Activity base = getActivity();
        if (base instanceof BaseActivity) {
            ((BaseActivity)base).onFragmentResult(getRequestCode(), getResult(), intent);
        }
    }
    protected int getRequestCode() {
        return BaseActivity.REQUEST_NONE;
    }

    protected void setResult(int result) {
        mResult = result;
    }

    protected int getResult() {
        return mResult;
    }
}
