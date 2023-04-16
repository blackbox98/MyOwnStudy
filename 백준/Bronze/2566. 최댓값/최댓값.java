import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = -1;
        int R = -1;
        int C = -1;
        int size = 9;
        for (int r = 0; r < size; r++) {
            String[] tmp = br.readLine().split(" ");
            for (int c = 0; c < size; c++) {
                int n = Integer.parseInt(tmp[c]);
                if (answer < n) {
                    answer = n;
                    R = r + 1;
                    C = c + 1;
                }
            }
        }
        System.out.println(answer);
        System.out.println(R + " " + C);
    }
}
