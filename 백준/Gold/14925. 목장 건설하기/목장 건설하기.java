import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        char[][] map = new char[M][N];
        for (int r = 0; r < M; r++) {
            String[] tmp = br.readLine().split(" ");
            for (int c = 0; c < N; c++) {
                map[r][c] = tmp[c].charAt(0);
            }
        }
        int answer = 0;
        int[][] square = new int[M + 1][N + 1];
        for (int r = 1; r <= M; r++) {
            for (int c = 1; c <= N; c++) {
                if (map[r - 1][c - 1] != '0') continue;
                square[r][c] = Math.min(Math.min(square[r - 1][c], square[r][c - 1]), square[r - 1][c - 1]) + 1;
                answer = Math.max(answer, square[r][c]);
            }
        }
        System.out.println(answer);
    }
}