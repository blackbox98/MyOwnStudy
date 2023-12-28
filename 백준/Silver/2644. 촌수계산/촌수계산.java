import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer>[] list = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] target = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(e);
            list[e].add(s);
        }
        System.out.println(bfs(list, target, n));
    }

    private static int bfs(List<Integer>[] list, int[] target, int n) {
        int cnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{target[0], 0});
        boolean[] v = new boolean[n + 1];
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == target[1]) {
                cnt = cur[1];
                break;
            }
            if (v[cur[0]]) continue;
            v[cur[0]] = true;
            for (int next : list[cur[0]]) {
                if (v[next]) continue;
                queue.offer(new int[]{next, cur[1] + 1});
            }
        }
        return cnt == 0 ? -1 : cnt;
    }
}