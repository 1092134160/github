package sort;

@SuppressWarnings({ "rawtypes"})
public class Merge extends Template {

	public static Comparable[] aux;
	
	public static void main(String[] args) {
		Comparable[] a = {5,1,8,9,6};
		sort(a);
		if(isSorted(a)) 
			show(a);
	}
	
	public static void sort(Comparable a[]) {
		aux = new Comparable[a.length];
		sort(a,0,a.length - 1);
	}
	
    public static void sort(Comparable a[],int low,int high) {
		if(low >= high) return;
		int mid = (low + high) / 2;
		sort(a,low,mid);
		sort(a,mid + 1, high);
		merge(a,low,mid,high);
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
