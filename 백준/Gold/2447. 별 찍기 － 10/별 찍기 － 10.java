import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static char[][] stars;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stars = new char[N][N];
        for (int r = 0; r < N; r++) {
            Arrays.fill(stars[r], '*');
        }
        setArea(0, 0, 3);
        StringBuilder answer = new StringBuilder();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                answer.append(stars[r][c]);
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
    
    private static void setArea(int sr, int sc, int size) {
        if (size > N) return;
        for (int nr = sr; nr < N; nr += size) {
            for (int nc = sc; nc < N; nc += size) {
                removeStar(nr, nc, size);
            }
        }
        setArea(sr, sc, size * 3);
    }
    
    private static void removeStar(int sr, int sc, int size) {
        int curSize = size / 3;
        for (int r = sr + curSize; r < sr + (curSize * 2); r++) {
            for (int c = sc + curSize; c < sc + (curSize * 2); c++) {
                stars[r][c] = ' ';
            }
        }
    }
}