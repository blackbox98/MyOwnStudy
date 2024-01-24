import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] list;
    static int[] result;

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
        result = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            result[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(result[0] == 1 ? bfs() : 0);
    }

    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        boolean[] v = new boolean[N + 1];
        v[1] = true;
        int idx = 1;
        while (!queue.isEmpty()) {
            int x = queue.poll();
            int size = 0;
            for (int y : list[x]) {
                if (v[y]) continue;
                v[y] = true;
                size++;
            }
            for (int i = idx; i < idx + size; i++) {
                if (!v[result[i]]) return 0;
                else queue.offer(result[i]);
            }
            idx += size;
        }
        return 1;
    }
}