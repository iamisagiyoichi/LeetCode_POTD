class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededRight = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                if (neededRight % 2 != 0) {
                    insertions++;
                    neededRight--;
                }
                neededRight += 2;
            } else {
                neededRight--;
                if (neededRight < 0) {
                    insertions++;
                    neededRight += 2;
                }
            }
        }

        return insertions + neededRight;
    }
}
