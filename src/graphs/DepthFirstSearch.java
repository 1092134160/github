package graphs;

public class DepthFirstSearch {

	private boolean[] marked;//v与s是否连通
	private int count;//与s连通的顶点总数
	
	public DepthFirstSearch(Graph G,int s) {
		marked = new boolean[G.V()];
		dfs(G,s);
	}

	public void dfs(Graph G,int s) {
		marked[s] = true;
		count++;
		for(int w : G.adj(s)) {
			if(!marked(w)) {
				dfs(G,w);
			}
		}
	}
	
	public boolean marked(int s) {
		return marked[s];
	}

	public int count() {
		return count;
	}

}
