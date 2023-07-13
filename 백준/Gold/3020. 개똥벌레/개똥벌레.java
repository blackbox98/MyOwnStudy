import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] stalagmite = new int[H + 1];
        int[] stalactite = new int[H + 1];
        for (int i = 0; i < N / 2; i++) {
            stalagmite[Integer.parseInt(br.readLine())]++;
            stalactite[H - Integer.parseInt(br.readLine())]++;
        }
        for (int i = 1; i <= H; i++) {
            stalagmite[H - i] += stalagmite[H - i + 1];
            stalactite[i] += stalactite[i - 1];
        }
        int total;
        int[] answer = new int[]{N + 1, 0};
        for (int i = 0; i < H; i++) {
            total = stalagmite[i + 1] + stalactite[i];
            if (answer[0] > total) {
                answer[0] = total;
                answer[1] = 1;
            } else if (answer[0] == total) {
                answer[1]++;
            }
        }
        System.out.printf("%d %d\n", answer[0], answer[1]);
    }
}