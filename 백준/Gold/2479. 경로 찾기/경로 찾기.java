import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, start, end;
    static List<Integer>[] list;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        String[] codes = new String[N + 1];
        list = new List[N + 1];
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            codes[i] = br.readLine();
            list[i] = new ArrayList<>();
        }
        for (int s = 1; s < N; s++) {
            for (int e = s + 1; e <= N; e++) {
                if (isHemingPath(codes[s], codes[e])) {
                    list[s].add(e);
                    list[e].add(s);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        System.out.println(bfs());
    }

    private static boolean isHemingPath(String s1, String s2) {
        int cnt = 0;
        for (int i = 0; i < K; i++) {
            if (s1.charAt(i) != s2.charAt(i)) cnt++;
            if (cnt > 1) break;
        }
        return cnt == 1;
    }

    private static String bfs() {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        boolean[] v = new boolean[N + 1];
        v[start] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == end) {
                Stack<Integer> stack = new Stack<>();
                while (cur > 0) {
                    stack.push(cur);
                    cur = parent[cur];
                }
                while (!stack.isEmpty()) sb.append(stack.pop()).append(" ");
                break;
            }
            for (int next : list[cur]) {
                if (v[next]) continue;
                queue.offer(next);
                v[next] = true;
                parent[next] = cur;
            }
        }
        return sb.length() == 0 ? -1 + "" : sb.toString();
    }
}