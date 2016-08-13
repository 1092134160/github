package sort;

@SuppressWarnings({ "rawtypes","unchecked"})
public class Quick3way extends Template {

	public static void main(String[] args) {
		Comparable[] a = {5,1,8,9,6,22,78,54,16};
		sort(a);
		if(isSorted(a)) 
			show(a);
	}
	
	public static void sort(Comparable[] a) {
		sort(a,0,a.length - 1);
	}
	
	public static void sort(Comparable[] a, int low, int high) {
		if(low >= high)
			return;
		int lt = low;
		int gt = high;
		int i = low + 1;
		Comparable v = a[low];
		while(i <= gt) {
			int cmp = a[i].compareTo(v);
			if(cmp < 0) exch(a,i++,lt++);
			else if(cmp > 0) exch(a,i,gt--);
			else i++;
		}
		sort(a,low,lt - 1);
		sort(a,gt + 1,high);
	}

}
