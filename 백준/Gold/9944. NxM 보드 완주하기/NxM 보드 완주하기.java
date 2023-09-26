import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static final int limit = 1000001;
    static int N, M, answer;
    static char[][] map;
    static boolean[][] v;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String s = " ";
        StringBuilder sb = new StringBuilder();
        int tc = 1;
        while ((s = br.readLine()) != null) {
            st = new StringTokenizer(s);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][M];
            v = new boolean[N][M];
            int left = 0;
            char[] tmp;
            for (int r = 0; r < N; r++) {
                tmp = br.readLine().toCharArray();
                for (int c = 0; c < M; c++) {
                    map[r][c] = tmp[c];
                    if (map[r][c] == '.') left++;
                }
            }
            answer = limit;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (map[r][c] == '*') continue;
                    v[r][c] = true;
                    dfs(r, c, left - 1, 0);
                    v[r][c] = false;
                }
            }
            sb.append("Case ").append(tc++).append(": ").append(answer == limit ? -1 : answer).append("\n");
        }
        System.out.println(sb);
    }
    
    private static void dfs(int sr, int sc, int left, int move) {
        if (left == 0) {
            answer = Math.min(answer, move);
            return;
        }
        if (move == limit) return;
        int nr, nc, k;
        for (int[] d : dir) {
            k = 1;
            while (true) {
                nr = sr + d[0] * k;
                nc = sc + d[1] * k;
                if (check(nr, nc)) {
                    if (k > 1) {
                        for (int i = 1; i < k; i++) {
                            v[sr + d[0] * i][sc + d[1] * i] = true;
                        }
                        dfs(nr - d[0], nc - d[1], left - (k - 1), move + 1);
                        for (int i = 1; i < k; i++) {
                            v[sr + d[0] * i][sc + d[1] * i] = false;
                        }
                    }
                    break;
                }
                k++;
            }
        }
    }
    
    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || v[r][c] || map[r][c] == '*';
    }
}