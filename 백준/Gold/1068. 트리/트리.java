import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int root = -1;
        List<Integer>[] list = new List[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
                continue;
            }
            list[parent].add(i);
        }
        int targetNode = Integer.parseInt(br.readLine());
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        boolean[] v = new boolean[N];
        v[targetNode] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (v[cur]) continue;
            v[cur] = true;
            if (list[cur].isEmpty()) {
                answer++;
                continue;
            }
            int cnt = 0;
            for (int next : list[cur]) {
                if (v[next]) continue;
                queue.offer(next);
                cnt++;
            }
            if (cnt == 0) answer++;
        }
        System.out.println(answer);
    }
}