import java.util.*;

class Solution {
    static int max, res;
    static int[] arr, answer;
    static int[][] list;
    public int solution(int n, int[][] q, int[] ans) {
        
        /**
        1~n 5개 뽑는 조합
        조합에서 나온 수 Set 구성
        q 순회하면서 set이랑 겹치는 수가 ans로 나오는지.
        **/
        max = n;
        arr = new int[5];
        answer = ans.clone();
        list = new int[q.length][5];
        
        for(int i=0; i<q.length; i++) {
            list[i] = q[i].clone();
        }
        
        comb(0, 1);
        
        return res;
    }
    
    static void comb(int idx, int start) {
        if(idx == 5) {
            //logic
            search();
            return;
        }
        for(int i=start; i<=max; i++) {
            arr[idx] = i;
            comb(idx+1, i + 1);
        }
    }
    
    static void search() {
        Set<Integer> candi = new HashSet<>();
        
        for(int idx : arr) {
            candi.add(idx);
        }
        
        boolean flag = true;
        for(int i=0; i<list.length; i++) {
            int[] idx = list[i];
            int cnt = 0;
            for(int num : idx) {
                if(candi.contains(num)) cnt++;
            }
            if(cnt != answer[i]) {
                flag = !flag;
                break;
            }
        }
        
        if(flag) res++;
    }
}