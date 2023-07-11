import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int D;
    static int[] oven;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        oven = new int[D];
        st = new StringTokenizer(br.readLine());
        for (int i = D - 1; i >= 0; i--) {
            oven[i] = Integer.parseInt(st.nextToken());
            if (i < D - 1 && oven[i] > oven[i + 1]) oven[i] = oven[i + 1];
        }
        int answer = -1;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int pizza = Integer.parseInt(st.nextToken());
            answer = binarySearch(pizza, answer + 1, D - 1);
            if (answer == -1) break;
        }
        if (answer == -1) answer = 0;
        else answer = D - answer;
        System.out.println(answer);
    }

    private static int binarySearch(int pizza, int low, int high) {
        boolean flag = false;
        while (low < high) {
            int mid = (low + high) / 2;
            if (oven[mid] < pizza) low = mid + 1;
            else {
                high = mid;
                flag = true;
            }
        }
        if (flag) return low;
        return -1;
    }
}