import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, P, X;
    static int[] arrX;
    static String[] nums = {"1110111", "0010010", "1011101", "1011011", "0111010", "1101011", "1101111", "1010010", "1111111", "1111011"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arrX = fillZero(X);
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (X != i && checkNum(i)) answer++;
        }
        System.out.println(answer);
    }

    private static int[] fillZero(int num) {
        int[] arr = new int[K];
        for (int i = K - 1; i >= 0; i--) {
            arr[i] = num % 10;
            num /= 10;
        }
        return arr;
    }

    private static boolean checkNum(int target) {
        int[] arrTarget = fillZero(target);
        int dif = 0;
        for (int i = K - 1; i >= 0; i--) {
            dif += checkDifferent(arrX[i], arrTarget[i]);
            if (dif > P) return false;
        }
        return true;
    }

    private static int checkDifferent(int base, int target) {
        int cnt = 0;
        for (int i = 0; i < 7; i++) {
            if (nums[base].charAt(i) != nums[target].charAt(i)) cnt++;
        }
        return cnt;
    }
}