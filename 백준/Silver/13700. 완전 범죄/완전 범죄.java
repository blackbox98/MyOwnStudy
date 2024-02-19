import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int F = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        if (K > 0) st = new StringTokenizer(br.readLine());
        boolean[] v = new boolean[N + 1];
        for (int i = 0; i < K; i++) {
            v[Integer.parseInt(st.nextToken())] = true;
        }
        System.out.println(bfs(N, S, D, F, B, v));
    }

    private static String bfs(int N, int S, int D, int F, int B, boolean[] v) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{S, 0});
        v[S] = true;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[0] == D) return cur[1] + "";
            if (cur[0] - B >= 1 && !v[cur[0] - B]) {
                pq.offer(new int[]{cur[0] - B, cur[1] + 1});
                v[cur[0] - B] = true;
            }
            if (cur[0] + F <= N && !v[cur[0] + F]) {
                pq.offer(new int[]{cur[0] + F, cur[1] + 1});
                v[cur[0] + F] = true;
            }
        }
        return "BUG FOUND";
    }
}