import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Person[] people = new Person[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            people[i] = new Person(i, w, h, 1);
        }
        Arrays.sort(people, Comparator.comparingInt(p -> p.w));
        int bigger;
        for (int i = N - 2; i >= 0; i--) {
            bigger = 0;
            for (int j = i + 1; j < N; j++) {
                if (people[i].w < people[j].w && people[i].h < people[j].h) bigger++;
            }
            people[i].rank += bigger;
        }
        Arrays.sort(people, Comparator.comparingInt(p -> p.idx));
        StringBuilder sb = new StringBuilder();
        for (Person p : people) {
            sb.append(p.rank).append(" ");
        }
        System.out.println(sb);
    }

    private static class Person {
        int idx;
        int w;
        int h;
        int rank;

        private Person(int idx, int w, int h, int rank) {
            this.idx = idx;
            this.w = w;
            this.h = h;
            this.rank = rank;
        }
    }
}