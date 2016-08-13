package graphs;

import fundamentals.Queue;
import fundamentals.Stack;

public class BreadthFirstPaths {

	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	
	public BreadthFirstPaths(Graph G,int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.E()];
		this.s = s;
		
	}

	public void bfs(Graph G,int s) {
		marked[s] = true;
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(s);
		while(!queue.isEmpty()) {
			int v = queue.dequeue();
			for(int w : G.adj(v)) {
				if(!hasPathTo(w)) {
					edgeTo[w] = v;
					marked[w] = true;
					queue.enqueue(w);
				}
			}
		}		
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v) {
		if(!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		for(int x=v; x!=s; x=edgeTo[x]) {
			path.push(s);
		}
		path.push(s);
		return path;
	}
	
}
