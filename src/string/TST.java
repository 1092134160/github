package string;

import fundamentals.Queue;

public class TST<Value> {

	private Node root;
	
	public TST() {
		// TODO Auto-generated constructor stub
	}
	
	private class Node {
		char c;
		Node left,mid,right;
		Value val;
	}

	public Value get(String key) {
		Node node = get(root,key,0);
		return node.val;
	}

	private Node get(Node node,String key,int d) {
		if(node == null) return null;
		if(key.charAt(d) < node.c) return get(node.left,key,d);
		else if(key.charAt(d) > node.c) return get(node.right,key,d);
		else if(d < key.length() - 1) return get(node.mid,key,d+1);
		else return node;
	}

	public void put(String key, Value val) {
		root = put(root,key,val,0);
	}

	private Node put(Node node,String key,Value val,int d) {
		char c = key.charAt(d);
		if(node == null) {
			node = new Node();
			node.c = c;
		}
		if(c < node.c)
			node.left = put(node.left,key,val,d);
        else if(c > node.c)
        	node.right = put(node.right,key,val,d);
		else if(d < key.length() - 1) {
			node.mid = put(node.mid,key,val,d+1);
		}
		else node.val = val;
		return node;
	}

	public Iterable<String> keys() {
		return keysWithPrefix("");
	}

	public Iterable<String> keysWithPrefix(String pre) {
		Queue<String> q = new Queue<String>();
		Node node = get(root,pre,0);
		if(node == null) return q;
		if(node.val != null) q.enqueue(pre);
		collect(node.mid,pre,q);
		return q;
	}

	public void collect(Node node,String pre,Queue<String> q){
		if(node == null) return;
		collect(node.left,pre,q);
		if(node.val != null) q.enqueue(pre + node.c);
		collect(node.mid,pre + node.c,q);
		collect(node.right,pre,q);
	}

	public Iterable<String> keysWithMatch(String pat) {
		Queue<String> q = new Queue<String>();
		collect(root,"",pat,q);
		return q;	
	}

	public void collect(Node node, String pre,String pat, Queue<String> q) {
		if(node == null) return;
		char c = pat.charAt(pre.length());
		if(c == '.' || c < node.c) collect(node.left,pre,pat,q);
		if(c == '.' || c == node.c) {
			if(pre.length() == pat.length() && node.val != null) q.enqueue(pre+node.c);
			else
				collect(node.mid,pre+node.c,pat,q);
		} 
		if(c == '.' || c > node.c) 
			collect(node.right,pre,pat,q);
	}

	public String longestPrefixOf(String s) {
		return s.substring(0, search(root,s,0,0));
	}
	
	public int search(Node node,String s,int d,int length) {
		if(node == null) return length;
		if(d == s.length()) return length;
		if(s.charAt(d) < node.c) {
			return search(node.left,s,d+1,length);
		} else if(s.charAt(d) > node.c) {
			return search(node.right,s,d+1,length);
		} else {
			if(node.val != null) length = d;
			return search(node.mid,s,d+1,length);
		}
	}

	/*private Node delete(Node node,String key,int d) {
		if(node == null) return null;
		if(key.length() == d)
			node.val = null;
		else {
			char c = key.charAt(d);
			if(c < node.c) node.left = delete(node.left,key,d);
			else if(c == node.c) node.mid = delete(node.mid,key,d+1);
			else if(c > node.c) node.right = delete(node.right,key,d);
		}
		if(node.val != null) return node;
		
	}*/
	
	/*public String longestPrefixOf(String query) {
        if (query == null || query.length() == 0) return null;
        int length = 0;
        Node<Value> x = root;
        int i = 0;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if      (c < x.c) x = x.left;
            else if (c > x.c) x = x.right;
            else {
                i++;
                if (x.val != null) length = i;
                x = x.mid;
            }
        }
        return query.substring(0, length);
    }*/

}
