package graphs;

public class DepthFirstSearch {

	private boolean[] marked;//v��s�Ƿ���ͨ
	private int count;//��s��ͨ�Ķ�������
	
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
