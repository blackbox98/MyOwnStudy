import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, answer;
    static List<Integer>[] list;
    static Queue<Integer> queue;
    static boolean[] v;

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
        st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        if (first != 1) {
            System.out.println(0);
            return;
        }
        queue = new LinkedList<>();
        for (int i = 1; i < N; i++) {
            queue.offer(Integer.parseInt(st.nextToken()));
        }
        answer = 1;
        v = new boolean[N + 1];
        dfs(1);
        System.out.println(answer);
    }

    private static void dfs(int cur) {
        if (v[cur]) return;
        v[cur] = true;
        Set<Integer> set = new HashSet<>();
        for (int next : list[cur]) {
            if (v[next]) continue;
            set.add(next);
        }
        if (set.size() == 0) return;
        int next = queue.poll();
        if (set.contains(next)) dfs(next);
        else answer = 0;
    }
}