import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] distance;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        distance = new int[N + 1][N + 1];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            distance[start][end] = dist;
            distance[end][start] = dist;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            System.out.println(checkDist(start, end));
        }
    }

    private static int checkDist(int start, int end) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});
        v = new boolean[N + 1];
        v[start] = true;
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int i = 1; i <= N; i++) {
                if (v[i] || distance[node[0]][i] == 0) continue;
                int nd = node[1] + distance[node[0]][i];
                if (i == end) return nd;
                queue.offer(new int[]{i, nd});
                v[i] = true;
            }
        }
        return 0;
    }
}