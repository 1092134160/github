package graphs;

public class CC {

	boolean[] marked;
	int[] id;
	int count;
	
	public CC(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for(int s = 0; s < G.V(); s++) {
			if(!marked[s]) {
				dfs(G,s);
				count++;
			}
		}
	}

	public void dfs(Graph G,int v) {
		marked[v] = true;
		id[v] = count;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				dfs(G,w);
			}
		}
	}
	
	public boolean connected(int w,int v) {
		return id[w] == id[v];
	}
	
	public int id(int w) {
		return id[w];
	}
	
	public int count() {
		return count;
	}
	
}
