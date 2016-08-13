package string;

public class KMP {

	public static void main(String args[]) {
		KMP kmp = new KMP();
		System.out.println(kmp.index_KMP("aaaaac", "aaac", 0));
	}
	
	private void get_nextval(int[] nextval,String t) {
		int N = t.length();
		int j = -1;
		int i = 0;
		nextval[i] = j;
		while(i < N-1) {
			if(j == -1 || t.charAt(i) == t.charAt(j)) {
				i++;
				j++;
				if(t.charAt(i) != t.charAt(j)) 
					nextval[i] = j;
				else 
					nextval[i] = nextval[j];
			} else {
				j = nextval[j];
			}
		}
	}
	
	public int index_KMP(String s,String t,int pos) {
		int i = pos;
		int j = 0;
		int[] nextval = new int[t.length()];
		get_nextval(nextval,t);
		while(i < s.length() && j < t.length()) {
			if(j == -1 || s.charAt(i) == t.charAt(j)) {
				i++;
				j++;
			} else
				j = nextval[j];
		}
		if(j >= t.length()) return i - t.length();
		else return -1;
	}

}
