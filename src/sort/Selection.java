package sort;

@SuppressWarnings("rawtypes")
public class Selection extends Template {

	public static void sort(Comparable[] a) {
		for(int i = 0; i < a.length; i++) {
			int minOfIndex = i;
			for(int j = i + 1; j < a.length; j++)
				if(less(a[j],a[minOfIndex]))
					minOfIndex = j;
			if(i != minOfIndex)
				exch(a,i,minOfIndex);
		}
	}
	
	public static void main(String args[]) {
		Comparable[] a = {1,5,8,9,6};
		sort(a);
		if(isSorted(a)) 
			show(a);
	}

}
