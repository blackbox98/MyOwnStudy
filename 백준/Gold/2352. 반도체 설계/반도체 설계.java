import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int answer = 1;
        int idx = 0;
        int target, left, right, mid;
        int[] LIS = new int[n + 1];
        LIS[answer] = A[idx];
        while (++idx < n) {
            target = A[idx];
            if (LIS[answer] < target) LIS[++answer] = target;
            else {
                left = 1;
                right = answer;
                while (left < right) {
                    mid = (left + right) / 2;
                    if (LIS[mid] < target) left = mid + 1;
                    else right = mid;
                }
                LIS[left] = target;
            }
        }
        System.out.println(answer);
    }
}