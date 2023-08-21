import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] video;
    static StringBuilder answer;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        video = new int[N][N];
        char[] tmp;
        int last = -1;
        boolean flag = true;
        for (int r = 0; r < N; r++) {
            tmp = br.readLine().toCharArray();
            for (int c = 0; c < N; c++) {
                video[r][c] = tmp[c] - '0';
                if (flag) {
                    if (last == -1) last = video[r][c];
                    else {
                        if (last != video[r][c]) flag = false;
                    }
                }
            }
        }
        if (flag) System.out.println(last);
        else {
            answer = new StringBuilder();
            setArea(0, 0, N / 2);
            System.out.println(answer);
        }
    }
    
    private static void setArea(int sr, int sc, int size) {
        if (size < 1) return;
        answer.append("(");
        for (int nr = sr; nr < sr + size * 2; nr += size) {
            for (int nc = sc; nc < sc + size * 2; nc += size) {
                if (checkArea(nr, nc, video[nr][nc], size)) answer.append(video[nr][nc]);
                else setArea(nr, nc, size / 2);
            }
        }
        answer.append(")");
    }
    
    private static boolean checkArea(int sr, int sc, int last, int size) {
        for (int r = sr; r < sr + size; r++) {
            for (int c = sc; c < sc + size; c++) {
                if (last != video[r][c]) return false;
            }
        }
        return true;
    }
}