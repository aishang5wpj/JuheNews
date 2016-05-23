package com.aishang5wpj.juhenews;

import android.test.AndroidTestCase;

import com.aishang5wpj.juhenews.utils.FileUtils;

/**
 * Created by wpj on 16/5/17上午11:42.
 */
public class FileUtilsTest extends AndroidTestCase {

    @Override
    public void testAndroidTestCaseSetupProperly() {
        super.testAndroidTestCaseSetupProperly();

        String result = FileUtils.readAssertsFile(mContext, "newsChannel.json");
        assertNotNull(result);
        result = FileUtils.readAssertsFile(mContext, "test/test.json");
        assertNotNull(result);
    }
}
