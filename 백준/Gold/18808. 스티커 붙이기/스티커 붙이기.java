import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0, N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        Queue<int[][]> stickers = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[R][C];
            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    sticker[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            stickers.offer(sticker);
        }
        L:
        while (!stickers.isEmpty()) {
            int[][] sticker = stickers.poll();
            for (int i = 0; i < 4; i++) {
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < M; c++) {
                        if (map[r][c] == 1 && sticker[0][0] == 1) continue;
                        if (check(sticker, r, c)) continue L;
                    }
                }
                int nr = sticker[0].length;
                int nc = sticker.length;
                sticker = turnSticker(sticker, nr, nc);
            }
        }
        System.out.println(answer);
    }

    private static boolean check(int[][] sticker, int sr, int sc) {
        int stkR = sticker.length;
        int stkC = sticker[0].length;
        if (sr + stkR - 1 >= N || sc + stkC - 1 >= M) return false;
        for (int r = 0; r < stkR; r++) {
            for (int c = 0; c < stkC; c++) {
                if (map[sr + r][sc + c] == 1 && sticker[r][c] == 1) return false;
            }
        }
        for (int r = 0; r < stkR; r++) {
            for (int c = 0; c < stkC; c++) {
                if (sticker[r][c] == 1) {
                    map[sr + r][sc + c] = 1;
                    answer++;
                }
            }
        }
        return true;
    }

    private static int[][] turnSticker(int[][] sticker, int R, int C) {
        int[][] tmp = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                tmp[r][c] = sticker[C - c - 1][r];
            }
        }
        return tmp;
    }
}