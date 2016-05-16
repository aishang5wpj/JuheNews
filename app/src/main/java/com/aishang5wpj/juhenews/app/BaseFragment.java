package com.aishang5wpj.juhenews.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wpj on 16/5/16上午10:42.
 */
public abstract class BaseFragment extends Fragment implements IInit {
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        int id = getLayoutId();
        mView = inflater.inflate(id, null);
        return mView;
    }

    protected abstract int getLayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this instanceof IInit) {
            IInit iInit = this;
            iInit.onInitViews();
            iInit.onInitListeners();
            iInit.onInitData();
        }
    }

    protected View findViewById(int id) {
        return null == mView ? null : mView.findViewById(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
        //包含小数点的名字作为key似乎有某种问题？
//        return getClass().getCanonicalName();
    }
}
