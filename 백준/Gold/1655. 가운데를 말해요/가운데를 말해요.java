import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minPQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>();
        int num, min, max;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            num = Integer.parseInt(br.readLine());
            if (minPQ.isEmpty()) {
                minPQ.offer(num);
            } else if (maxPQ.isEmpty()) {
                if (minPQ.peek() > num) {
                    maxPQ.offer(minPQ.poll());
                    minPQ.offer(num);
                } else maxPQ.offer(num);
            } else {
                min = minPQ.peek();
                max = maxPQ.peek();
                if (min <= num && num <= max) {
                    if (minPQ.size() > maxPQ.size()) maxPQ.offer(num);
                    else minPQ.offer(num);
                } else if (num < min) {
                    if (minPQ.size() > maxPQ.size()) maxPQ.offer(minPQ.poll());
                    minPQ.offer(num);
                } else {
                    if (minPQ.size() <= maxPQ.size()) minPQ.offer(maxPQ.poll());
                    maxPQ.offer(num);
                }
            }
            sb.append(minPQ.peek()).append("\n");
        }
        System.out.println(sb);
    }
}