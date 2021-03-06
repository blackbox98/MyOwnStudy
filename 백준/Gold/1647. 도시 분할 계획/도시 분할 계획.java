import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, ans;
	static ArrayList<Edge>[] edges;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<Edge>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			edges[start].add(new Edge(end, dist));
			edges[end].add(new Edge(start, dist));
		}

		// prim
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.addAll(edges[1]);
		v = new boolean[N + 1];
		v[1] = true;
		int cnt = 0;
		int max = 0;
		while (!pq.isEmpty()) {
			Edge p = pq.poll();
			if(!v[p.e]) {
				v[p.e]= true; 
				pq.addAll(edges[p.e]);
				ans += p.w;
				if (max < p.w) {
					max = p.w;
				}
				cnt++;
				if (cnt == M) {
					break;
				}
			}
		}

		System.out.println(ans - max);
	}

	static class Edge implements Comparable<Edge> {
		int e, w;

		public Edge(int c, int d) {
			super();
			this.e = c;
			this.w = d;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
}