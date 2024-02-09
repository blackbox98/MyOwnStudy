import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            int point = Integer.parseInt(st.nextToken());
            queue.offer(new int[]{point, 1});
            set.add(point);
        }
        long total = 0;
        while (K > 0 && !queue.isEmpty()) {
            int[] cur = queue.poll();
            if (!set.contains(cur[0] - 1)) {
                queue.offer(new int[]{cur[0] - 1, cur[1] + 1});
                set.add(cur[0] - 1);
                total += cur[1];
                K--;
            }
            if (K == 0) break;
            if (!set.contains(cur[0] + 1)) {
                queue.offer(new int[]{cur[0] + 1, cur[1] + 1});
                set.add(cur[0] + 1);
                total += cur[1];
                K--;
            }
        }
        System.out.println(total);
    }
}