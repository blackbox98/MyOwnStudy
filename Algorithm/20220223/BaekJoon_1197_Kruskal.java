package hw_20220223;

// Kruskal 알고리즘 사용

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_1197_Kruskal {

	static int V, E;
	static int[] p;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_1197_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		ArrayList<Edge> edges = new ArrayList<>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(edges);

		makeSet();
		int sum = 0;
		int cnt = 0;
		for (int i = 0; i < E; i++) {
			Edge edge = edges.get(i);
			if (findSet(edge.s) != findSet(edge.e)) {
				union(edge.s, edge.e);
				sum += edge.w;
				cnt++;
			}
			if (cnt == V) {
				break;
			}
		}
		System.out.println(sum);
	}
	
	static class Edge implements Comparable<Edge>{
		int s, e, w;

		public Edge(int s, int e, int w) {
			super();
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}

		@Override
		public String toString() {
			return "Edge [s=" + s + ", e=" + e + ", w=" + w + "]";
		}
		
	}
	
	static void makeSet() {
		p = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			p[i] = i;
		}
	}
	
	static int findSet(int a) {
		if (p[a] == a) return a;
		return findSet(p[a]);
	}
	
	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return;
		p[bRoot] = aRoot;
	}
}