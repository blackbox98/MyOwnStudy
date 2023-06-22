import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, K, S, X, Y;
    static PriorityQueue<Virus> pq = new PriorityQueue<>();
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                pq.offer(new Virus(r, c, map[r][c], 0));
            }
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;
        if (S > 0) bfs();
        System.out.println(map[X][Y]);
    }

    private static void bfs() {
        while (!pq.isEmpty()) {
            Virus virus = pq.poll();
            for (int[] d : dir) {
                int nr = virus.r + d[0];
                int nc = virus.c + d[1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 0 && virus.sec < S) {
                    map[nr][nc] = virus.kind;
                    pq.offer(new Virus(nr, nc, virus.kind, virus.sec + 1));
                }
            }
            if (map[X][Y] > 0) break;
        }
    }

    private static class Virus implements Comparable<Virus> {
        int r;
        int c;
        int kind;
        int sec;

        private Virus(int r, int c, int kind, int sec) {
            this.r = r;
            this.c = c;
            this.kind = kind;
            this.sec = sec;
        }

        @Override
        public int compareTo(Virus o) {
            return this.sec == o.sec ? Integer.compare(this.kind, o.kind) : Integer.compare(this.sec, o.sec);
        }
    }
}