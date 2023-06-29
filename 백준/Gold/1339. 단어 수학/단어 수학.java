import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] values = new int[26];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int value = (int) Math.pow(10, s.length() - 1);
            for (char c : s.toCharArray()) {
                values[c - 'A'] += value;
                value /= 10;
            }
        }
        Arrays.sort(values);
        int num = 9;
        int answer = 0;
        for (int i = 25; i >= 0; i--) {
            if (values[i] == 0) break;
            answer += values[i] * num--;
        }
        System.out.println(answer);
    }
}