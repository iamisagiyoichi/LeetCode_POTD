/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] ans = {-1, -1};
        
        int first = -1, prev = -1;
        int min = Integer.MAX_VALUE;
        int pos = 1;
        
        while (head.next != null && head.next.next != null) {
            int a = head.val;
            int b = head.next.val;
            int c = head.next.next.val;
            
            if ((b > a && b > c) || (b < a && b < c)) {
                if (first == -1) {
                    first = pos + 1;
                } else {
                    min = Math.min(min, pos + 1 - prev);
                }
                
                prev = pos + 1;
            }
            
            head = head.next;
            pos++;
        }
        
        if (first == -1 || first == prev)
            return ans;
        
        ans[0] = min;
        ans[1] = prev - first;
        
        return ans;
    }
}
