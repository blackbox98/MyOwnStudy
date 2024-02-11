import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static List<Integer>[] list;
    static Queue<Integer> queue;
    static boolean[] v;
    static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            list = new List[n + 1];
            for (int i = 1; i <= n; i++) {
                list[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                list[s].add(e);
                list[e].add(s);
            }
            sb.append(bfs()).append("\n");
        }
        System.out.println(sb);
    }

    private static String bfs() {
        queue = new LinkedList<>();
        v = new boolean[n + 1];
        check = new int[n + 1];
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 1; i <= n; i++) {
                if (!v[i]) {
                    queue.offer(i);
                    v[i] = true;
                    check[i] = 1;
                    flag = true;
                    break;
                }
            }
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                int nNum = (check[cur] + 1) % 2;
                for (int next : list[cur]) {
                    if (v[next]) {
                        if (check[cur] == check[next]) return "impossible";
                        continue;
                    }
                    queue.offer(next);
                    v[next] = true;
                    check[next] = nNum;
                }
            }
        }
        return "possible";
    }
}