class Solution {
    public boolean checkString(String s) {
        boolean flag = false;
        for(char c : s.toCharArray()) {
            if(c == 'b')
                flag = true;
            else if(flag)
                return false;
        }
        return true;
    }
}
