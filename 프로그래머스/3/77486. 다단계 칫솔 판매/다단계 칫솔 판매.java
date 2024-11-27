import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> res = new HashMap<>();
        for(int i=0; i<enroll.length; i++) {
            parent.put(enroll[i], referral[i]); //판매원의 부모를 기록
            res.put(enroll[i], 0);
        }
        
    
        for(int i=0; i<seller.length; i++) {
            String cur = seller[i];
            int price = amount[i] * 100;
            while(true) {
                if(cur.equals("-")) break;
                
                int remain = (int) (price * 0.1);
                if(remain == 0) {
                    res.put(cur, res.get(cur) + price);
                    break;
                }
                else { //10프로 이월이 된다면
                    res.put(cur, res.get(cur) + (price-remain));
                    price = remain;
                    cur = parent.get(cur);
                }
            }
        }
        
        int[] ans = new int[enroll.length];
        for(int i=0; i<enroll.length; i++) {
            ans[i] = res.get(enroll[i]);
        }
        
        return ans;
    }
}