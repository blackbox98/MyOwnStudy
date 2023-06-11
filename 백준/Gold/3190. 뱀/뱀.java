import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        boolean[][] v = new boolean[N][N];
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int appleR = Integer.parseInt(st.nextToken()) - 1;
            int appleC = Integer.parseInt(st.nextToken()) - 1;
            map[appleR][appleC] = 1;
        }
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
        int d = 0;
        int answer = 0;
        int r = 0;
        int c = 0;
        Queue<int[]> snake = new LinkedList<>();
        snake.offer(new int[] {r, c});
        v[r][c] = true;
        int L = Integer.parseInt(br.readLine());
        boolean finish = false;
        L : for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            for (int move = answer; move < sec; move++) {
                answer++;
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc]) {
                    snake.offer(new int[] {nr, nc});
                    v[nr][nc] = true;
                    if (map[nr][nc] == 0) {
                        int[] remove = snake.poll();
                        v[remove[0]][remove[1]] = false;
                    } else map[nr][nc] = 0;
                    r = nr;
                    c = nc;
                } else {
                    finish = true;
                    break L;
                }
            }
            if (C == 'L') d = d - 1 == -1 ? 3 : d - 1;
            else if (C == 'D') d = (d + 1) % 4;
        }
        if (!finish) {
            while (true) {
                answer++;
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || v[nr][nc]) break;
                r = nr;
                c = nc;
            }
        }
        System.out.println(answer);
    }
}