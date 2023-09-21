import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int targetR = -1;
        char[] start = new char[k];
        for (int i = 0; i < k; i++) {
            start[i] = (char) ('A' + i);
        }
        char[] end = br.readLine().toCharArray();
        char[][] map = new char[n][k];
        for (int r = 0; r < n; r++) {
            map[r] = br.readLine().toCharArray();
            if (map[r][0] == '?') targetR = r;
        }
        char tmp;
        for (int r = 0; r < targetR; r++) {
            for (int c = 0; c < k - 1; c++) {
                if (map[r][c] == '*') continue;
                tmp = start[c];
                start[c] = start[c + 1];
                start[c + 1] = tmp;
            }
        }
        for (int r = n - 1; r > targetR; r--) {
            for (int c = 0; c < k - 1; c++) {
                if (map[r][c] == '*') continue;
                tmp = end[c];
                end[c] = end[c + 1];
                end[c + 1] = tmp;
            }
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < k - 1; i++) {
            if (start[i] == end[i]) answer.append('*');
            else if (start[i] == end[i + 1]) {
                answer.append('-');
                tmp = start[i];
                start[i] = start[i + 1];
                start[i + 1] = tmp;
            } else {
                answer = new StringBuilder();
                for (int j = 0; j < k - 1; j++) {
                    answer.append('x');
                }
                break;
            }
        }
        System.out.println(answer);
    }
}