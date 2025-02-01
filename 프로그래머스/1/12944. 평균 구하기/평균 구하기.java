class Solution {
    public double solution(int[] arr) {
        double answer = 0;
        for(int idx : arr) {
            answer += idx;
        }
        return answer / arr.length;
    }
}