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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[][][] box = new int[H][N][M];
        boolean[][][] v = new boolean[H][N][M];
        int total = 0;
        Queue<int[]> ripeTomato = new LinkedList<>();
        for (int h = 0; h < H; h++) {
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    box[h][r][c] = Integer.parseInt(st.nextToken());
                    if (box[h][r][c] == 0) {
                        total++;
                        continue;
                    }
                    if (box[h][r][c] == 1) ripeTomato.offer(new int[]{h, r, c});
                    v[h][r][c] = true;
                }
            }
        }
        int answer = 0;
        int[][] dir = {{0, 0, 1}, {0, 1, 0}, {0, 0, -1}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}}; // 우 하 좌 상 앞 뒤
        while (total > 0) {
            Queue<int[]> pq = new LinkedList<>();
            while (!ripeTomato.isEmpty()) {
                int[] point = ripeTomato.poll();
                for (int[] d : dir) {
                    int nh = point[0] + d[0];
                    int nr = point[1] + d[1];
                    int nc = point[2] + d[2];
                    if (nh >= 0 && nh < H && nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nh][nr][nc] && box[nh][nr][nc] == 0) {
                        pq.offer(new int[]{nh, nr, nc});
                        box[nh][nr][nc] = 1;
                        v[nh][nr][nc] = true;
                        total--;
                    }
                }
            }
            if (pq.isEmpty() && total != 0) {
                answer = -1;
                break;
            }
            ripeTomato = pq;
            answer++;
        }
        System.out.println(answer);
    }
}