import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        PriorityQueue<Virus> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.kind));
        int[][] map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                pq.offer(new Virus(r, c, map[r][c]));
            }
        }
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;
        int Y = Integer.parseInt(st.nextToken()) - 1;
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
        while (S-- > 0) {
            Queue<Virus> addQueue = new LinkedList<>();
            while (!pq.isEmpty()) {
                Virus virus = pq.poll();
                int r = virus.r;
                int c = virus.c;
                int kind = virus.kind;
                boolean infection = false;
                for (int[] d : dir) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 0) {
                        infection = true;
                        map[nr][nc] = kind;
                        addQueue.offer(new Virus(nr, nc, kind));
                    }
                }
                if (infection) addQueue.offer(virus);
            }
            for (Virus virus : addQueue) {
                pq.offer(virus);
            }
        }
        System.out.println(map[X][Y]);
    }

    private static class Virus {
        int r;
        int c;
        int kind;

        private Virus(int r, int c, int kind) {
            this.r = r;
            this.c = c;
            this.kind = kind;
        }
    }
}