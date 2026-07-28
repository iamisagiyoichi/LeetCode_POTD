class Solution {
public:
    string smallestPalindrome(string s) {
        vector<int> freq(26, 0);

        for (char c : s) {
            freq[c - 'a']++;
        }

        string firstHalf = "";
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            firstHalf.append(freq[i] / 2, char('a' + i));
            if (freq[i] % 2 == 1) {
                mid = char('a' + i);
            }
        }

        string secondHalf = firstHalf;
        reverse(secondHalf.begin(), secondHalf.end());

        if (mid != 0) {
            return firstHalf + mid + secondHalf;
        }

        return firstHalf + secondHalf;
    }
};
