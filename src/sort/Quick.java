package sort;

@SuppressWarnings({ "rawtypes"})
public class Quick extends Template {

	public static void main(String[] args) {
		Comparable[] a = {5,1,8,9,6,22,78,54,16};
		sort(a);
		if(isSorted(a)) 
			show(a);
	}
	
	public static void sort(Comparable[] a) {
		sort(a,0,a.length - 1);
	}
	
	public static void sort(Comparable[] a,int low, int high) {
		if(low >= high)
			return;
		int p = partition(a,low,high);
		sort(a,low,p);
		sort(a,p + 1,high);
	}
	
	public static int partition(Comparable[] a,int low,int high) {
		Comparable v = a[low];
		int left = low;
		int right = high + 1;
		while(left < right) {
			while(right > left && less(v,a[--right]));
			while(right > left && less(a[++left],v));
			exch(a,left,right);
		}
		exch(a,low,left);
		return left;
	}
	
}
