
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int min = nums.length + 1;

        for(int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while(sum >= target) {
                int length = right - left + 1;

                if(length < min) {
                    min = length;
                }

                sum -= nums[left];
                left++;
            }
        }

        if(min == nums.length + 1) {
            return 0;
        }

        return min;
    }
}
