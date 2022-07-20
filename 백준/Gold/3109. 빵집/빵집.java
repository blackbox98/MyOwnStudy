import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C, ans;
	static int[] dr = { -1, 0, 1 };
	static char[][] map;
	static boolean[][] v;
	static boolean[] goal;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		v = new boolean[R][C];
		goal = new boolean[R];
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
			}
		}
		for (int r = 0; r < R; r++) {
			check(r, 0, r);
		}
		System.out.println(ans);
	}
	
	private static void check(int r, int c, int sr) {
		if (c == C-1) {
			goal[sr] = true;
			ans++;
			return;
		}
		
		for (int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			if (nr >= 0 && nr < R && c+1 < C && !v[nr][c+1] && !goal[sr] && map[nr][c+1] == '.') {
				v[nr][c+1] = true;
				check(nr, c+1, sr);
			}
		}
	}
}