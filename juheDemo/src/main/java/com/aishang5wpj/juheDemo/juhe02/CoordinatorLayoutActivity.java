package com.aishang5wpj.juheDemo.juhe02;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aishang5wpj.juheDemo.R;

/**
 * Created by wpj on 16/5/27下午5:31.
 */
public class CoordinatorLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton mActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mActionButton = (FloatingActionButton) findViewById(R.id.floating_action_btn);
        mActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_btn:

                Snackbar.make(mActionButton, "嵌套在CoordinatorLayout后的显示", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
