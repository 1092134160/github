package sort;

@SuppressWarnings("unchecked")
public class MaxPQ<Key extends Comparable<Key>> {

	private Key[] pq;
	private int N = 0;

	public MaxPQ(int maxN) {
		pq =  (Key[]) new Comparable[maxN + 1];
	}
	
	public boolean isEmpty(Integer[] pq) {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void insert(Key v) {
		pq[++N] = v;
		swim(N);
	}
	
	public Key delMax() {
		Key max = pq[1];
		exch(1,N--);
		pq[N+1] = null;
		sink(1);
		return max;
	}
	
	private boolean less(int i,int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void exch(int i,int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	
	private void swim(int k) {
		while(k > 1 && less(k/2,k)) {
			exch(k/2,k);
			k = k / 2;
		}
	}
	
	private void sink(int k) {
		while(2 * k < N) {
			int j = 2*k;
			if(j < N && less(j,j+1)) j++;
			if(less(j,k)) break;
			exch(k,j);
			k = j;
		}
	}
	
	public void sort(Key[] pq) {
		for(int i = N / 2; i > 0; i--) {
			sink(i);
		}
		while(N > 0) {
			exch(1,N--);
			sink(N);
		}
	}
	
	public static void main(String[] args) {
		
	}
	
}
