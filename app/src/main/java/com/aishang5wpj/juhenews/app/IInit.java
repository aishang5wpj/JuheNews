package com.aishang5wpj.juhenews.app;

import android.view.View.OnClickListener;

public interface IInit extends OnClickListener {

    void onInitViews();

    void onInitListeners();

    void onInitData();
}
