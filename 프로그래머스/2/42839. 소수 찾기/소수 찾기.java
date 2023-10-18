import java.util.*;
class Solution {
    static int res;
    static boolean[] v;
    static char[] arr;
    static HashSet<Integer> hs = new HashSet<>();
    public int solution(String numbers) {        
        v = new boolean[numbers.length()];
        for(int i=1; i<=numbers.length(); i++) {
            arr = new char[i];
            Perm(0, numbers);
        }
        
        Iterator iter = hs.iterator();
        while(iter.hasNext()){
            int idx = (int) iter.next();
            boolean flag = true;
            System.out.println(idx);
            if(idx == 1 || idx == 0) continue;
            if(idx == 2) {
                res++;
                continue;
            }
            for(int i=2; i<idx; i++) {
                if(idx % i == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag) res++;
            // System.out.println("소수 : " + idx);
        }
        return res;
    }
    private static void Perm(int cnt, String s) {
        if(cnt == arr.length) {
            String item = "";
            for(int i=0; i<arr.length; i++) {
                item += arr[i];
            }
            int idx = Integer.parseInt(item);
            hs.add(idx);
        } else {
            for(int i=0; i<s.length(); i++) {
                if(v[i]) continue;
                arr[cnt] = s.charAt(i);
                v[i] = true;
                Perm(cnt+1, s);
                v[i] = false;
            }
        }
    }
}