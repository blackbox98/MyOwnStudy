import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        char[] alphabet = name.toCharArray();
        Integer[] counts = new Integer[26];
        for (int i = 0; i < counts.length; i++) {
            counts[i] = 0;
        }
        for (char c : alphabet) {
            counts[c - 65]++;
        }
        ArrayList<AlphaCnt> list = new ArrayList<AlphaCnt>();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                list.add(new AlphaCnt((char) (i + 65), counts[i]));
            }
        }
        int oddNum = 0; // 홀수의 갯수
        char oddAlpha = ' ';
        for (AlphaCnt alphaCnt : list) {
            if (alphaCnt.n % 2 != 0) {
                oddNum++;
                oddAlpha = alphaCnt.c;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (oddNum > 1) {
            sb.append("I'm Sorry Hansoo");
        } else {
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).n / 2; j++) {
                    sb.append(list.get(i).c);
                }
                list.get(i).n /= 2;
            }
            if (oddAlpha != ' ') { // 문자의 갯수 중 홀수 개가 있는 경우
                sb.append(oddAlpha);
            }
            for (int i = list.size() - 1; i >= 0; i--) {
                for (int j = 0; j < list.get(i).n; j++) {
                    sb.append(list.get(i).c);
                }
            }
        }
        System.out.println(sb);
    }

    public static class AlphaCnt {
        char c;
        int n;

        public AlphaCnt(char c, int n) {
            this.c = c;
            this.n = n;
        }
    }
}