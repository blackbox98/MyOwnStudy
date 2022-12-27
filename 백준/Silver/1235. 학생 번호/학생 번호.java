import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = 0;
        int N = Integer.parseInt(br.readLine());
        String[] str = new String[N];
        for (int i = 0; i < N; i++) {
            str[i] = br.readLine();
        }
        boolean flag = false;
        ArrayList<Integer> list;
        int[] check;
        int size = str[0].length();
        L : for (int i = 1; i <= size; i++) {
            list = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(str[j].substring(str[j].length() - i));
                if (list.contains(n)) continue L;
                list.add(n);
                if (j == N - 1) {
                    flag = true;
                    ans = i;
                }
            }
            if (flag) break;
        }
        System.out.println(ans);
    }
}