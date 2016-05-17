package com.aishang5wpj.juhenews.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by wpj on 16/5/17下午2:38.
 */
public class Utils {

    public static String urlEncoderStr(String string) {
        try {
            return URLEncoder.encode(string, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string;
    }
}
