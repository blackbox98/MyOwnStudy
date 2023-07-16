import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] arr;
    static Stack<String> stack = new Stack<>();
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        int count = 0;
        int left = 1;
        int right = C - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (makeString(mid)) {
                count = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            stack.clear();
        }
        System.out.println(count);
    }

    private static boolean makeString(int start) {
        for (int c = 0; c < C; c++) {
            sb = new StringBuilder();
            for (int r = start; r < R; r++) {
                sb.append(arr[r][c]);
            }
            if (stack.contains(sb.toString())) return false;
            else stack.push(sb.toString());
        }
        return true;
    }
}