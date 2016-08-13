package sort;

@SuppressWarnings("rawtypes")
public class HeapSort extends Template {

	public static void main(String args[]) {
		Comparable[] a = {0,5,1,8,9,6,22,78,54,16};
		sort(a);
		show(a);
	}
	
	public static void sort(Comparable[] a) {
		int N = a.length - 1;
		for(int k = 2; k <= N; k++) {
			swim(a,k);
		}
		while(N > 1) {
			exch(a,1,N--);
			sink(a,1,N);
		}
	}
	
	public static void sink(Comparable[]a,int k,int N) {
		while(k <= N/2) {
			int j = 2*k;
			if(j < N && less(a[j],a[j+1])) j++;
			if(less(a[j],a[k])) break;
			exch(a,k,j);
			k = j;
		}
	}
	
	public static void swim(Comparable[]a,int k) {
		while(k > 1 && less(a[k/2],a[k])) {
			exch(a,k,k/2);
			k = k/2;
		}
	}

}
