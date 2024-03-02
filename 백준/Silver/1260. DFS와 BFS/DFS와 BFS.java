import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] list;
    static boolean[] v;
    static StringBuilder sb1, sb2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        list = new List[N + 1];
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
        v = new boolean[N + 1];
        sb1 = new StringBuilder();
        dfs(V);
        sb2 = new StringBuilder();
        bfs(V);
        System.out.println(sb1);
        System.out.println(sb2);
    }

    private static void dfs(int cur) {
        sb1.append(cur).append(" ");
        v[cur] = true;
        for (int next : list[cur]) {
            if (v[next]) continue;
            dfs(next);
        }
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        v = new boolean[N + 1];
        v[start] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb2.append(cur).append(" ");
            for (int next : list[cur]) {
                if (v[next]) continue;
                queue.offer(next);
                v[next] = true;
            }
        }
    }
}