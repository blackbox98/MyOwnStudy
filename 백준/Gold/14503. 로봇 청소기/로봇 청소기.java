import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer;
    static int N;
    static int M;
    static int[][] map;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북 동 남 서
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        answer = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clean(r, c, d);
        System.out.println(answer);
    }

    private static void clean(int r, int c, int d) {
        if (map[r][c] == 0) {
            map[r][c] = 2;
            answer++;
        }
        boolean uncleaned = false;
        for (int i = 0; i < dir.length; i++) {
            int nr = r + dir[i][0];
            int nc = c + dir[i][1];
            if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
                uncleaned = true;
                break;
            }
        }
        if (uncleaned) {
            d--;
            if (d == -1) d = dir.length - 1;
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                if (map[nr][nc] == 0) {
                    r = nr;
                    c = nc;
                }
                clean(r, c, d);
            }
        } else {
            int nr = r + dir[(d + 2) % dir.length][0];
            int nc = c + dir[(d + 2) % dir.length][1];
            if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 1) clean(nr, nc, d);
        }
    }
}