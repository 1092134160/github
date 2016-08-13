package graphs;

import fundamentals.Stack;

public class DepthFirstPaths {
	
	private boolean[] marked;//v��s�Ƿ���ͨ
	private int[] edgeTo;//����㵽��һ��������֪·���ϵ����һ������
	private final int s;//���

	public DepthFirstPaths(Graph G,int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.E()];
		this.s = s;
		dfs(G,s);
	}

	public void dfs(Graph G,int v) {
		marked[s] = true;
		for(int w : G.adj(s)) {
			if(!marked[s]) {
				edgeTo[w] = s;
				dfs(G,w);
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v) {
		if(!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		for(int x=v; x!=s; x=edgeTo[v]) {
			path.push(x);
		}
		path.push(s);
		return path;
	}
}
