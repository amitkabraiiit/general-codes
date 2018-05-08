package com.practice.design.cache;

import java.util.HashMap;

class DoubleLinkedListNode {
	public int val;
	public int key;
	public DoubleLinkedListNode pre;
	public DoubleLinkedListNode next;

	public DoubleLinkedListNode(int key, int value) {
		val = value;
		this.key = key;
	}
}

public class LRUCache {
	private HashMap<Integer, DoubleLinkedListNode> map 
	= new HashMap<Integer, DoubleLinkedListNode>();
	private DoubleLinkedListNode head;
	private DoubleLinkedListNode end;
	private int capacity;
	private int len;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		len = 0;
	}

	public int get(int key) {
		if (map.containsKey(key)) {
			DoubleLinkedListNode latest = map.get(key);
			removeNode(latest);
			setHead(latest);
			return latest.val;
		} else {
			return -1;
		}
	}

	public void removeNode(DoubleLinkedListNode node) {
		DoubleLinkedListNode cur = node;
		DoubleLinkedListNode pre = cur.pre;
		DoubleLinkedListNode post = cur.next;

		if (pre != null) {
			pre.next = post;
		} else {
			head = post;
		}

		if (post != null) {
			post.pre = pre;
		} else {
			end = pre;
		}
	}

	public void setHead(DoubleLinkedListNode node) {
		node.next = head;
		node.pre = null;
		if (head != null) {
			head.pre = node;
		}

		head = node;
		if (end == null) {
			end = node;
		}
	}

	public void set(int key, int value) {
		if (map.containsKey(key)) {
			DoubleLinkedListNode oldNode = map.get(key);
			oldNode.val = value;
			removeNode(oldNode);
			setHead(oldNode);
		} else {
			DoubleLinkedListNode newNode = new DoubleLinkedListNode(key, value);
			if (len < capacity) {
				setHead(newNode);
				map.put(key, newNode);
				len++;
			} else {
				map.remove(end.key);
				end = end.pre;
				if (end != null) {
					end.next = null;
				}

				setHead(newNode);
				map.put(key, newNode);
			}
		}
		
	}
	@Override
	public String toString(){
		DoubleLinkedListNode tmp = head;
		while(tmp != null){
			System.out.println(tmp.key+":"+tmp.val);
			tmp = tmp.next;
		}
		System.out.println();
		return null;
		
	}
	
	public static void main(String[] args) {
		LRUCache cache = new LRUCache(3);
		cache.set(0, 0);
		cache.set(1, 1);
		cache.set(2, 2);
		System.out.println(cache.toString());
		cache.set(3, 3);
		System.out.println(cache.toString());
		cache.set(2, 2);
		System.out.println(cache.toString());
		cache.set(4, 4);
		System.out.println(cache.toString());
	}
}

