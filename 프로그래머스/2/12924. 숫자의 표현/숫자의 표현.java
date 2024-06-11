class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for(int i=0; i<n; i++) {
            int temp = 0;
            for(int j=i+1; j<=n; j++) {
                temp += j;
                if(temp > n) break;
                else if(temp < n) continue;
                else {
                    answer++;
                }
            }
        }
        return answer;
    }
}