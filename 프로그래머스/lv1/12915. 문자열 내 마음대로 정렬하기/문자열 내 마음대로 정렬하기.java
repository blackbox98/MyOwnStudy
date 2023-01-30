import java.util.*;
class Solution {
    static int N;
    public String[] solution(String[] strings, int n) {
        String[] answer = new String[strings.length];
        N = n;
        List<StringGroup> list = new ArrayList<>();
        for (String s : strings) {
            list.add(new StringGroup(s));
        }
        Collections.sort(list);
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i).s;
        }
        return answer;
    }
    
    private static class StringGroup implements Comparable<StringGroup> {
        String s;

        public StringGroup(String s) {
            this.s = s;
        }

        @Override
        public int compareTo(StringGroup o) {
            if (this.s.charAt(N) == o.s.charAt(N)) return this.s.compareTo(o.s);
            return s.substring(N).compareTo(o.s.substring(N));
        }
    }
}