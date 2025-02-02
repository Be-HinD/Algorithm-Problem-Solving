class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        
        boolean[] v = new boolean[10];
        for(int idx : numbers) v[idx] = true;
        
        for(int i=0; i<v.length; i++) {
            answer += v[i] ? 0 : i;
        }
        return answer;
    }
}