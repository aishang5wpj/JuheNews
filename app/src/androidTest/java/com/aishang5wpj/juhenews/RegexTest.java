package com.aishang5wpj.juhenews;

import android.test.AndroidTestCase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wpj on 16/5/25下午1:22.
 */
public class RegexTest extends AndroidTestCase {
    @Override
    public void testAndroidTestCaseSetupProperly() {
        super.testAndroidTestCaseSetupProperly();

        String url = "http://p0.meituan.net/w.h/movie/b721e73740601799c065affb619d837e151622.jpg";
        Pattern pattern = Pattern.compile("p0.meituan.net/(w|[0-9]+)[.](h|[0-9]+)/movie/");
        Matcher matcher = pattern.matcher(url);
        url = matcher.replaceAll("p0.meituan.net/720.1080/movie/");
        assertEquals("http://p0.meituan.net/720.1080/movie/b721e73740601799c065affb619d837e151622.jpg", url);
    }
}
