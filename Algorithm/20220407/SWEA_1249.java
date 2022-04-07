package hw_20220407;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_1249 {

	static int N, ans;
	static int[][] map;
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_1249_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int r = 0; r < N; r++) {
				String s = br.readLine();
				for (int c = 0; c < N; c++) {
					map[r][c] = s.charAt(c) - '0';
				}
			}
			dijkstra(0, 0);
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void dijkstra(int sr, int sc) {
		boolean[][] v = new boolean[N][N];
		int[][] minDist = new int[N][N]; // 출발지에서 현재 위치까지의 최소 비용
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minDist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		// 비용의 최소합
		PriorityQueue<int[]> pQueue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		minDist[sr][sc] = 0;
		pQueue.offer(new int[] {sr, sc, minDist[sr][sc]});
		
		int r, c, minD, nr, nc, current[];
		while (true) {
			current = pQueue.poll(); // pQueue 안의 정점 중 출발지에서 자신으로의 비용이 최소인 정점의 정보
			r = current[0];
			c = current[1];
			minD = current[2];
			
			if (v[r][c]) {
				continue;
			}
			
			v[r][c] = true;
			
			if (r == N - 1 && c == N - 1) {
				ans = minD; // 도착지라면 끝내기
				return;
			}
			
			// 현 정점의 인접정점을 들여다보며 최소비용 갱신
			for (int d = 0; d < 4; d++) {
				nr = r + dr[d];
				nc = c + dc[d];
				if (check(nr, nc) && minDist[nr][nc] > minD + map[nr][nc]) {
					minDist[nr][nc] = minD + map[nr][nc];
					pQueue.offer(new int[] {nr, nc, minDist[nr][nc]});
				}
			}
		}
	}
	
	private static boolean check(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < N) return true;
		return false;
	}
}