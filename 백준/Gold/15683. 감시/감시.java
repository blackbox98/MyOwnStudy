import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M, ans = Integer.MAX_VALUE;
	static ArrayList<Point> points;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		N = str.charAt(0) - '0';
		M = str.charAt(2) - '0';
		map = new int[N][M];
		points = new ArrayList<>();
		int cnt0 = 0;
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] != 0 && map[r][c] != 6) {
					points.add(new Point(r, c, map[r][c]));
				}
				if (map[r][c] == 0) {
					cnt0++;
				}
			}
		}
		
		if (points.isEmpty()) {
			ans = cnt0;
		} else {
			for (int i = 0; i < points.size(); i++) {
				dfs(points.get(i).r, points.get(i).c, points.get(i).cNum, 0, new int[points.size()]);
			}
		}
		
		System.out.println(ans);
	}

	static class Point {
		int r, c, cNum;

		Point(int r, int c, int cNum) {
			this.r = r;
			this.c = c;
			this.cNum = cNum;
		}
	}

	private static void dfs(int r, int c, int cNum, int idx, int[] dir) {
		// base part
		if (idx == points.size()) {
			int cntZero = 0;
			int[][] tmpMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					tmpMap[i][j] = map[i][j];
				}
			}
			for (int k = 0; k < points.size(); k++) {
				int nr = points.get(k).r;
				int nc = points.get(k).c;
				watch(tmpMap, nr, nc, tmpMap[nr][nc], dir[k]);
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(tmpMap[i][j] == 0) {
						cntZero++;
					}
				}
			}
			ans = Math.min(ans, cntZero);
			return;
		}

		// inductive part
		for (int d = 1; d <= 4; d++) {
			int nr = points.get(idx).r;
			int nc = points.get(idx).c;
			dir[idx] = d;
			dfs(nr, nc, map[nr][nc], idx + 1, dir);
		}
	}

	private static void watch(int[][] tmpMap, int r, int c, int cNum, int d) {
		switch (cNum) {
		case 1:
			// d => 1 : 상, 2 : 우 , 3 : 하, 4 : 좌
			switch (d) {
			case 1:
				for (int i = r; i >= 0; i--) {
					if (tmpMap[i][c] == 0) {
						tmpMap[i][c] = cNum;
					}
					if (tmpMap[i][c] == 6) break;
				}
				break;
			case 2:
				for (int i = c; i < M; i++) {
					if (tmpMap[r][i] == 0) {
						tmpMap[r][i] = cNum;
					}
					if (tmpMap[r][i] == 6) break;
				}
				break;
			case 3:
				for (int i = r; i < N; i++) {
					if (tmpMap[i][c] == 0) {
						tmpMap[i][c] = cNum;
					}
					if (tmpMap[i][c] == 6) break;
				}
				break;
			case 4:
				for (int i = c; i >= 0; i--) {
					if (tmpMap[r][i] == 0) {
						tmpMap[r][i] = cNum;
					}
					if (tmpMap[r][i] == 6) break;
				}
				break;
			}
			break;
		case 2:
			// d => 1, 3 : 상하, 2, 4 : 좌우
			switch (d) {
			case 1:
			case 3:
				for (int i = r; i >= 0; i--) {
					if (tmpMap[i][c] == 0) {
						tmpMap[i][c] = cNum;
					}
					if (tmpMap[i][c] == 6) break;
				}
				for (int i = r; i < N; i++) {
					if (tmpMap[i][c] == 0) {
						tmpMap[i][c] = cNum;
					}
					if (tmpMap[i][c] == 6) break;
				}
				break;
			case 2:
			case 4:
				for (int i = c; i >= 0; i--) {
					if (tmpMap[r][i] == 0) {
						tmpMap[r][i] = cNum;
					}
					if (tmpMap[r][i] == 6) break;
				}
				for (int i = c; i < M; i++) {
					if (tmpMap[r][i] == 0) {
						tmpMap[r][i] = cNum;
					}
					if (tmpMap[r][i] == 6) break;
				}
				break;
			}
			break;
		case 3:
			// d => 1 : 상우, 2 : 우하, 3 : 하좌, 4 : 좌상
			switch (d) {
			case 1:
				for (int i = r; i >= 0; i--) {
					if (tmpMap[i][c] == 0) {
						tmpMap[i][c] = cNum;
					}
					if (tmpMap[i][c] == 6) break;
				}
				for (int j = c; j < M; j++) {
					if (tmpMap[r][j] == 0) {
						tmpMap[r][j] = cNum;
					}
					if (tmpMap[r][j] == 6) break;
				}
				break;
			case 2:
				for (int i = c; i < M; i++) {
					if (tmpMap[r][i] == 0) {
						tmpMap[r][i] = cNum;
					}
					if (tmpMap[r][i] == 6) break;
				}
				for (int j = r; j < N; j++) {
					if (tmpMap[j][c] == 0) {
						tmpMap[j][c] = cNum;
					}
					if (tmpMap[j][c] == 6) break;
				}
				break;
			case 3:
				for (int i = r; i < N; i++) {
					if (tmpMap[i][c] == 0) {
						tmpMap[i][c] = cNum;
					}
					if (tmpMap[i][c] == 6) break;
				}
				for (int j = c; j >= 0; j--) {
					if (tmpMap[r][j] == 0) {
						tmpMap[r][j] = cNum;
					}
					if (tmpMap[r][j] == 6) break;
				}
				break;
			case 4:
				for (int i = r; i >= 0; i--) {
					if (tmpMap[i][c] == 0) {
						tmpMap[i][c] = cNum;
					}
					if (tmpMap[i][c] == 6) break;
				}
				for (int j = c; j >= 0; j--) {
					if (tmpMap[r][j] == 0) {
						tmpMap[r][j] = cNum;
					}
					if (tmpMap[r][j] == 6) break;
				}
				break;
			}
			break;
		case 4:
			// d => 1 : 좌상우, 2 : 상우하, 3 : 우하좌, 4 : 하좌상
			switch (d) {
			case 1:
				for (int i = r; i >= 0; i--) {
					if (tmpMap[i][c] == 0) {
						tmpMap[i][c] = cNum;
					}
					if (tmpMap[i][c] == 6) break;
				}
				for (int j = c; j >= 0; j--) {
					if (tmpMap[r][j] == 0) {
						tmpMap[r][j] = cNum;
					}
					if (tmpMap[r][j] == 6) break;
				}
				for (int j = c; j < M; j++) {
					if (tmpMap[r][j] == 0) {
						tmpMap[r][j] = cNum;
					}
					if (tmpMap[r][j] == 6) break;
				}
				break;
			case 2:
				for (int i = c; i < M; i++) {
					if (tmpMap[r][i] == 0) {
						tmpMap[r][i] = cNum;
					}
					if (tmpMap[r][i] == 6) break;
				}
				for (int j = r; j >= 0; j--) {
					if (tmpMap[j][c] == 0) {
						tmpMap[j][c] = cNum;
					}
					if (tmpMap[j][c] == 6) break;
				}
				for (int j = r; j < N; j++) {
					if (tmpMap[j][c] == 0) {
						tmpMap[j][c] = cNum;
					}
					if (tmpMap[j][c] == 6) break;
				}
				break;
			case 3:
				for (int i = r; i < N; i++) {
					if (tmpMap[i][c] == 0) {
						tmpMap[i][c] = cNum;
					}
					if (tmpMap[i][c] == 6) break;
				}
				for (int j = c; j >= 0; j--) {
					if (tmpMap[r][j] == 0) {
						tmpMap[r][j] = cNum;
					}
					if (tmpMap[r][j] == 6) break;
				}
				for (int j = c; j < M; j++) {
					if (tmpMap[r][j] == 0) {
						tmpMap[r][j] = cNum;
					}
					if (tmpMap[r][j] == 6) break;
				}
				break;
			case 4:
				for (int i = r; i >= 0; i--) {
					if (tmpMap[i][c] == 0) {
						tmpMap[i][c] = cNum;
					}
					if (tmpMap[i][c] == 6) break;
				}
				for (int i = r; i < N; i++) {
					if (tmpMap[i][c] == 0) {
						tmpMap[i][c] = cNum;
					}
					if (tmpMap[i][c] == 6) break;
				}
				for (int j = c; j >= 0; j--) {
					if (tmpMap[r][j] == 0) {
						tmpMap[r][j] = cNum;
					}
					if (tmpMap[r][j] == 6) break;
				}
				break;
			}
			break;
		case 5:
			for (int i = r; i >= 0; i--) {
				if (tmpMap[i][c] == 0) {
					tmpMap[i][c] = cNum;
				}
				if (tmpMap[i][c] == 6) break;
			}
			for (int i = r; i < N; i++) {
				if (tmpMap[i][c] == 0) {
					tmpMap[i][c] = cNum;
				}
				if (tmpMap[i][c] == 6) break;
			}
			for (int j = c; j >= 0; j--) {
				if (tmpMap[r][j] == 0) {
					tmpMap[r][j] = cNum;
				}
				if (tmpMap[r][j] == 6) break;
			}
			for (int j = c; j < M; j++) {
				if (tmpMap[r][j] == 0) {
					tmpMap[r][j] = cNum;
				}
				if (tmpMap[r][j] == 6) break;
			}
			break;
		}
	}
}