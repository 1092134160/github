package sort;

@SuppressWarnings({ "rawtypes"})
public class MergeBU extends Template {

	public static Comparable[] aux;
	
	public static void main(String[] args) {
		Comparable[] a = {5,1,8,9,6};
		sort(a);
		if(isSorted(a)) 
			show(a);
	}
	
	public static void sort(Comparable a[]) {
		aux = new Comparable[a.length];
		int N = a.length;
		for(int sz = 1; sz < N; sz = sz + sz)
			for(int low = 0; low + sz < N; low += sz + sz)
				merge(a,low,low + sz - 1,Math.min(low + sz + sz, N - 1));
	}

	public static void merge(Comparable a[],int low,int mid,int high) {
		int i = low;
		int j = mid + 1;
		for(int k = low; k <= high; k++)
			aux[k] = a[k];
		for(int k = low; k <= high; k++) {
			if(i > mid) a[k] = aux[j++];
			else if(j > high) a[k] = aux[i++];
			else if(less(aux[i],aux[j])) a[k] = aux[i++];
			else if(less(aux[j],aux[i])) a[k] = aux[j++];
		}
	}

}
