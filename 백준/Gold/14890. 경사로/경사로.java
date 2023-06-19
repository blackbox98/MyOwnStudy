import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static int[][] map;
    static final char ROW = 'R';
    static final char COLUMN = 'C';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            if (check(i, 0, ROW)) answer++;
            if (check(0, i, COLUMN)) answer++;
        }
        System.out.println(answer);
    }

    private static boolean check(int r, int c, char type) {
        boolean flag = true;
        int last = map[r][c];
        int target;
        int cnt = 1;
        L : for (int j = 1; j < N; j++) {
            if (type == 'R') target = map[r][j];
            else target = map[j][c];
            if (last != target) {
                if (Math.abs(last - target) > 1) return false;
                if (last > target) {
                    for (int k = 1; k < L; k++) {
                        if (type == 'R' && (j + k >= N || map[r][j + k] != target)) return false;
                        else if (type == 'C' && (j + k >= N || map[j + k][c] != target)) return false;
                    }
                    j += L - 1;
                    cnt = -1;
                } else {
                    if (cnt < L) return false;
                    cnt = 0;
                }
                last = target;
            }
            cnt++;
        }
        return flag;
    }
}