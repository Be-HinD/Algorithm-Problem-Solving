import java.util.Arrays;
class Solution {
    public long solution(int n, int[] times) {
        //이분탐색 문제는 long 박고 시작하는게 좋음.
        long answer = 0;
        
        Arrays.sort(times); //오름차순 정렬
        
        long low = 1; //최소 시간, 0초일 경우는 없음.
        
        //제일 긴 시간을 가지는 검사관의 시간값
        //times는 int배열이라 타입변환 해줘야함..
        long high = (long) times[times.length-1] * n;
        
        
        //high로는 10초의 검사관이 6명을 다 검사하는 최악경우 수
        long mid = 0;
        answer = high; //최악경우가 정답일 수 있음.
        
        while(low<=high) {
            
            mid = low + (high-low)/2; //mid는 검사 끝나는 시간
            
            long sum = 0;
            for(int time : times) {
                //각 검사관이 mid라는 시간값을 기준으로 몇명이나 처리가능한지 총계
                //예로 5초만에 끝난다는 기준으로 한다면 최소값인 7초를 가진 검사관이
                //한 명도 검사를 못 끝내기 때문에 sum(처리한 사람 수) = 0
                sum += mid/time;
            }
            
            if(sum >= n) { //mid시간에 처리할 수 있는 사람이 n을 넘는다면
                answer = mid; //출력값에 임시저장
                high = mid -1; //해당 mid값은 안되는 시간이기에 비교할 필요없음. == -1
            }
            else {
                low = mid + 1;
            }
        }
        return answer;
    }
}