class Solution {
    public int solution(int[] numbers) {
        int answer = 45;
        
        for(int idx : numbers) answer -= idx;
        return answer;
    }
}