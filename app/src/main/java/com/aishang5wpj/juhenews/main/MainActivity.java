package com.aishang5wpj.juhenews.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseActivity;

public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onInitViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mNavigationView = (NavigationView) findViewById(R.id.main_menu_navigation);
    }

    @Override
    public void onInitListeners() {

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Snackbar.make(mNavigationView, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void onInitData() {
        disableSwipeback();
        setSupportActionBar(mToolbar);
    }

    @Override
    public void onClick(View v) {

    }
}
