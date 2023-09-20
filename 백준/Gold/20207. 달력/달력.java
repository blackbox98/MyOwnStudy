import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int s, e;
        int[] plans = new int[366];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            for (int day = s; day <= e; day++) {
                plans[day]++;
            }
        }
        int answer = 0;
        int w = 0;
        int h = 0;
        for (int day = 1; day <= 365; day++) {
            if (plans[day] == 0) {
                if (w != 0 && h != 0) {
                    answer += w * h;
                    w = 0;
                    h = 0;
                }
                continue;
            }
            w++;
            h = Math.max(h, plans[day]);
        }
        if (w != 0 && h != 0) answer += w * h;
        System.out.println(answer);
    }
}