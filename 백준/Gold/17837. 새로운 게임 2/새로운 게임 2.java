import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] board;
    static StringBuilder[][] map;
    static int[][] units;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        map = new StringBuilder[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                map[r][c] = new StringBuilder();
            }
        }
        units = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            switch (d) {
                case 1:
                    d = 2;
                    break;
                case 2:
                    d = 3;
                    break;
                case 3:
                    d = 1;
                    break;
                default:
                    break;
            }
            units[i] = new int[]{r, c, d};
            map[r][c].append(i);
        }
        int answer = 0;
        L:
        while (answer < 1000) {
            answer++;
            for (int num = 0; num < K; num++) {
                if (move(num)) break L;
            }
        }
        System.out.println(answer == 1000 ? -1 : answer);
    }

    private static boolean move(int num) {
        int[] unit = units[num];
        int r = unit[0];
        int c = unit[1];
        int idx = map[r][c].indexOf(num + "");
        StringBuilder sb = new StringBuilder(map[r][c].substring(idx));
        int nr = r + dir[unit[2]][0];
        int nc = c + dir[unit[2]][1];
        if (check(nr, nc) || board[nr][nc] == 2) {
            unit[2] = (unit[2] + 2) % 4;
            nr = r + dir[unit[2]][0];
            nc = c + dir[unit[2]][1];
            if (check(nr, nc) || board[nr][nc] == 2) return false;
        }
        if (board[nr][nc] == 1) map[nr][nc].append(sb.reverse());
        else map[nr][nc].append(sb);
        map[r][c] = new StringBuilder(map[r][c].substring(0, idx));
        for (int i = 0; i < sb.length(); i++) {
            int n = sb.charAt(i) - '0';
            units[n][0] = nr;
            units[n][1] = nc;
        }
        return map[unit[0]][unit[1]].length() >= 4;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}