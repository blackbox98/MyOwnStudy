import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] place = new int[N + 2];
        place[0] = 0;
        place[N + 1] = L;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            place[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(place);
        int left = 1;
        int right = L;
        int mid, sum;
        while (left < right) {
            mid = (left + right) / 2;
            sum = 0;
            for (int i = 1; i <= N + 1; i++) {
                sum += (place[i] - place[i - 1] - 1) / mid;
            }
            if (sum > M) left = mid + 1;
            else right = mid;
        }
        System.out.println(left);
    }
}