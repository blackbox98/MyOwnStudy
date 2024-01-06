import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        List<Integer>[] list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(e);
            list[e].add(s);
        }
        for (int i = 1; i <= N; i++) {
            Collections.sort(list[i]);
        }
        System.out.println(bfs(list, N, R));
    }

    private static StringBuilder bfs(List<Integer>[] list, int N, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        boolean[] v = new boolean[N + 1];
        v[start] = true;
        int[] orders = new int[N + 1];
        int order = 1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            orders[cur] = order++;
            for (int next : list[cur]) {
                if (v[next]) continue;
                queue.offer(next);
                v[next] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(orders[i]).append("\n");
        }
        return sb;
    }
}