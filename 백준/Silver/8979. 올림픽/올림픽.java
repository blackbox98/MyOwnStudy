import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        PriorityQueue<Country> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());
            pq.offer(new Country(num, gold, silver, bronze));
        }
        int rank = 1;
        Country country = pq.poll();
        int answer = country.num;
        int sameRank = 0;
        while (answer != K && !pq.isEmpty()) {
            Country next = pq.poll();
            if (country.equals(next)) sameRank++;
            else {
                rank += sameRank + 1;
                sameRank = 0;
            }
            country = next;
            if (country.num == K) {
                answer = rank;
                break;
            }
        }
        System.out.println(answer);
    }

    private static class Country implements Comparable<Country> {
        int num;
        int gold;
        int silver;
        int bronze;

        private Country(int num, int gold, int silver, int bronze) {
            this.num = num;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public int compareTo(Country o) {
            if (this.gold == o.gold) {
                if (this.silver == o.silver) {
                    if (this.bronze == o.bronze) {
                        return this.num - o.num;
                    } else return o.bronze - this.bronze;
                } else return o.silver - this.silver;
            } else return o.gold - this.gold;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Country country = (Country) o;
            return gold == country.gold && silver == country.silver && bronze == country.bronze;
        }

        @Override
        public int hashCode() {
            return Objects.hash(gold, silver, bronze);
        }
    }
}