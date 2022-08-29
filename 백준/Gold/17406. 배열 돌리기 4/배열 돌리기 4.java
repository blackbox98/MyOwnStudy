import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K, Ans = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] com;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];

		// map 초기화
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// solving
		com = new int[K][];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			com[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()) };
		}

		// 순열
		permutation(0, new int[K], new boolean[K]);
		System.out.println(Ans);
	}

	private static void permutation(int idx, int[] sel, boolean[] v) {
		// base part
		if (idx == K) {
			// solving
			int value = solve(sel);
			Ans = Math.min(Ans, value);
			return;
		}

		// inductive part
		for (int i = 0; i < K; i++) {
			if (!v[i]) {
				v[i] = true;
				sel[idx] = i;
				permutation(idx + 1, sel, v);
				v[i] = false;
			}
		}
	}

	private static int solve(int[] sel) {
		// 배열 복사
		int[][] tmpMap = new int[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}

		// 명령어 수 만큼 회전
		// 기준값 계산하기
		// (r-s, c-s) -> (r+s, c+s)
		for (int i = 0; i < sel.length; i++) {
			// 회전할 배열 크기
			// left top
			int osr = com[sel[i]][0] - com[sel[i]][2];
			int osc = com[sel[i]][1] - com[sel[i]][2];
			// right bottom
			int oer = com[sel[i]][0] + com[sel[i]][2];
			int oec = com[sel[i]][1] + com[sel[i]][2];

			// 몇 단계를 회전할 지 계산
			int step = Math.min(Math.abs(oer - osr), Math.abs(oec - osc)) / 2;
			for (int d = 0; d < step; d++) {
				// 이동하는 좌표 값
				int sr = osr + d;
				int sc = osc + d;
				int er = oer - d;
				int ec = oec - d;

				// 방향
				int dir = 0;
				// 시작점 값 복사
				int tmp = tmpMap[sr][sc];
				// 한바퀴 회전
				while (dir < 4) {
					int nr = sr + dr[dir];
					int nc = sc + dc[dir];
					// 경계선 안에 있다면 dir 방향으로 전진
					if (nr >= osr + d && nr <= oer - d && nc >= osc + d && nc <= oec - d) {
						tmpMap[sr][sc] = tmpMap[nr][nc];
						sr = nr;
						sc = nc;
					}
					// 경계선을 벅어나면 방향을 바꿔줌
					else {
						dir++;
					}
				}
				tmpMap[sr][sc + 1] = tmp;
			}
		}
		// 최소값 구하기
		int res = Integer.MAX_VALUE;
		for (int i = 1; i < tmpMap.length; i++) {
			int sum = 0;
			for (int j = 1; j < tmpMap[i].length; j++) {
				sum += tmpMap[i][j];
			}
			res = Math.min(res, sum);
		}
		return res;
	}
}