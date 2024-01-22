import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        System.out.println(bfs(A, B, N, M));
    }

    private static int bfs(int A, int B, int N, int M) {
        int cnt = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{N, 0});
        Set<Integer> set = new HashSet<>();
        set.add(N);
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[0] == M) {
                cnt = cur[1];
                break;
            }
            if (cur[0] - 1 >= 0 && !set.contains(cur[0] - 1)) {
                set.add(cur[0] - 1);
                pq.offer(new int[]{cur[0] - 1, cur[1] + 1});
            }
            if (cur[0] + 1 <= 100000 && !set.contains(cur[0] + 1)) {
                set.add(cur[0] + 1);
                pq.offer(new int[]{cur[0] + 1, cur[1] + 1});
            }
            if (cur[0] - A >= 0 && !set.contains(cur[0] - A)) {
                set.add(cur[0] - A);
                pq.offer(new int[]{cur[0] - A, cur[1] + 1});
            }
            if (cur[0] + A <= 100000 && !set.contains(cur[0] + A)) {
                set.add(cur[0] + A);
                pq.offer(new int[]{cur[0] + A, cur[1] + 1});
            }
            if (cur[0] - B >= 0 && !set.contains(cur[0] - B)) {
                set.add(cur[0] - B);
                pq.offer(new int[]{cur[0] - B, cur[1] + 1});
            }
            if (cur[0] + B <= 100000 && !set.contains(cur[0] + B)) {
                set.add(cur[0] + B);
                pq.offer(new int[]{cur[0] + B, cur[1] + 1});
            }
            if (cur[0] * A <= 100000 && !set.contains(cur[0] * A)) {
                set.add(cur[0] * A);
                pq.offer(new int[]{cur[0] * A, cur[1] + 1});
            }
            if (cur[0] * B <= 100000 && !set.contains(cur[0] * B)) {
                set.add(cur[0] * B);
                pq.offer(new int[]{cur[0] * B, cur[1] + 1});
            }
        }
        return cnt;
    }
}