import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            boolean consistent = true;
            int n = Integer.parseInt(br.readLine());
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(br.readLine());
            }
            Collections.sort(list);
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).startsWith(list.get(i - 1))) {
                    consistent = false;
                    break;
                }
            }
            System.out.println(consistent ? "YES" : "NO");
        }
    }
}