package com.aishang5wpj.juhenews;

import android.test.AndroidTestCase;

/**
 * Created by wpj on 16/5/26下午6:32.
 */
public class SimpleTest extends AndroidTestCase {

    @Override
    public void testAndroidTestCaseSetupProperly() {
        super.testAndroidTestCaseSetupProperly();

        boolean flag = false;
        flag |= false;
        assertEquals(false, flag);
    }
}
