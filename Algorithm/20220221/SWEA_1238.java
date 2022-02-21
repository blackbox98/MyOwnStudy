package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1238 {

	static int N, start, depth, ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_1238_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			depth = 0;
			ans = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			Node[] node = new Node[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				node[from] = new Node(to, node[from]);
			}

			check(node, start, new boolean[N], 1);
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void check(Node[] node, int start, boolean[] v, int cnt) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(start);
		v[start] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			cnt++;
			for (Node temp = node[current]; temp != null; temp = temp.link) {
				if (!v[temp.num]) {
					queue.offer(temp.num);
					v[temp.num] = true;
					if (depth < cnt) {
						depth = cnt;
						ans = temp.num;
					}
					if (depth == cnt) {
						ans = Math.max(ans, temp.num);
					}
				}
			}
		}
	}

	public static class Node {
		int num;
		Node link;

		Node(int num, Node link) {
			this.num = num;
			this.link = link;
		}

		@Override
		public String toString() {
			return "Node [num=" + num + ", link=" + link + "]";
		}
	}
}