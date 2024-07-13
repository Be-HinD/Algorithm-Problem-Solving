import java.util.*;
class Solution {
    static int limit, res;
    static int[] arr;
    public int solution(String[][] relation) {
        int answer = 0;
        
        /**
        그리디하게 생각하면 1 ~ relation.length 만큼 조합 돌리면 됨.
        1~8까지 조합 구한 후(인덱스 번호), 유일성 최소성 확인?
        문자열 이어붙이기?
        r = 2개부터는 문자열 한 컬럼씩 이어붙이면서 Hash로 체크
        
        Hmm.....
        **/
        for(int i=1; i<relation[0].length; i++) {
            limit = i;
            arr = new int[i];
            comb(0,0,relation);
        }
        
        return result.size()==0 ? 1 : result.size();
    }
    
    static List<String> result = new ArrayList<>();
    static void comb(int start, int cnt, String[][] relation) {
        if(cnt == limit) {
            // 이전 후보키로 선정된 값이 포함되어있는지 체크
            //ex) 0,1,2 중 0,2 속성이 포함되어있는지...
            //굿.
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<arr.length; i++) sb.append(arr[i]);
            
            //최소성 확인
            for(String idx : result) {
                int count = 0;
                for(int i=0; i<idx.split("").length; i++) {
                    if(sb.toString().contains(String.valueOf(idx.charAt(i)))) {
                        count++;
                    }
                }
                
                if(idx.length() == count) {
                    return;
                }
            }
            
            if(checkCandidateKey(relation)) {
                result.add(sb.toString());   //02 추가
            }
            return;
        }
        for(int i=start; i<relation[0].length; i++) {
            arr[cnt] = i;
            comb(i+1, cnt+1, relation);
        }
    }
    
    static boolean checkCandidateKey(String[][] relation) {
        int cnt = 0;
        int minimal = 0;    //최소성 체크 변수
        while(cnt++ <limit) {
            
            Set<String> tuple = new HashSet<>();
            boolean flag = true;
            for(int i=0; i<relation.length; i++) {
                String idx = "";
                for(int j=0; j<cnt; j++) {
                    idx += relation[i][arr[j]];
                }
                if(tuple.contains(idx)) {
                    flag = false;
                    break;
                }
                tuple.add(idx);
            }
            if(!flag) continue;
            minimal++;
            if(minimal > 1) return false; //최소성 만족못함
            
        }
        if(minimal == 0) return false; // 유일성 만족못함
        return true;
        
    }
}