package search;

public class RedBlackBST<Key extends Comparable<Key>,Value> {

	private static final boolean RED = true;
	private static final boolean BLACK = true;
	private Node root;
	
	private class Node {
		Key key;
		@SuppressWarnings("unused")
		Value value;
		int N;
		private Node left,right;
		private boolean color;
		Node(Key key, Value value, int n, boolean color) {
			super();
			this.key = key;
			this.value = value;
			N = n;
			this.color = color;
		}	
	}
	
	private boolean isRed(Node node) {
		if(node == null) return false;
		return node.color;
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node node) {
		if(node == null) return 0;
		return node.N;
	}
	
	private Node rotateLeft(Node node) {
		Node x = node.right;
		node.right = x.left;
		x.left = node;
		x.color = node.color;
		node.color = RED;
		x.N = node.N;
		node.N = size(node.left) + size(node.right) + 1;
		return x;
	}
	
	private Node rotateRight(Node node) {
		Node x = node.left;
		node.left = x.right;
		x.right = node;
		x.color = node.color;
		node.color = RED;
		x.N = node.N;
		node.N = 1 + size(node.left) + size(node.right);
		return x;
	}
	
	private void flipColor(Node node) {
		node.left.color = BLACK;
		node.right.color = BLACK;
		node.color = RED;
	}
	
	public void put(Key key,Value value) {
		root = put(root,key,value);
		root.color = BLACK;
	}
	
	private Node put(Node node,Key key, Value value) {
		if(node == null) return new Node(key,value,1,RED);
		int cmp = node.key.compareTo(key);
		if(cmp > 0) node.left = put(node.left,key,value);
		else if(cmp < 0) node.right = put(node.right,key,value);
		else node.value = value;
		if(!isRed(node.left) && isRed(node.right)) node = rotateLeft(node);
		if(isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
		if(isRed(node.left) && isRed(node.right)) flipColor(node);
		node.N = 1 + size(node.left) + size(node.right);
		return node;
	}
}
