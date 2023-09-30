import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static Map<Cross, Set<int[]>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int r = 0; r < N; r++) {
            board[r] = br.readLine().toCharArray();
        }
        map = new HashMap<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] == '.') continue;
                getCrossSize(r, c);
            }
        }
        int answer = 0;
        for (Cross first : map.keySet()) {
            for (Cross second : map.keySet()) {
                if (!first.equals(second) && isPossible(first, second)) {
                    answer = Math.max(answer, (first.size * 4 + 1) * (second.size * 4 + 1));
                }
            }
        }
        System.out.println(answer);
    }

    private static void getCrossSize(int r, int c) {
        int size = 1;
        int nr, nc;
        L:
        while (true) {
            for (int[] d : dir) {
                nr = r + d[0] * size;
                nc = c + d[1] * size;
                if (check(nr, nc)) break L;
            }
            size++;
        }
        for (int i = 0; i < size; i++) {
            Set<int[]> set = new HashSet<>();
            set.add(new int[]{r, c});
            for (int j = 1; j <= i; j++) {
                set.add(new int[]{r + j, c});
                set.add(new int[]{r - j, c});
                set.add(new int[]{r, c + j});
                set.add(new int[]{r, c - j});
            }
            map.put(new Cross(r, c, i), set);
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || board[r][c] == '.';
    }

    private static boolean isPossible(Cross C1, Cross C2) {
        for (int[] p1 : map.get(C1)) {
            for (int[] p2 : map.get(C2)) {
                if (p1[0] == p2[0] && p1[1] == p2[1]) return false;
            }
        }
        return true;
    }

    private static class Cross {
        int r;
        int c;
        int size;

        private Cross(int r, int c, int size) {
            this.r = r;
            this.c = c;
            this.size = size;
        }

        @Override
        public boolean equals(Object obj) {
            Cross o = (Cross) obj;
            return this.r == o.r && this.c == o.c && this.size == o.size;
        }

        @Override
        public int hashCode() {
            return r * 16 + c;
        }
    }
}