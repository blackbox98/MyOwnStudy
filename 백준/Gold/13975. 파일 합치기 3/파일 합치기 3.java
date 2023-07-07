import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int K;
        PriorityQueue<Long> pq;
        StringTokenizer st;
        for (int tc = 0; tc < T; tc++) {
            K = Integer.parseInt(br.readLine());
            pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                pq.offer(Long.parseLong(st.nextToken()));
            }
            long answer = 0;
            while (pq.size() > 1) {
                long file1 = pq.poll();
                long file2 = pq.poll();
                answer += file1 + file2;
                pq.offer(file1 + file2);
            }
            System.out.println(answer);
        }
    }
}