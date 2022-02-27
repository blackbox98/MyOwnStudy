package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1213 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_1213_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			String s = br.readLine();
			String str = br.readLine();
			int ans = 0;
			for (int i = 0; i < str.length(); i++) {
                boolean flag = false;
                if (i <= str.length() - s.length() && str.charAt(i) == s.charAt(0)) {
                    flag = true;
                    for (int j = 1; j < s.length(); j++) {
                        if (str.charAt(i + j) != s.charAt(j)) {
                            flag = false;
                        }
                    }
                }
                if(flag) {
                    ans++;
                }
            }
            System.out.printf("#%d %d\n", tc, ans);
			/*
			int oldLen = str.length();
			str = str.replace(s, "");
			int newLen = str.length();
			System.out.printf("#%d %d\n", tc, (oldLen - newLen) / s.length());
			*/
		}
	}
}