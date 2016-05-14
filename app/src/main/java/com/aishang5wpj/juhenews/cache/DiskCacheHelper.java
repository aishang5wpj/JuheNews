package com.aishang5wpj.juhenews.cache;


import com.aishang5wpj.juhenews.utils.FileUtils;

public class DiskCacheHelper implements IAction {

    private static final String DICOTRY = "cache/";

    @Override
    public String onReadFromCache(ICache iCache) {
        return FileUtils.readTextFromDisk(DICOTRY + iCache.getType());
    }

    @Override
    public void onSaveToCache(ICache iCache, String content) {

        FileUtils.saveText2Disk(DICOTRY + iCache.getType(), content);
    }
}
