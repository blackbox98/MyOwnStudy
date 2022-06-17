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
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<Integer> queue = new LinkedList<>();
		Queue<Integer> result = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		while(result.size() != N) {
			for (int i = 1; i <= K; i++) {
				if(i == K) {
					result.offer(queue.poll());
					break;
				}
				queue.offer(queue.poll());
			}
		}
		System.out.println(result.toString().replace('[', '<').replace(']', '>'));
	}
}