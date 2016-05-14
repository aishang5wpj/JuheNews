package com.aishang5wpj.juhenews.cache;

import android.support.v4.util.LruCache;

import java.io.UnsupportedEncodingException;

public class LruCacheHelper implements IAction {

    private LruCache<String, String> mLruCache;

    public LruCacheHelper() {
        // 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常。
        // LruCache通过构造函数传入缓存值，以KB为单位。
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // 使用最大可用内存值的1/8作为缓存的大小。
        int cacheSize = maxMemory / 8;
        mLruCache = new LruCache<String, String>(cacheSize) {
            @Override
            protected int sizeOf(String key, String string) {
                // 重写此方法来衡量每张图片的大小，默认返回图片数量。
                try {
                    return string.getBytes("utf-8").length / 1024;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return string.getBytes().length / 1024;
            }
        };
    }

    @Override
    public String onReadFromCache(ICache iCache) {

        return mLruCache.get(iCache.getType());
    }

    @Override
    public void onSaveToCache(ICache iCache, String content) {

        mLruCache.put(iCache.getType(), content);
    }
}
