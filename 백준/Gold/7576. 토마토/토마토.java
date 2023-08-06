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
        int[][] box = new int[N][M];
        boolean[][] v = new boolean[N][M];
        int total = 0;
        Queue<int[]> ripeTomato = new LinkedList<>();
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                box[r][c] = Integer.parseInt(st.nextToken());
                if (box[r][c] == 0) {
                    total++;
                    continue;
                }
                if (box[r][c] == 1) ripeTomato.offer(new int[]{r, c});
                v[r][c] = true;
            }
        }
        int answer = 0;
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
        Queue<int[]> pq;
        while (total > 0) {
            pq = new LinkedList<>();
            while (!ripeTomato.isEmpty()) {
                int[] point = ripeTomato.poll();
                for (int[] d : dir) {
                    int nr = point[0] + d[0];
                    int nc = point[1] + d[1];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nr][nc] && box[nr][nc] == 0) {
                        pq.offer(new int[]{nr, nc});
                        box[nr][nc] = 1;
                        v[nr][nc] = true;
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