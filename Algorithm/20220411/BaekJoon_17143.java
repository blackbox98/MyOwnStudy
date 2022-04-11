package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_17143 {

	static int R, C, M, ans;
	static ArrayList<Point> sharks = new ArrayList<Point>();
	/*
	  0
	3 x 2
	  1
	 */
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_17143_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharks.add(new Point(r, c, s, d, z));
		}
		fishing(0);
		System.out.println(ans);
	}

	private static void fishing(int fishking) {
		for (int i = 0; i < C; i++) {
			Collections.sort(sharks);
			for (int j = 0; j < sharks.size(); j++) {
				if (sharks.get(j).c == i) {
					ans += sharks.get(j).z;
					sharks.remove(j--);
					break;
				}
			}
			move();
		}
	}

	private static void move() {
		Point[][] map = new Point[R][C];
		ArrayList<Point> death = new ArrayList<Point>();
		for (int i = 0; i < sharks.size(); i++) {
			Point p = sharks.get(i);
			for (int s = 0; s < p.s; s++) {
				if(p.d == 1 && p.r == 0) p.d=2;
				else if(p.d == 2 && p.r == R - 1) p.d=1;
				else if(p.d == 3 && p.c == C - 1) p.d=4;
				else if(p.d == 4 && p.c == 0) p.d=3;
				
				p.r += dr[p.d - 1];
				p.c += dc[p.d - 1];
			}
			sharks.get(i).r = p.r;
			sharks.get(i).c = p.c;
			sharks.get(i).d = p.d;
			if (map[p.r][p.c] == null) {
				map[p.r][p.c] = sharks.get(i);
			} else if (map[p.r][p.c].z < p.z) {
				death.add(map[p.r][p.c]);
				map[p.r][p.c] = sharks.get(i);
			} else {
				death.add(sharks.get(i));
			}
		}
		for (int i = 0; i < death.size(); i++) {
			sharks.remove(death.get(i));
		}
//		print(map);
	}

	static class Point implements Comparable<Point> {
		int r, c, s, d, z;

		public Point(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Point o) {
			if (this.r == o.r) {
				return Integer.compare(this.c, o.c);
			}
			return Integer.compare(this.r, o.r);
		}
	}

	private static void print(Point[][] map) {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == null) {
					System.out.print("0 ");
					continue;
				}
				System.out.print(map[r][c].z + " ");
			}
			System.out.println();
		}
		System.out.println("=============");
	}
}