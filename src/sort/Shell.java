package sort;

@SuppressWarnings("rawtypes")
public class Shell extends Template {

	public static void sort(Comparable[] a) {
		int N = a.length;
		int h = 1;
		while(h <= N / 3) h = h * 3 + 1;
		while(h >= 1) {
			for(int i = h; i < a.length; i++)
				for(int j = i; j > h && less(a[j],a[j-h]); j--)
					exch(a,j,j - h);
			h = h / 3;
		}
	}
	
	public static void main(String args[]) {
		Comparable[] a = {1,5,8,9,6};
		sort(a);
		if(isSorted(a)) 
			show(a);
	}

}
