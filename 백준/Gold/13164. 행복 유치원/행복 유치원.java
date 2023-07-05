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
        int K = Integer.parseInt(st.nextToken());
        int[] child = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            child[i] = Integer.parseInt(st.nextToken());
        }
        int[] dif = new int[N - 1];
        for (int i = 0; i < dif.length; i++) {
            dif[i] = child[i + 1] - child[i];
        }
        Arrays.sort(dif);
        int answer = 0;
        for (int i = 0; i < N - K; i++) {
            answer += dif[i];
        }
        System.out.println(answer);
    }
}