class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String s = Integer.toString(n, k);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '0') {
                StringBuilder sb = new StringBuilder();
                for (int j = i; j < s.length(); j++) {
                    c = s.charAt(j);
                    if (c == '0') break;
                    sb.append(c);
                }

                if (isPrime(Long.parseLong(sb.toString()))) {
                    if (s.contains('0' + sb.toString() + '0')) answer++;
                    else if (i == 0 && i + sb.length() < s.length() && s.charAt(i + sb.length()) == '0')
                        answer++;
                    else if (i + sb.length() == s.length() && i - 1 >= 0 && s.charAt(i - 1) == '0') answer++;
                    else if (s.length() == sb.length()) answer++;
                }
                i += sb.length() - 1;
            }
        }
        return answer;
    }
    
    private static boolean isPrime(long num) {
        if (num == 1) return false;
        boolean result = true;
        for (long i = 2; i * i <= num; i++) {
            if (i * i == num || num % i == 0) {
                result = false;
                break;
            }
        }
        return result;
    }
}