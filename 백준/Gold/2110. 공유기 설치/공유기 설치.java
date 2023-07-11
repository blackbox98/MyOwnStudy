import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        house = new int[N];
        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);
        int answer = 0;
        int left = 1;
        int right = house[N - 1] - house[0];
        while (left <= right) {
            int mid = (left + right) / 2;
            if (installRouter(mid) < C) {
                right = mid - 1;
            } else {
                left = mid + 1;
                answer = mid;
            }
        }
        System.out.println(answer);
    }

    private static int installRouter(int interval) {
        int cnt = 1;
        int last = house[0];
        for (int i = 1; i < N; i++) {
            if (house[i] - last >= interval) {
                cnt++;
                last = house[i];
            }
        }
        return cnt;
    }
}