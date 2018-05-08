package com.practice.ds.tree;

import java.util.HashMap;
import java.util.Map;

/*
 * 1) Seperate TrieNode class
 * a) Two constructor one with and another (root) without argument
 * b) Should have children map of char vs node mapping  
 * c) bool isLeaf
 * d) char ch
 * 
 * 2) Trie class with 
 * a) Have root TrieNode : TrieNodee root = new TrieNodee();
 * b) insert function (word)
 * 		Start from root and look for its childrens for characters of word
 * 		Go down and at last mark node as leaf.
 * c) search function
 */

class TrieNodee{
	Map<Character, TrieNodee> children = new HashMap<>();
	char ch;
	boolean isLeaf = false;
	TrieNodee(char ch){
		this.ch = ch;
	}
	public TrieNodee() {
	}
}

public class Trie2 {
	
	TrieNodee root = new TrieNodee();

	public void insert(String str){
		Map<Character, TrieNodee> children = root.children;
		TrieNodee temp ;
		for(int i = 0 ; i < str.length(); i++){
			char ch = str.charAt(i);
			if(children.containsKey(ch))temp = children.get(ch);
			else{
				temp = new TrieNodee(ch);
				children.put(ch, temp);			
			}	
			children = temp.children;
			if(i == str.length()-1) temp.isLeaf = true;
		}

	}
	public boolean search(String str){
		Map<Character, TrieNodee> children = root.children;
		TrieNodee ans= null;
		for(int i = 0 ; i < str.length(); i++){
			char ch = str.charAt(i);
			if(children.containsKey(ch)){
				ans = children.get(ch);
				children = ans.children;
			}
			else{
				return false;
			}	
		}
		if(ans.isLeaf) return true;
		else return false;
	}
	public static void main(String[] args) {
		Trie2 tr2 = new Trie2();
		tr2.insert("amita");
		tr2.insert("raja");
		tr2.insert("baja");
		System.out.println(tr2.search("raj"));
		System.out.println(tr2.search("raja"));
		System.out.println(tr2.search("baj"));
		System.out.println(tr2.search("baja"));
	}
}
