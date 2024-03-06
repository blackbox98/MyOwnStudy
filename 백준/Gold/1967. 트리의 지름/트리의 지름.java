import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<int[]>[] list;
    static Queue<int[]> queue;
    static boolean[] v;
    static int[] max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(0);
            return;
        }
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[s].add(new int[]{e, w});
            list[e].add(new int[]{s, w});
        }
        queue = new LinkedList<>();
        max = new int[2];
        bfs(1);
        bfs(max[0]);
        System.out.println(max[1]);
    }

    private static void bfs(int start) {
        queue.offer(new int[]{start, 0});
        v = new boolean[N + 1];
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (v[cur[0]]) continue;
            if (max[1] < cur[1]) max = cur.clone();
            v[cur[0]] = true;
            for (int[] next : list[cur[0]]) {
                if (v[next[0]]) continue;
                queue.offer(new int[]{next[0], cur[1] + next[1]});
            }
        }
    }
}