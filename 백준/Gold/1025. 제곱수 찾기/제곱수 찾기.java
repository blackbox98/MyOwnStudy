import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = -1;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        char[] tmp;
        for (int r = 0; r < N; r++) {
            tmp = br.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                map[r][c] = tmp[c] - '0';
                if (isSquare(map[r][c])) answer = Math.max(answer, map[r][c]);
            }
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                for (int dr = -N; dr < N; dr++) {
                    for (int dc = -M; dc < M; dc++) {
                        if (dr == 0 && dc == 0) continue;
                        int result = 0;
                        int nr = r;
                        int nc = c;
                        while (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                            result = result * 10 + map[nr][nc];
                            if (isSquare(result)) answer = Math.max(answer, result);
                            nr += dr;
                            nc += dc;
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static boolean isSquare(int n) {
        double d = Math.sqrt(n);
        return (int) d - d == 0;
    }
}