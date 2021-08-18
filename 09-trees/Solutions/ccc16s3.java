import java.util.*;
import java.io.*;
public class CCC2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<Integer> adj[];
	static boolean[] pho;
	static int far;
	static int max;
	static int n;
	static int og;
	public static void main(String[] args) throws IOException {
		n = readInt(); int m = readInt();
		og = n;
		pho = new boolean[n];
		int first = -1;
		for (int i = 0; i < m; i++) {
			int x = readInt();
			if (first == -1) {
				first = x;
			}
			pho[x] = true;
		}
		adj = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList();
		}
		for (int i = 0; i < n-1; i++) {
			int a = readInt(), b = readInt();
			adj[a].add(b); adj[b].add(a);
		}
		dfs(first,-1);
		int[] dis = new int[og];
		far = 0; max = 0;
		bfs(first,dis);
		max = 0; 
		bfs(far,dis);
		System.out.println(2*(n-1) - max);
	}
	static void bfs(int v, int[] dis) {
		Queue<Integer> q = new LinkedList();
		boolean[] vis = new boolean[og];
		q.add(v); vis[v] = true; dis[v] = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int nxt: adj[cur]) {
				if (!vis[nxt] && pho[nxt]) {
					q.add(nxt); vis[nxt] = true; dis[nxt] = dis[cur] + 1;
					if (dis[nxt] > max) {
						max = dis[nxt];
						far = nxt;
					}
				}
			}
		}
	}
	static boolean dfs(int v, int par) {
		for (int nxt: adj[v]) {
			if (nxt != par) {
				pho[v] |= dfs(nxt,v);
			}
		}
		if (!pho[v]) {
			n--;
		}
		return pho[v];
	}
	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}
	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	static long readLong () throws IOException {
		return Long.parseLong(next());
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
