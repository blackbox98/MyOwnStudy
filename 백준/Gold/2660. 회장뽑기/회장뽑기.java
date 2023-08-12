import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, min;
    static List<Integer>[] listArr;
    static int[] scores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        listArr = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            listArr[i] = new ArrayList<>();
        }
        String s;
        while ((s = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (start == -1 && end == -1) break;
            listArr[start].add(end);
            listArr[end].add(start);
        }
        scores = new int[N + 1];
        min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            getScore(i);
        }
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (scores[i] == min) {
                cnt++;
                sb.append(i).append(" ");
            }
        }
        System.out.printf("%d %d\n", min, cnt);
        System.out.println(sb);
    }

    private static void getScore(int i) {
        int score = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, 0});
        boolean[] v = new boolean[N + 1];
        v[i] = true;
        while (!queue.isEmpty()) {
            int[] member = queue.poll();
            int currentIdx = member[0];
            int currentScore = member[1] + 1;
            for (int idx : listArr[currentIdx]) {
                if (v[idx]) continue;
                v[idx] = true;
                queue.offer(new int[]{idx, currentScore});
                score = Math.max(score, currentScore);
            }
        }
        scores[i] = score;
        min = Math.min(min, score);
    }
}