package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1861 {

	static int[] dr = { 0, -1, 0, 1 }; // 좌 상 우 하
	static int[] dc = { -1, 0, 1, 0 };
	static int Ans;
	static int best;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_1861_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			Ans = Integer.MAX_VALUE;
			best = 0;
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			} //
			int way; // 0 : 좌, 1 : 상, 2 : 우, 3 : 하
			int curR, curC, nr, nc, cnt;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					boolean finish = false; // 탐색 종료 여부
					boolean turn; // 방향 변경 여부
					way = 0;
					cnt = 1;
					curR = r; // 현재 map의 행 저장
					curC = c; // 현재 map의 열 저장
					while (!finish) {
						turn = true;
						nr = curR + dr[way];
						nc = curC + dc[way];
						if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
							if (map[curR][curC] + 1 == map[nr][nc]) {
								curR = nr;
								curC = nc;
								cnt++;
								way = 0; // 이동한 좌표에서 새롭게 4방 탐색을 하기 위해 방향 초기화
								turn = false;
							}
						}
						if (turn) {
							way++;
							if (way == 4) { // 4방 탐색이 끝나면
								if (best == cnt) {
									Ans = Math.min(Ans, map[r][c]);
								} else if (best < cnt) {
									best = Math.max(best, cnt);
									Ans = map[r][c];
								}
								finish = true; // 탐색 종료
								break;
							}
						}
					}
				}
			}
			System.out.printf("#%d %d %d\n", tc, Ans, best);
		}
	}
}