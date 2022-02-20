package Daily_Practice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 미로_도착지점 {

	static int R, C;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/미로_도착지점.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			int J = Integer.parseInt(st.nextToken());
			int[][] maze = new int[N+1][N+1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < J; i++) {
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				maze[r][c] = 1; // 점퍼의 위치를 1로 표시
			}
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			L : for (int i = 0; i < M; i++) {
				int dir = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				for (int j = 0; j < k; j++) {
					int nr = sr + dr[dir-1];
					int nc = sc + dc[dir-1];
					if (nr > 0 && nr <= N && nc > 0 && nc <= N) {
						if (maze[nr][nc] == 1) {
							R = 0;
							C = 0;
							break L;
						}
						sr = nr;
						sc = nc;
					} else {
						R = 0;
						C = 0;
						break L;
					}
				}
				R = sr;
				C = sc;
			}
			System.out.printf("#%d %d %d\n", tc, R, C);
		}
	}
}