package com.aishang5wpj.juhenews.cache;

import android.text.TextUtils;

public class CacheManager implements IAction {

    private IAction mMemoryCache, mDiskCache;

    private CacheManager() {
        mMemoryCache = new LruCacheHelper();
        mDiskCache = new DiskCacheHelper();
    }

    public static CacheManager getManager() {
        return LazyHolder.INSTANCE;
    }

    private static final class LazyHolder {
        public static final CacheManager INSTANCE = new CacheManager();
    }

    @Override
    public String onReadFromCache(ICache iCache) {
        // 从内存读取
        String result = mMemoryCache.onReadFromCache(iCache);
        // 如果内存没有，则从磁盘读取，并将读取后的结果保存至内存
        if (TextUtils.isEmpty(result)) {
            // 从磁盘读取
            result = mDiskCache.onReadFromCache(iCache);
            // 缓存至内存
            mMemoryCache.onSaveToCache(iCache, result);
        }
        return result;
    }

    @Override
    public void onSaveToCache(ICache iCache, String content) {

        mMemoryCache.onSaveToCache(iCache, content);
        mDiskCache.onSaveToCache(iCache, content);
    }
}
