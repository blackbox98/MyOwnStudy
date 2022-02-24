package hw_20220224;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_1753 {

	static class Point {
		int e, w;

		public Point(int e, int w) {
			super();
			this.e = e;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_1753_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine()) - 1;
		ArrayList<Point>[] adjList = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			adjList[s].add(new Point(e, w));
		}
		
		int[] distance = new int[V];
		Arrays.fill(distance, Integer.MAX_VALUE);
		boolean[] v = new boolean[V];
		distance[K] = 0;
		
		for (int cnt = 0; cnt < V; cnt++) {
			int min = Integer.MAX_VALUE;
			int current = 0;
			for (int i = 0; i < V; i++) {
				if(!v[i] && min > distance[i]) {
					min = distance[i];
					current = i;
				}
			}
			v[current] = true;
			for (int i = 0; i < adjList[current].size(); i++) {
				if (!v[adjList[current].get(i).e] && adjList[current].get(i).w != 0 && distance[adjList[current].get(i).e] > distance[current] + adjList[current].get(i).w) {
					distance[adjList[current].get(i).e] = distance[current] + adjList[current].get(i).w;
				}
			}
		}
		for (int i = 0; i < V; i++) {
			if (distance[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
				continue;
			}
			System.out.println(distance[i]);
		}
	}
}