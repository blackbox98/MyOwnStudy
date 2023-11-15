import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map, adj;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int num = 1;
        String s;
        while ((s = br.readLine()) != null && !s.equals("0")) {
            N = Integer.parseInt(s);
            map = new int[N][N];
            adj = new int[N][N];
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    adj[r][c] = Integer.MAX_VALUE;
                }
            }
            sb.append("Problem " + num++ + ": " + bfs() + "\n");
        }
        System.out.println(sb);
    }
    
    private static int bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, map[0][0]));
        adj[0][0] = map[0][0];
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.r == N - 1 && node.c == N - 1) continue;
            for (int[] d : dir) {
                int nr = node.r + d[0];
                int nc = node.c + d[1];
                if (check(nr, nc)) continue;
                if (adj[nr][nc] > node.rupee + map[nr][nc]) {
                    adj[nr][nc] = node.rupee + map[nr][nc];
                    pq.offer(new Node(nr, nc, adj[nr][nc]));
                }
            }
        }
        return adj[N - 1][N - 1];
    }
    
    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
    
    private static class Node implements Comparable<Node> {
        int r;
        int c;
        int rupee;
        
        private Node(int r, int c, int rupee) {
            this.r = r;
            this.c = c;
            this.rupee = rupee;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.rupee - o.rupee;
        }
    }
}