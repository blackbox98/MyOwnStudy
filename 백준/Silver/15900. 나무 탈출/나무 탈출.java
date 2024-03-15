import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(e);
            list[e].add(s);
        }
        System.out.println(bfs(1));
    }

    private static String bfs(int root) {
        int turn = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{root, 0});
        boolean[] v = new boolean[N + 1];
        v[root] = true;
        int cnt;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            cnt = 0;
            for (int next : list[cur[0]]) {
                if (v[next]) continue;
                queue.offer(new int[]{next, cur[1] + 1});
                v[next] = true;
                cnt++;
            }
            if (cnt == 0) turn += cur[1];
        }
        return turn % 2 == 0 ? "No" : "Yes";
    }
}