package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_4012 {

	static int N, ans;
	static int[][] map;
	static List<Integer> list;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_4012_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<>();
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
				list.add(r);
			}
			choose(new int[N / 2], 0, 0);
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void choose(int[] sel, int idx, int cnt) {
		if (cnt == sel.length) {
			List<Integer> B = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				B.add(list.get(i));
			}
			for (int i = 0; i < sel.length; i++) {
				B.remove(Integer.valueOf(sel[i]));
			}
			int scoreA = 0, scoreB = 0;
			for (int i = 0; i < sel.length; i++) {
				for (int j = 0; j < sel.length; j++) {
					if (sel[i] != sel[j]) {
						scoreA += map[sel[i]][sel[j]];
						scoreB += map[B.get(i)][B.get(j)];
					}
				}
			}
			ans = Math.min(ans, Math.abs(scoreA - scoreB));
			return;
		}

		for (int i = idx; i < N; i++) {
			sel[cnt] = i;
			choose(sel, i + 1, cnt + 1);
		}
	}

	private static void print(int[][] map) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}