import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algo2_대전_7_전병찬 {

	static int M, N, ans; // 산의 가로/세로 크기와 결과값을 각각 저장하는 전역변수 선언
	static int[][] map; // 산의 지형 정보를 저장하는 map 배열 선언
	// 4방 탐색을 위한 dr, dc 배열 선언
	// 우 하 좌 상
	static int[] dr = { 0, 1, 0, -1 }; // 행 단위 이동방향 정의
	static int[] dc = { 1, 0, -1, 0 }; // 열 단위 이동방향 정의
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // bufferedReader를 통해 값을 받아옴
		int T = Integer.parseInt(br.readLine()); // 테스트케이스의 수 입력
		for (int tc = 1; tc <= T; tc++) { // 테스트케이스만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine()); // StringTokenizer를 사용해 입력 값을 공백 단위로 잘라서 받음
			M = Integer.parseInt(st.nextToken()); // 산의 세로 크기 입력
			N = Integer.parseInt(st.nextToken()); // 산의 가로 크기 입력
			map = new int[M][N]; // map의 크기 선언
			for (int r = 0; r < M; r++) { // 산의 세로 크기만큼 반복
				st = new StringTokenizer(br.readLine()); // 입력값을 공백 단위로 자르기
				for (int c = 0; c < N; c++) { // 산의 가로 크기만큼 반복
					map[r][c] = Integer.parseInt(st.nextToken()); // map 정보 채우기
				} // end for
			} // end for
			ans = 0; // 결과값을 저장하기 위한 변수 선언 및 포기화
			boolean[][] v = new boolean[M][N]; // 방문배열 선언
			v[0][0] = true; // 시작점에 대한 방문처리
			dfs(0, 0, v); // 탐색을 위한 dfs() 메소드 실행
			System.out.printf("#%d %d\n", tc, ans); // 결과 출력
		} // end for
	}
	
	private static void dfs(int sr, int sc, boolean[][] v) { // 탐색을 위한 dfs() 메소드 정의
		if (sr == M - 1 && sc == N - 1) { // 도착지에 도달한 경우
			ans++; // 결과값 + 1
			return; // 메소드 return
		} // end if
		
		for (int d = 0; d < 4; d++) { // 4방 탐색
			int nr = sr + dr[d]; // 새로운 행 좌표
			int nc = sc + dc[d]; // 새로운 열 좌표
			// 새로운 행과 열이 산의 가로/세로 범위를 넘지 않고, 아직 방문하지 않았으며, 내리막길인 경우
			if (nr >= 0 && nr < M && nc >= 0 && nc < N && !v[nr][nc] && map[sr][sc] > map[nr][nc]) {
				v[nr][nc] = true; // 새로운 좌표에 대한 방문 처리
				dfs(nr, nc, v); // dfs() 메소드 실행
				v[nr][nc] = false; // 다른 경우에서의 방문을 위한 false 처리
			} // end if
		} // end for
	} // dfs() 메소드 종료
}