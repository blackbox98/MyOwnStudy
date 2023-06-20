import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] blocks = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
        }
        int answer = 0;
        for (int i = 0; i < W - 1; i++) {
            int left = 0;
            int right = 0;
            for (int j = 0; j < i; j++) {
                left = Math.max(left, blocks[j]);
            }
            for (int j = i + 1; j < W; j++) {
                right = Math.max(right, blocks[j]);
            }
            int lowerHeight = Math.min(left, right);
            if (lowerHeight > blocks[i]) answer += lowerHeight - blocks[i];
        }
        System.out.println(answer);
    }
}