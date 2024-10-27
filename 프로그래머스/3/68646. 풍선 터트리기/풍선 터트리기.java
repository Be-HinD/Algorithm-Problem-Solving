class Solution {
    public int solution(int[] a) {
        int answer = 2; //왼쪽, 오른쪽 끝은 무조건 남길 수 있음.
        
        /**
        타겟 i를 기준으로 왼쪽, 오른쪽을 비교
        i보다 낮은값이 한쪽에라도 없어야함.
        즉 왼쪽 오른쪽 모두 i보다 작은값이 있다면 false
        left[i] = i에서의 최소값
        right[i] = i에서의 최소값
        **/
        if(a.length == 1) return 1;
        
        int[] left = new int[a.length];
        int[] right = new int[a.length];
        
        left[0] = a[0];
        for(int i=1; i<a.length; i++) {
            if(a[i] < left[i-1]) {
                left[i] = a[i];
            }
            else {
                left[i] = left[i-1];
            }
        }
        
        right[a.length-1] = a[a.length-1];
        for(int i=a.length-2; i>=0; i--) {
            if(a[i] < right[i+1]) {
                right[i] = a[i];
            }
            else {
                right[i] = right[i+1];
            }
        }
        
        for(int i=1; i<a.length-1; i++) {
            int cnt = 0;
            
            if(left[i-1] < a[i]) cnt++;
            if(right[i+1] < a[i]) cnt++;
            if(cnt==2) continue;
            answer++;
        }
        return answer;
    }
}