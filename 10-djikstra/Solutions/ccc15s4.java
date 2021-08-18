import java.util.*;
import java.io.*;
public class CCC2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		int k = readInt(), n = readInt(), m = readInt();
		ArrayList<Edge> adj[] = new ArrayList[n+1];
		for (int i = 0; i <= n; i++) {
			adj[i] = new ArrayList();
		}
		for (int i = 0; i < m; i++) {
			int a = readInt(), b = readInt(), t = readInt(), h = readInt();
			adj[a].add(new Edge(b,t,h));
			adj[b].add(new Edge(a,t,h));
		}
		int c = readInt(), d = readInt();
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		int[][] dis = new int[n+1][k+1];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(dis[i], Integer.MAX_VALUE);
		}
		dis[c][k] = 0; q.add(new Edge(c,0,k));
		while (!q.isEmpty()) {
			Edge cur = q.poll();
			for (Edge nxt: adj[cur.v]) {
				if (cur.x - nxt.x > 0 && dis[nxt.v][cur.x-nxt.x] > dis[cur.v][cur.x] + nxt.w) {
					dis[nxt.v][cur.x-nxt.x] = dis[cur.v][cur.x] + nxt.w;
					q.add(new Edge(nxt.v,dis[nxt.v][cur.x-nxt.x],cur.x-nxt.x));
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i <= k; i++) {
			if (dis[d][i] != Integer.MAX_VALUE) {
				min = Math.min(min, dis[d][i]);
			}
		}
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	static class Edge implements Comparable<Edge>{
		int v, w, x;
		Edge (int v, int w, int x){
			this.v = v;
			this.w = w;
			this.x = x;
		}
		public int compareTo(Edge e) {
			if (this.w == e.w) {
				return this.x - e.x;
			}
			return this.w - e.w;
		}
	}
	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	static long readLong () throws IOException {
		return Long.parseLong(next());
	}
	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}
	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}
	static char readCharacter () throws IOException {
		return next().charAt(0);
	}
	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
