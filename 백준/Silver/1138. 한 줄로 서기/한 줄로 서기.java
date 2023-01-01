import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arr = br.readLine().split(" ");
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = N - 1; i >= 0; i--) {
            list.add(Integer.parseInt(arr[i]), i + 1);
        }
        for (int n : list) {
            System.out.print(n + " ");
        }
    }
}