import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int answer = 0;
            int N = Integer.parseInt(br.readLine());
            int[][] grades = new int[N][2];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());
                grades[i] = new int[]{num1, num2};
            }
            Arrays.sort(grades, Comparator.comparingInt(o -> o[1]));
            int min = Integer.MAX_VALUE;
            for (int[] arr : grades) {
                if (arr[0] < min) {
                    answer++;
                    min = arr[0];
                } else continue;
                if (min == 1) break;
            }
            System.out.println(answer);
        }
    }
}