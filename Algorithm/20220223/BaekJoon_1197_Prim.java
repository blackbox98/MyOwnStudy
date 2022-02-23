package hw_20220223;

// Prim 알고리즘 사용

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_1197_Prim {

	static int V, E, ans;
	static int[][] adj;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_1197_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		ArrayList<Edge>[] adj = new ArrayList[V+1];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<Edge>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[s].add(new Edge(e, w));
			adj[e].add(new Edge(s, w));
		}

		boolean[] v = new boolean[V+1];
		int[] dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		dist[0] = 0;
		v[0] = true;
		
		for (int cnt = 1; cnt < V; cnt++) {
			int minD = Integer.MAX_VALUE;
			int minIdx = -1;
			for (int i = 1; i <= V; i++) {
				if (!v[i] && dist[i] < minD) {
					minIdx = i;
					minD = dist[i];
				}
			}
			v[minIdx] = true;
			for (int i = 0; i < adj[minIdx].size(); i++) {
				Edge edge = adj[minIdx].get(i);
				if (!v[edge.e] && edge.w != 0 && dist[edge.e] > edge.w) {
					dist[edge.e] = edge.w;
				}
			}
		}
		for (int i = 0; i < dist.length; i++) {
			ans += dist[i];
		}
		System.out.println(ans);
	}

	static class Edge {
		int e, w;

		public Edge(int e, int w) {
			super();
			this.e = e;
			this.w = w;
		}
	}
}