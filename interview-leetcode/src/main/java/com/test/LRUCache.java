package com.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zkCheng
 * @date 2022/1/13 10:47
 */
public class LRUCache {

    Entry head;
    Entry tail;
    int capacity;
    int size;
    Map<Integer, Entry> cache;

    public LRUCache(int capacity){
        this.capacity = capacity;
        //初始化链表
        initLinkedList();
        size = 0;
        cache = new HashMap<>(capacity + 2);
    }

    /**
     * 插入数据
     */
    public void put(int key, int value){
        Entry entry = cache.get(key);
        if(entry != null){  // 如果插入的元素不为空，将其放在首条
            entry.value = value;
            moveToHead(entry);
            return;
        }
        // 不存在，则插入到首条
        if(capacity == size){ // 若缓存已满，则删掉尾部数据再插入
            Entry lastNode = tail.pre;
            deleteNode(lastNode);
            cache.remove(lastNode.key);
            size--;
        }

        // 加入到首节点
        Entry newNode = new Entry();
        newNode.key = key;
        newNode.value = value;
        addNode(newNode);
        cache.put(key,newNode);
        size++;
    }

    // 先删除，再增加
    private void moveToHead(Entry entry){
        deleteNode(entry);
        addNode(entry);
    }

    // 删除耨个节点
    private void deleteNode(Entry node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    // 获取某个节点
    public int get(int key){
        Entry node = cache.get(key);
        if(node == null){
            return -1;
        }

        moveToHead(node);
        return node.value;

    }

    // 将其填充到首节点
    private void addNode(Entry node){
        head.next.pre = node;
        node.next = head.next;

        node.pre = head;
        head.next = node;
    }

    // 定义一个双向链表的节点
    public static class Entry{
        public Entry pre;
        public Entry next;

        public int key;
        public int value;

        public Entry(int key, int value){
            this.key = key;
            this.value = value;
        }

        public Entry(){}
    }

    private void initLinkedList(){
        head = new Entry();
        tail = new Entry();

        head.next = tail;
        tail.pre = head;
    }


    public static void main(String[] args){
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        System.out.println(lruCache.get(1));
        lruCache.put(3,3);
        System.out.println(lruCache.get(2));
    }
}
