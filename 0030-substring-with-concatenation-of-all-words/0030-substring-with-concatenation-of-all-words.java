import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> ans = new ArrayList<>();

        if (s.length() == 0 || words.length == 0) {
            return ans;
        }

        int wordLength = words[0].length();
        int wordCount = words.length;
        int totalLength = wordLength * wordCount;

        if (s.length() < totalLength) {
            return ans;
        }

        // Frequency of words that we need
        HashMap<String, Integer> needed = new HashMap<>();

        for (String word : words) {
            needed.put(word, needed.getOrDefault(word, 0) + 1);
        }

        // Try every possible starting offset
        for (int start = 0; start < wordLength; start++) {

            int left = start;
            int right = start;
            int count = 0;

            HashMap<String, Integer> current = new HashMap<>();

            while (right + wordLength <= s.length()) {

                // Take the next word-sized part
                String word = s.substring(right, right + wordLength);
                right += wordLength;

                // If this word is not in words
                if (!needed.containsKey(word)) {
                    current.clear();
                    count = 0;
                    left = right;
                    continue;
                }

                // Add the word to current window
                current.put(word, current.getOrDefault(word, 0) + 1);
                count++;

                // Too many occurrences of this word
                while (current.get(word) > needed.get(word)) {

                    String leftWord = s.substring(left, left + wordLength);
                    current.put(leftWord, current.get(leftWord) - 1);

                    left += wordLength;
                    count--;
                }

                // We have exactly all words
                if (count == wordCount) {
                    ans.add(left);

                    // Move window forward to search for another answer
                    String leftWord = s.substring(left, left + wordLength);
                    current.put(leftWord, current.get(leftWord) - 1);

                    left += wordLength;
                    count--;
                }
            }
        }

        return ans;
    }
}