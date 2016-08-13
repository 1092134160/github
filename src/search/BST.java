package search;

import fundamentals.Queue;

public class BST<Key extends Comparable<Key>,Value> {
	
	private Node root;
	
	private class Node {
		
		private Key key;
		private Value value;
		private Node left,right;
		private int N;
		
		public Node(Key key,Value value,int N) {
			this.key = key;
			this.value = value;
			this.N = N;
		}
		
	}
	
	public int size() {
		return size(root);
	}

	private int size(Node node) {
		if(node == null) return 0;
		return node.N;
	}
	
	public Value get(Key key) {
		return get(root,key);
	}
	
	private Value get(Node node,Key key) {
		if(node == null) return null;
		int cmp = key.compareTo(node.key);
		if(cmp < 0) return get(node.left,key);
		if(cmp > 0) return get(node.right,key);
		return node.value;
	}
	
	public void put(Key key,Value value) {
		root = put(root,key,value);
	}
	
    public Node put(Node node,Key key,Value value) {
		if(node == null) return new Node(key,value,1);
		int cmp = key.compareTo(node.key);
		if(cmp < 0) node.left = put(node.left,key,value);
		else if(cmp > 0) node.right = put(node.right,key,value);
		else node.value = value;
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	public Key min() {
		return min(root).key;
	}
	
	private Node min(Node node) {
		if(node.left == null) return node;
		return min(node.left);
	}
    
	public Key max() {
		return max(root).key;
	}
	
	private Node max(Node node) {
		if(node.right == null) return node;
		return max(node.right);
	}
	
	public Key floor(Key key) {
		Node node = floor(root,key);
		if(node == null) return null;
		return node.key;
	}
	
	private Node floor(Node node,Key key) {
		if(node == null) return null;
		int cmp = key.compareTo(node.key);
		if(cmp == 0) return node;
		if(cmp < 0) return floor(node.left,key);
		Node t = floor(node.right,key);
		if(t == null) return node;
		return t;
	}
	
	public Key ceilng(Key key) {
		Node node = ceilng(root,key);
		if(node == null) return null;
		return node.key;
	}
	
	private Node ceilng(Node node,Key key) {
		if(node == null) return null;
		int cmp = key.compareTo(node.key);
		if(cmp == 0) return node;
		else if(cmp > 0) return floor(node.right,key);
		Node t = floor(node.left,key);
		if(t == null) return node;
		return t;
	}
	
	public Key select(int k) {
		return select(root,k).key;
	}
	
	private Node select(Node node,int k) {
		if(node == null) return null;
		int t = size(node.left);
		if(t == k) return node;
		else if(t > k) return select(node.left,k);
		else return select(node.right,k-t-1); 
	}
	
	public int rank(Key key) {
		return rank(key,root);
	}
	
	private int rank(Key key,Node node) {
		if(node == null) return -1;
		int cmp = key.compareTo(node.key);
		if(cmp == 0) return size(node.left);
		else if(cmp < 0) return rank(key,node.left);
		else return size(node.left) + rank(key,node.right);
	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node node) {
		if(node.left == null) return node.right;
		node.left = deleteMin(node);
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	public Node deleteMax() {
		return deleteMax(root);
	}
	
    private Node deleteMax(Node node) {
		if(node.right == null) return node.left;
		node.right = deleteMax(node.right);
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
    
    public void delete(Key key) {
    	root = delete(root,key);
    }
	
    private Node delete(Node node,Key key) {
    	if(node == null) return null;
    	int cmp = node.key.compareTo(key);
    	if(cmp > 0) node.left = delete(node.left,key);
    	else if(cmp < 0) node.right = delete(node.right,key);
    	else {
    		if(node.left == null) return node.right;
    		if(node.right == null) return node.left;
    		Node t = node;
    		node = min(t.right);
    		node.right = deleteMin(t.right);
    		node.left = t.left;
    	}
    	node.N = size(node.left) + size(node.right) + 1;
    	return node;
    }
    
    public Iterable<Key> keys() {
    	return keys(min(),max());
    }
    
    public Iterable<Key> keys(Key low,Key high) {
    	Queue<Key> queue = new Queue<Key>();
    	keys(root,queue,low,high);
    	return queue;
    }
    
    private void keys(Node node,Queue<Key> queue,Key low,Key high) {
    	if(node == null) return;
    	int cmpLow = low.compareTo(node.key);
    	int cmpHigh = high.compareTo(node.key);
    	if(cmpLow < 0) keys(node.left,queue,low,node.key);
    	else if(cmpLow >= 0 && cmpHigh <= 0) queue.enqueue(node.key);
    	else if(cmpHigh > 0) keys(node.right,queue,node.key,high);
    }
    
}
