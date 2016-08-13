package sort;

@SuppressWarnings("rawtypes")
public class Insertion extends Template {

	public static void sort(Comparable[] a) {
		for(int i = 1; i < a.length; i++)
			for(int j = i; j > 0 && less(a[j],a[j-1]); j--)
				exch(a,j,j-1);
	}
	
	public static void main(String args[]) {
		Comparable[] a = {5,1,8,9,6};
		sort(a);
		if(isSorted(a)) 
			show(a);
	}

}
