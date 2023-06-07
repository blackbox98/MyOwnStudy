import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(br.readLine());
        }
        list.sort((o1, o2) -> {
            if (o1.length() == o2.length()) {
                int o1ToInt = 0;
                int o2ToInt = 0;
                for (char c : o1.toCharArray()) {
                    if (c - '0' >= 0 && c - '0' < 10) o1ToInt += c - '0';
                }
                for (char c : o2.toCharArray()) {
                    if (c - '0' >= 0 && c - '0' < 10) o2ToInt += c - '0';
                }
                if (o1ToInt == o2ToInt) {
                    return o1.compareTo(o2);
                } else return Integer.compare(o1ToInt, o2ToInt);
            } else return Integer.compare(o1.length(), o2.length());
        });
        for (String s : list) {
            System.out.println(s);
        }
    }
}