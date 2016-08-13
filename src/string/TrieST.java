package string;

import fundamentals.Queue;

public class TrieST<Value> {

	private static int R = 256;
	private Node root;

	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
	}

	public TrieST() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public Value get(String key) {
        Node node = get(root,key,0);
        if(node == null) return null;
        return (Value) node.val;
	}

	private Node get(Node node, String key, int d) {
		if (node == null)
			return null;
		if (d == key.length())
			return node;
		return get(node.next[key.charAt(d)], key, d + 1);
	}
	
	public void put(String key, Value value) {
	    root = put(root,key,value,0);
	}
	
	private Node put(Node node, String key, Value value, int d) {
		if(node == null) node = new Node();
		if(d == key.length()) {
			node.val = value;
			return node;
		}
		node.next[key.charAt(d)] =  put(node.next[key.charAt(d)],key,value,d+1);
		return node;
	}
	
	public Iterable<String> keys() {
		return keysWithPrefix("");
	}
	
	public Iterable<String> keysWithPrefix(String pre) {
		Queue<String> q = new Queue<String>();
		collect(get(root,pre,0),pre,q);
		return q;
	}

	public void collect(Node node, String pre, Queue<String> q) {
		if(node == null) return;
		if(node.val != null) q.enqueue(pre);
		for(char c = 0; c < R; c++) {
			collect(node.next[c],pre + c,q);
		}
	}
	
	public Iterable<String> keysWithMatch(String pat) {
		Queue<String> q = new Queue<String>();
		collect(root,"",pat,q);
		return q;
	}
	
	public void collect(Node node, String pre, String pat, Queue<String> q) {
		if(node == null) return;
		if(pre.length() == pat.length() && node.val != null) q.enqueue(pre);
		if(pre.length() == pat.length()) return;
		char next = pat.charAt(pre.length());
		for(char c = 0; c < R; c++)
			if(next == '.' || next == c)
				collect(node.next[c],pre+c,pat,q);
	}
	
	public String longestPrefixOf(String s) {
		return s.substring(0, search(root,s,0,0));
	}
	
	public int search(Node node,String s,int d,int length) {
		if(node == null) return length;
		if(node.val != null) length = d;
		if(d == s.length()) return length;
		char c = s.charAt(d);
		return search(node.next[c],s,d+1,length);
	}
	
	public void delete(String key) {
		root = delete(root,key,0);
	}
	
	private Node delete(Node node,String key,int d) {
		if(node == null) return null;
		if(d == key.length())
			node.val = null;
		else {
			char c = key.charAt(d);
			node.next[c] = delete(node.next[c],key,d+1);
		}
		if(node.val != null) return node;
		for(char c = 0; c < R; c++)
			if(node.next[c] != null) return node;
		return null;
	}
	
}
