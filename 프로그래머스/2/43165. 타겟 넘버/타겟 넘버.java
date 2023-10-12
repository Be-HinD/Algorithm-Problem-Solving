class Solution {
    static int t, cnt;
    public int solution(int[] numbers, int target) {
        t = target;
        dfs(0, numbers, 0);
        return cnt;
    }
    private static void dfs(int idx, int[] numbers, int res) {
        if(idx == numbers.length) { //타겟에 도달했을 경우
            if(res == t) cnt++;
        } else {
            dfs(idx+1, numbers, res+numbers[idx]);
            dfs(idx+1, numbers, res-numbers[idx]);
        }
    }
}