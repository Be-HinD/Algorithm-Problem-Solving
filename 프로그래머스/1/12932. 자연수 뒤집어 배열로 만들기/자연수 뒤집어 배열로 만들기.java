class Solution {
    public int[] solution(long n) {
        
        char[] arr = String.valueOf(n).toCharArray();
        
        int[] answer = new int[arr.length];
        
        for(int i=arr.length-1; i>=0; i--) {
            answer[arr.length - i - 1] = arr[i] - '0';
        }
        return answer;
    }
}