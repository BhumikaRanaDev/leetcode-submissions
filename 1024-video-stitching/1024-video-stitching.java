class Solution {
    public int videoStitching(int[][] clips, int time) {
        int count = 0;
        int end = 0;
        int farthest = 0;
        int i = 0;

        while (end < time) {
            for (int[] clip : clips) {
                if (clip[0] <= end) {
                    farthest = Math.max(farthest, clip[1]);
                }
            }

            if (farthest == end) {
                return -1;
            }

            count++;
            end = farthest;
        }

        return count;
    }
}