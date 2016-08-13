package sort;

import java.util.HashMap;;

public class LRUCache {

	class Node {

		int value;
		int key;
		Node pre;
		Node next;

		Node(int key, int value) {
			this.value = value;
			this.key = key;
			this.pre = null;
			this.next = null;
		}

	}

	private HashMap<Integer, Node> map;
	private int capacity;
	private int number;
	Node head;
	Node tail;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		number = 0;
		head = new Node(-1, -1);
		head.pre = null;
		head.next = null;
		tail = head;
		map = new HashMap<Integer, Node>();
	}

	public int get(int key) {
		Node ret = map.get(key);
		if (ret == null)
			return -1;
		refresh(ret);
		return ret.value;
	}

	public void set(int key, int value) {
		Node ret = map.get(key);
		if (ret != null) {
			refresh(ret);
			ret.value = value;
			return;
		}
		if (capacity == number) {	
			map.remove(tail.key);
			// map.put(key,value);
			number--;
			tail = tail.pre;
			tail.next = null;
		}
		Node putNode = new Node(key, value);
		tail.next = putNode;
		putNode.pre = tail;
		putNode.next = null;
		tail = putNode;
		map.put(key, putNode);
		number++;
		refresh(putNode);
	}

	private void refresh(Node node) {
		if (head.next == node)
			return;
		Node temp = head.next;
		Node nodePre = node.pre;
		Node nodeNext = node.next;
		head.next = node;
		node.pre = head;
		node.next = temp;
		temp.pre = node;
		nodePre.next = nodeNext;
		if (nodeNext != null)
			nodeNext.pre = nodePre;
		else
			tail = nodePre;
	}

	public static void main(String args[]) {
		LRUCache l = new LRUCache(1);
		l.set(2, 1);
		System.out.println(l.get(2));
		l.set(3, 2);
		System.out.println(l.get(2));
		l.get(3);
	}
}
