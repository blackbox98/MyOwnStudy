import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        long answer = -1;
        Queue<long[]> pq = new LinkedList<>();
        pq.offer(new long[]{A, 1});
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            if (cur[0] > B) continue;
            if (cur[0] == B) {
                answer = cur[1];
                break;
            }
            pq.offer(new long[]{cur[0] * 2, cur[1] + 1});
            pq.offer(new long[]{cur[0] * 10 + 1, cur[1] + 1});
        }
        System.out.println(answer);
    }
}