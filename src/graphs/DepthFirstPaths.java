package graphs;

import fundamentals.Stack;

public class DepthFirstPaths {
	
	private boolean[] marked;//v与s是否连通
	private int[] edgeTo;//从起点到到一个顶点已知路径上的最后一个顶点
	private final int s;//起点

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
