class Solution {
    static char[] dic = {'A', 'E', 'I', 'O', 'U'};
    
    public int solution(String word) {
        int answer = 0;
        int size = dic.length;
        StringBuilder sb = new StringBuilder();
        L : for (int a = 0; a < size; a++) {
            sb.append(dic[a]);
            answer++;
            if (sb.toString().equals(word)) break L;
            for (int b = 0; b < size; b++) {
                sb.append(dic[b]);
                answer++;
                if (sb.toString().equals(word)) break L;
                for (int c = 0; c < size; c++) {
                    sb.append(dic[c]);
                    answer++;
                    if (sb.toString().equals(word)) break L;
                    for (int d = 0; d < size; d++) {
                        sb.append(dic[d]);
                        answer++;
                        if (sb.toString().equals(word)) break L;
                        for (int e = 0; e < size; e++) {
                            sb.append(dic[e]);
                            answer++;
                            if (sb.toString().equals(word)) break L;
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    sb.deleteCharAt(sb.length() - 1);
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return answer;
    }
}