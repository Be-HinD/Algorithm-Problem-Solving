import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        //각 인원의 선물지수를 통계 ㅋㅋ
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<friends.length; i++) {
            map.put(friends[i], i);
        }
        // ex : muzi : 0, ryan : 1 ~~
        
        int[][] arr = new int[friends.length+1][friends.length];
        for(int i=0; i<gifts.length; i++) {
            String[] idx = gifts[i].split(" ");
            arr[map.get(idx[0])][map.get(idx[1])]++;
        }
        
        
        ////////////////////
        //선물지수 통계
        int[] cosdac = new int[friends.length];
        for(int i=0; i<friends.length; i++) {
            int send = 0;
            int give = 0;
            for(int j=0; j<friends.length; j++) {
                send += arr[i][j];
                give += arr[j][i];
            }
            cosdac[i] = send - give;
        }
        int[] cnt = new int[friends.length];
        
        for(int i=0; i<friends.length; i++) {
            for(int j=0; j<friends.length; j++) {
                if(i == j) continue;
                
                if(arr[i][j] == arr[j][i]) {
                    //i의 선물지수와 j의 선물지수를 비교
                    
                    if(cosdac[i] == cosdac[j]) continue;
                    if(cosdac[i] > cosdac[j]) cnt[i]++;
                }
                else if(arr[i][j] > arr[j][i]) {
                    cnt[i]++;
                }
                
            }
        }
        for(int i=0; i<friends.length; i++) {
            answer = Math.max(answer, cnt[i]);
        }
        return answer;
    }
}