import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        int[] tmp = new int[N];
        int big = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            if (A[i] >= big) big = A[i];
        }
        int idx = 0;
        for (int i = 0; i <= big; i++) {
            for (int j = 0; j < N; j++) {
                if (A[j] == i) tmp[j] = idx++;
            }
        }
        for (int n : tmp) {
            System.out.printf("%d ", n);
        }
    }
}