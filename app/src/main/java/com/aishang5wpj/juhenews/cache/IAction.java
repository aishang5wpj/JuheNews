package com.aishang5wpj.juhenews.cache;

public interface IAction {

    String onReadFromCache(ICache iCache);

    void onSaveToCache(ICache iCache, String content);
}
