import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int answer = 0;
        if (N <= K) {
            System.out.println(answer);
            return;
        }
        Set<Integer> set = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        List<Integer> diff = new ArrayList<>();
        for (int i = 0; i < list.size() - 1; i++) {
            diff.add(list.get(i + 1) - list.get(i));
        }
        diff.sort(Collections.reverseOrder());
        for (int i = diff.size() - 1; i >= K - 1; i--) {
            answer += diff.get(i);
        }
        System.out.println(answer);
    }
}