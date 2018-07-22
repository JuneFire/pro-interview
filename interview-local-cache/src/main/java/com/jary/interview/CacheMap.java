package com.jary.interview;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class CacheMap {

    private static final Long MIN_30 = 1000 * 60 * 30L;

    private static CacheMap cacheMap;

    private static final CacheMap getCacheMap(){
        if (cacheMap == null){
            cacheMap = new CacheMap();
        }
        return cacheMap;
    }

    private Map<String,UserInfo> userInfoMap;

    private Map<String,List<String>> dateMap;

    private Timer timer;

    private CacheMap(){

    }

    private void initCacheMap(){
        userInfoMap = new ConcurrentHashMap<>();
        dateMap = new ConcurrentHashMap<>();
        if (timer == null){
            timer = new Timer(false);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Map<String,List<String>> map;
                    synchronized (dateMap){
                        map = dateMap;
                    }
                    if (map == null || map.size() <= 0){
                        log.info("当前时间集合为null.");
                        return;
                    }
                    Date curDate = new Date();
                    Date keyTime;
                    for (Map.Entry<String,List<String>> entry : map.entrySet()){
                        keyTime = DateUtils.toDate(entry.getKey());
                        // 如果在半个小时之内,不需要更新缓存
                        if (Math.abs(keyTime.getTime() - curDate.getTime()) <= MIN_30){
                            log.info("key = {},curDate = {},相差不到半个小时,不需要更新缓存.",entry.getKey(),curDate);
                            continue;
                        }
                        // 如果在半小时后之后,更新缓存
                        updateDateMap(entry.getKey(),map);
                    }
                    // 讲map的值复制给dateMap
                    dateMap = map;

                }
            },1000,1000 * 60 * 10);
        }
    }

    public UserInfo get(String id){
        updateDateMap(id,null);
        return userInfoMap.get(id);
    }

    public void updateUserInfo(String id,UserInfo userInfo){
        boolean isNeedUpdate = false;
        Date curDate = new Date();
        Date keyTime;
        String oldKey = null;
        for (Map.Entry<String,List<String>> entry : dateMap.entrySet()){
            if (!entry.getValue().contains(id)){
                continue;
            }
            keyTime = DateUtils.toDate(entry.getKey());
            if (Math.abs(keyTime.getTime() - curDate.getTime()) > MIN_30){
                isNeedUpdate = true;
                oldKey = entry.getKey();
            }
        }
        if (isNeedUpdate && oldKey != null){
            List<String> list = dateMap.get(oldKey);
            if (list == null || list.size() == 0){
                log.error("数据返回异常.key = {}",oldKey);
                return;
            }
            list.remove(id);
            for (Map.Entry<String,List<String>> entry : dateMap.entrySet()){
                keyTime = DateUtils.toDate(entry.getKey());
                if (keyTime.getTime() - curDate.getTime() <= MIN_30){
                   List<String> newList = entry.getValue();
                   newList.add(id);
                   dateMap.put(entry.getKey(),newList);
                }
            }
        }
        if (userInfo == null){
            return;
        }
        Map<String,UserInfo> userMap;
        synchronized (userInfoMap){
            userMap = userInfoMap;
        }
        userMap.put(id,userInfo);
        userInfoMap = userMap;
    }

    public void updateDateMap(String oldKey,Map<String,List<String>> map){
        Date oldKeyTime = DateUtils.toDate(oldKey);
        Date newKeyTime = DateUtils.addDate(oldKeyTime,30);
        String newKey = DateUtils.toString(newKeyTime,DateUtils.DATE_FORM_FORMAT);
        List<String> list = map.get(oldKey);
        if (list == null || list.size() == 0){
            return;
        }
        List<String> newList = map.get(newKey);
        if (newList == null){
            newList = new ArrayList<>();
        }
        newList.addAll(list);
        map.put(newKey,newList);
        map.put(oldKey,null);
        map.remove(oldKey);
        dateMap = map;
    }

    public static void main(String[] args){
        CacheMap.getCacheMap().initCacheMap();

    }
}
