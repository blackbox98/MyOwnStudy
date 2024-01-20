import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] edge;
    static int[] edgeCnt, dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        edge = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            edge[i] = new ArrayList<>();
        }
        StringTokenizer st;
        edgeCnt = new int[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            edge[s].add(e);
            edge[e].add(s);
            edgeCnt[s]++;
            edgeCnt[e]++;
        }
        for (int i = 1; i <= N; i++) {
            if (edge[i].size() == 1) checkNotCycle(i);
        }
        dist = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (edge[i].size() <= 2 || edgeCnt[i] != 2) continue;
            for (int next : edge[i]) {
                if (edgeCnt[next] == 0) bfs(next);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(dist[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static void checkNotCycle(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            edgeCnt[cur]--;
            for (int next : edge[cur]) {
                if (edgeCnt[next] > 0) {
                    edgeCnt[next]--;
                    if (edgeCnt[next] == 1) queue.offer(next);
                }
            }
        }
    }

    private static void bfs(int start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 1});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            dist[cur[0]] = cur[1];
            for (int next : edge[cur[0]]) {
                if (dist[next] == 0 && edgeCnt[next] == 0) queue.offer(new int[]{next, dist[cur[0]] + 1});
            }
        }
    }
}