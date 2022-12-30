import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] strArr = new String[3];
        StringBuilder sb;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < str.length() - 1; i++) {
            for (int j = i + 1; j < str.length(); j++) {
                strArr[0] = str.substring(0, i);
                strArr[1] = str.substring(i, j);
                strArr[2] = str.substring(j, str.length());
                sb = new StringBuilder();
                for (int k = 0; k < 3; k++) {
                    for (int l = strArr[k].length() - 1; l >= 0; l--) {
                        sb.append(strArr[k].charAt(l));
                    }
                }
                list.add(sb.toString());
            }
        }
        Collections.sort(list);
        System.out.println(list.get(0));
    }
}