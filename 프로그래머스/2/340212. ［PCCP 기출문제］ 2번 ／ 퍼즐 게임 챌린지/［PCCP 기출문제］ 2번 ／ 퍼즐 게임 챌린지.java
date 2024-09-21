class Solution {
    static long limit;
    static int[] puzzle, time;
    public int solution(int[] diffs, int[] times, long limit) {
        
        //이분탐색(lower)으로 시간 설정 및 가능여부 판단
        this.limit = limit;
        puzzle = diffs;
        time = times;
        
        return lowerBound();
    }
    
    private static int lowerBound() {
        int low = 1;
        int high = Integer.MAX_VALUE;
        
        while(low < high) {
            final int mid = low + (high - low) / 2;
            
            long delayTime = isPossible(mid);
            if(delayTime > limit) {    //제한시간 내 못푸는 경우 mid 올려야함.
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        return high;
    }
    
    private static long isPossible(int level) {
        long res = 0;
        int prevTime = 0;
        for(int i=0; i<puzzle.length; i++) {
            if(puzzle[i] <= level) {
                res += time[i];
                prevTime = time[i];
                continue;
            }
            int diff = puzzle[i] - level;
            
            res += (time[i] + prevTime) * diff + time[i];
            prevTime = time[i];
        }
        
        return res;
    }
}