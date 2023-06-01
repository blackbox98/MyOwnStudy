import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    static List<String> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            expression(new char[N - 1], N, 0, 0);
            list.sort(Comparator.naturalOrder());
            for (String exp : list) {
                System.out.println(exp);
            }
            System.out.println();
        }
    }

    private static void expression(char[] arr, int N, int idx, int minusCnt) {
        if (idx == N - 1) {
            if (minusCnt > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < N - 1; i++) {
                    sb.append(i + 1);
                    sb.append(arr[i]);
                }
                sb.append(N);
                String[] s = sb.toString().replaceAll(" ", "").replaceAll("[+-]", " ").split(" ");
                int i = 0;
                int sum = Integer.parseInt(s[i++]);
                for (int j = 0; j < N - 1; j++) {
                    if (arr[j] == ' ') continue;
                    else if (arr[j] == '+') sum += Integer.parseInt(s[i++]);
                    else sum -= Integer.parseInt(s[i++]);
                }
                if (sum == 0) list.add(sb.toString());
            }
            return;
        }

        arr[idx] = '+';
        expression(arr, N, idx + 1, minusCnt);
        arr[idx] = '-';
        expression(arr, N, idx + 1, minusCnt + 1);
        arr[idx] = ' ';
        expression(arr, N, idx + 1, minusCnt);
    }
}