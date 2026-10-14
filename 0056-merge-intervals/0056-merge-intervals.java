
import java.util.Arrays;

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int[][] result = new int[intervals.length][2];
        int index = 0;

        for (int i = 0; i < intervals.length; i++) {
            if (index == 0 || intervals[i][0] > result[index - 1][1]) {
                result[index] = intervals[i];
                index++;
            } else {
                result[index - 1][1] =
                    Math.max(result[index - 1][1], intervals[i][1]);
            }
        }

        return Arrays.copyOf(result, index);
    }
}
