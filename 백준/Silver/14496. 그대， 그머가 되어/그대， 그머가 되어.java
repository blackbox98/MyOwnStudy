import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        if (a == b) {
            System.out.println(0);
            return;
        }
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Integer>[] list = new List[N + 1];
        Set<Integer>[] set = new Set[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
            set[i] = new HashSet<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(e);
            list[e].add(s);
        }
        int answer = -1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{a, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == b) {
                answer = cur[1];
                break;
            }
            for (int next : list[cur[0]]) {
                if (set[cur[0]].contains(next)) continue;
                set[cur[0]].add(next);
                queue.offer(new int[]{next, cur[1] + 1});
            }
        }
        System.out.println(answer);
    }
}