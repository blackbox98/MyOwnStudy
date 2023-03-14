import java.util.*;

class Solution {
    public int[] solution(String msg) {
        ArrayList<String> dic = new ArrayList<>();
        ArrayList<Integer> answer = new ArrayList<>();

        for(int i = 0; i < 26; i++) {
            dic.add(String.valueOf((char)('A' + i)));
        }

        for(int i = 0; i < msg.length(); i++) {
            for(int j = dic.size() - 1; j >= 0; j--) {
                if(msg.substring(i).startsWith(dic.get(j))) {
                    i += dic.get(j).length() - 1;
                    answer.add(j + 1);
                    if(i + 1 < msg.length()) dic.add(dic.get(j) + msg.charAt(i + 1));
                    break;
                }
            }
        }
        return answer.stream().mapToInt(n -> n).toArray();
    }
}