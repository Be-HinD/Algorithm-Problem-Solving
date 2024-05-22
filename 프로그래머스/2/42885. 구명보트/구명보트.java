import java.util.Arrays;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        /**
        최대한 구명보트마다 limit에 맞춰서 채워야 함.
        !!!! 구명 보트는 2사람까지밖에 못탄다고 하네요 ㅋㅋㅋㅋ !!!!
        **/
        
        // step1. 오름차순 정렬
        Arrays.sort(people);
        
    
        boolean[] v = new boolean[people.length];   //탑승 여부 체크
        
        int p = 0; // 가벼운 사람의 인덱스
        for(int j=people.length-1; j>=p; j--) {
            
            if(people[j] + people[p] <= limit) {
                p++;
            }
            
            answer++;
        }
        
        return answer;
    }
}