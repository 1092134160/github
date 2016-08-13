package search;

@SuppressWarnings("unchecked")
public class BinarySearchST<Key extends Comparable<Key>,Value> {
	
	private Key[] keys;
	private Value[] values;
    private int N;
    
	public BinarySearchST(int capacity) {
    	keys = (Key[]) new Comparable[capacity];
    	values = (Value[]) new Object[capacity];
    }
    
    public int size() {
    	return N;
    }
    
    public boolean isEmpty() {
    	return N == 0;
    }
    
    public Value get(Key key) {
    	if(isEmpty()) return null;
    	int i = rank(key);
    	if(i < N && key.compareTo(keys[i]) == 0) return values[i];
    	else return null;
    }
    
    public void put(Key key,Value value) {
    	int i = rank(key);
    	if(i < N && key.compareTo(keys[i]) == 0) {
    		values[i] = value;
    		return;
    	}
    	if(N == keys.length) return;
    	for(int j = N; j > i; j--) {
    		keys[j] = keys[j-1];
    		values[j] = values[j-1];
    	}
    	keys[i] = key;
    	values[i] = value;
    	N++;
    }
    
    public int rank(Key key) {
    	int low = 0;
    	int high = N - 1;
    	while(low <= high) {
    		int mid = (low + high) / 2;
    		if(keys[mid].compareTo(key) > 0) 
    			low = mid + 1;
    		else if(keys[mid].compareTo(key) < 0)
    			high = mid - 1;
    		else 
    			return mid;
    	}
    	return low;
    }
    
    public int rankRecursion(Key key,int low,int high) {
    	if(low > high) 
    		return low;
    	int mid = (low + high) / 2;
    	int cmp = key.compareTo(keys[mid]);
    	if(cmp > 0) 
    		return rankRecursion(key,mid + 1,high);
    	else if(cmp < 0) 
    		return rankRecursion(key,low,mid - 1);
    	else 
    		return mid;
    }

    public Key min() {
    	return keys[0];
    }
    
    public Key max() {
    	return keys[N-1];
    }
    
    public Key select(int k) {
    	if (k < 0 || k >= N) return null;
    	return keys[k];
    }
    
    public Key celing(Key key) {
    	int i = rank(key);
    	if(i == N) return null;
    	return keys[i];
    }
    
    public Key floor(Key key) {
    	int i = rank(key);
    	if(i < N && keys[i].compareTo(key) == 0) 
    		return keys[i];
    	else if(i == 0) return null;
    	return keys[i-1];
    }
    
    public void delete(Key key) {
    	int i = rank(key);
    	if(i == N || keys[i].compareTo(key) != 0)
    		return;
    	if(i < N && keys[i].compareTo(key) == 0) {
    		for(int j = i; j < N; j++) {
    			keys[j] = keys[j+1];
    			values[j] = values[j+1];
    		}
    	}
    	N--;
        keys[N] = null;  // to avoid loitering
        values[N] = null;
    }
    
}
