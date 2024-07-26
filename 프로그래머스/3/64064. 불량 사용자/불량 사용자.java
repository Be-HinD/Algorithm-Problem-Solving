import java.util.*;
class Solution {
    static String[] user, ban;
    static boolean[] v;
    // static Set<String[]> res;
    static Set<List<String>> res;
    static String[] arr;
    public int solution(String[] user_id, String[] banned_id) {
        
        user = user_id.clone();
        ban = banned_id.clone();
        
        arr = new String[ban.length];
        v = new boolean[user.length];
        res = new HashSet<>();
        perm(0);
        
        return res.size();
    }
    
    static void perm(int cnt) {
        if(cnt == arr.length) {
            
            boolean flag = true;
            for(int i=0; i<arr.length; i++) {
                if(!check(arr[i], ban[i])) {
                    flag = false;
                }
            }
            if(flag) {
                String[] temp = arr.clone();
                Arrays.sort(temp);
            
                res.add(Arrays.asList(temp));
            }
            return;
        }
        for(int i=0; i<user.length; i++) {
            if(v[i]) continue;
            v[i] = true;
            arr[cnt] = user[i];
            perm(cnt+1);
            v[i] = false;
        }
    }
    
    static boolean check(String origin, String diff) {
        if(origin.length() != diff.length()) return false;
        
        for(int i=0; i<origin.length(); i++) {
            if(diff.charAt(i) == '*') continue;
            if(origin.charAt(i) != diff.charAt(i)) return false;
        }
        
        return true;
    }
}