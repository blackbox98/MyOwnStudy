import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<int[]> list = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            list.add(new int[]{d, score});
        }
        list.sort((o1, o2) -> {
            if (o1[0] == o2[0]) return Integer.compare(o2[1], o1[1]);
            else return Integer.compare(o2[0], o1[0]);
        });
        int answer = 0;
        int maxDay = list.get(0)[0];
        for (int day = maxDay; day > 0; day--) {
            int idx = -1;
            int score = 0;
            for (int i = 0; i < list.size(); i++) {
                int[] task = list.get(i);
                if (day > task[0]) break;
                if (score >= task[1]) continue;
                idx = i;
                score = task[1];
            }
            if (idx == -1) continue;
            answer += score;
            list.remove(idx);
        }
        System.out.println(answer);
    }
}