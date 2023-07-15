import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        int sizeA = n * (n + 1) / 2;
        int sizeB = m * (m + 1) / 2;
        long[] sumA = new long[sizeA];
        long[] sumB = new long[sizeB];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                sumA[idx++] = sum;
            }
        }
        idx = 0;
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += B[j];
                sumB[idx++] = sum;
            }
        }
        Arrays.sort(sumA);
        Arrays.sort(sumB);
        long answer = 0;
        int left = 0;
        int right = sizeB - 1;
        while (left < sizeA && right >= 0) {
            long result = sumA[left] + sumB[right];
            if (result == T) {
                long cntA = 0;
                long num = sumA[left];
                while (left < sizeA && num == sumA[left]) {
                    left++;
                    cntA++;
                }
                long cntB = 0;
                num = sumB[right];
                while (right >= 0 && num == sumB[right]) {
                    right--;
                    cntB++;
                }
                answer += cntA * cntB;
            } else if (result < T) left++;
            else right--;
        }
        System.out.println(answer);
    }
}