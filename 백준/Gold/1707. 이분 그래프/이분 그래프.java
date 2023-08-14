import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] list;
    static int[] colors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int K = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < K; tc++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            list = new List[V + 1];
            for (int i = 0; i <= V; i++) {
                list[i] = new ArrayList<>();
            }
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                list[s].add(e);
                list[e].add(s);
            }
            boolean flag = false;
            colors = new int[V + 1];
            for (int i = 1; i <= V; i++) {
                if (colors[i] == 0) flag = checkGraph(i);
                if (!flag) break;
            }
            System.out.println(flag ? "YES" : "NO");
        }
    }

    private static boolean checkGraph(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        colors[start] = 1;
        while (!queue.isEmpty()) {
            int idx = queue.poll();
            for (int next : list[idx]) {
                if (colors[idx] == colors[next]) return false;
                if (colors[next] == 0) {
                    colors[next] = colors[idx] * -1;
                    queue.offer(next);
                }
            }
        }
        return true;
    }
}