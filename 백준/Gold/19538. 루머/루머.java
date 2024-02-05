import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] times, neighbors;
    static List<Integer>[] list;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        times = new int[N + 1];
        neighbors = new int[N + 1];
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            times[i] = -1;
            list[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int s = 1; s <= N; s++) {
            st = new StringTokenizer(br.readLine());
            while (true) {
                int e = Integer.parseInt(st.nextToken());
                if (e == 0) break;
                list[s].add(e);
            }
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        queue = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            int f = Integer.parseInt(st.nextToken());
            queue.offer(f);
            times[f] = 0;
        }
        bfs();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(times[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : list[cur]) {
                neighbors[next]++;
                if (times[next] == -1 && (list[next].size() + 1) / 2 <= neighbors[next]) {
                    queue.offer(next);
                    times[next] = times[cur] + 1;
                }
            }
        }
    }
}