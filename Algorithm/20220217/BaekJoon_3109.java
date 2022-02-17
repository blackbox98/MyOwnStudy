/*
5 5
.xx..
..x..
.....
...x.
...x.

// 2

6 10
..x.......
.....x....
.x....x...
...x...xx.
..........
....x.....

// 5
 */
package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_3109 {

	static int R, C, ans;
	static int[] dr = { -1, 0, 1 };
	static char[][] map;
	static boolean[][] v;
	static boolean goal;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		v = new boolean[R][C];
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
			}
		}
		//print(map);
		for (int r = 0; r < R; r++) {
			goal = false;
			check(r, 0);
		}
		System.out.println(ans);
	}
	
	private static void check(int r, int c) {
		if (c == C-1) {
			goal = true;
			ans++;
			return;
		}
		
		for (int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			if (nr >= 0 && nr < R && c+1 < C && !v[nr][c+1] && !goal && map[nr][c+1] == '.') {
				v[nr][c+1] = true;
				check(nr, c+1);
			}
		}
	}
	
	private static void print(char[][] map) {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
	}
}