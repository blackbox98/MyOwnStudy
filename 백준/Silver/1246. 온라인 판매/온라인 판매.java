import java.io.BufferedReader;
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
        int[] P = new int[M];
        for (int i = 0; i < M; i++) {
            P[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(P);
        int price = 0;
        int profit = 0;
        for (int i = 0; i < M; i++) {
            int client;
            if (N >= M - i) client = M - i;
            else client = N;
            if (profit < P[i] * client) {
                profit = P[i] * client;
                price = P[i];
            }
        }
        System.out.println(price + " " + profit);
    }
}