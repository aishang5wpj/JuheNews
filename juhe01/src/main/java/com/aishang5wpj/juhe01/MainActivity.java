package com.aishang5wpj.juhe01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by wpj on 16/5/27下午3:09.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClick(View view) {

        Class<? extends AppCompatActivity> cls = null;
        switch (view.getId()) {
            case R.id.toolbarBtn:
                cls = ToolbarActivity.class;
                break;
            case R.id.appBarLayoutBtn:
                cls = AppBarLayoutActivity.class;
                break;
            case R.id.coordinatorLayoutBtn:
                cls = CoordinatorLayoutActivity.class;
                break;
            case R.id.collapsingToolBarLayoutBtn:
                cls = CollapsingToolbarLayoutActivity.class;
                break;
        }
        if (null != cls) {
            Intent intent = new Intent(this, cls);
            startActivity(intent);
        }
    }
}
