package sort;

@SuppressWarnings("rawtypes")
public class BubbleSort extends Template {

	public static void main(String[] args) {
		Comparable[] a = {0,5,1,8,9,6,22,78,54,16};
		sort(a);
		show(a);

	}
	
	public static void sort(Comparable[] a) {
		boolean flag = true;
		for(int i = 1; i < a.length && flag; i++) {
			flag = false;
			for(int j = a.length - 2; j >= i; j--) {
				if(less(a[j+1],a[j])) {
					exch(a,j,j+1);
					flag = true;
				}
			}
		}
	}

}
