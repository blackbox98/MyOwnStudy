import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] computers;
    static List<Integer>[] list;
    static Queue<Integer> queue;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            list[s].add(e);
        }
        computers = new int[N + 1];
        queue = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            v = new boolean[N + 1];
            computers[i] = bfs(i);
            max = Math.max(max, computers[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (computers[i] == max) sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    private static int bfs(int start) {
        int cnt = 0;
        queue.add(start);
        v[start] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : list[cur]) {
                if (!v[next]) {
                    queue.add(next);
                    v[next] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}