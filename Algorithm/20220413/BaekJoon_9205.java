package hw_20220413;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_9205 {

	static int n;
	static Point start, end;
	static ArrayList<Point> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_9205_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			st = new StringTokenizer(br.readLine());
			end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			if(bfs()) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}
			
		}
	}

	private static boolean bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(start);
		boolean[] v = new boolean[n];
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			if (Math.abs(end.x - p.x) + Math.abs(end.y - p.y) <= 1000) {
				return true;
			}
			for (int i = 0; i < n; i++) {
				if (!v[i] && Math.abs(list.get(i).x - p.x) + Math.abs(list.get(i).y - p.y) <= 1000) {
					v[i] = true;
					queue.offer(new Point(list.get(i).x, list.get(i).y));
				}
			}
		}
		return false;
	}

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}