package com.aishang5wpj.juhenews;

import android.test.AndroidTestCase;

import com.aishang5wpj.juhenews.bean.DoubanMovieBean;
import com.aishang5wpj.juhenews.utils.FileUtils;
import com.google.gson.Gson;

/**
 * Created by wpj on 16/5/25上午10:24.
 */
public class MovieBeanTest extends AndroidTestCase {

    private Gson mGson;

    public MovieBeanTest() {
        mGson = new Gson();
    }

    @Override
    public void testAndroidTestCaseSetupProperly() {
        super.testAndroidTestCaseSetupProperly();

        String result = FileUtils.readAssertsFile(mContext, "test/movie.json");
        DoubanMovieBean movieBean = mGson.fromJson(result, DoubanMovieBean.class);
        assertNotNull(movieBean);
    }
}
