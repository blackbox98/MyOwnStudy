// import java.time.LocalDate;
// import java.time.format.TextStyle;
// import java.util.Locale;
class Solution {
    public String solution(int a, int b) {
        String[] dayOfWeek = {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"};
        int[] daysOfMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        int plusDay = b - 1;
        for (int i = 1; i < a; i++) {
            plusDay += daysOfMonth[i - 1];
        }
        return dayOfWeek[plusDay % 7];
        // return LocalDate.of(2016, a, b).getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US).toUpperCase();
    }
}