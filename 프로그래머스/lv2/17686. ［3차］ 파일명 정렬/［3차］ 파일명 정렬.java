import java.util.Arrays;

class Solution {
    public String[] solution(String[] files) {
        Arrays.sort(files, (o1, o2) -> {
            String o1Head = o1.split("[0-9]")[0];
            String o2Head = o2.split("[0-9]")[0];
            if (o1Head.equalsIgnoreCase(o2Head)) {
                String o1Body = o1.substring(o1Head.length());
                String o2Body = o2.substring(o2Head.length());
                return Integer.compare(Integer.parseInt(findNum(o1Body)), Integer.parseInt(findNum(o2Body)));
            } else {
                return o1Head.compareToIgnoreCase(o2Head);
            }
        });
        return files;
    }
    
    private static String findNum(String body) {
        StringBuilder number = new StringBuilder();
        for (char c : body.toCharArray()) {
            if (Character.isDigit(c) && number.length() <= 5) number.append(c);
            else break;
        }
        return number.toString();
    }
}