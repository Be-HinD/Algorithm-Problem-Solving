import java.util.*;
class Solution {
    public long solution(int[] sequence) {

        int n = sequence.length;
        
        //누적합 연산 + 최대 구간 탐색 및 인덱스 저장
        long[] plusSum = new long[n];
        long[] minusSum = new long[n];
        plusSum[0] = sequence[0]; minusSum[0] = sequence[0] * -1;
        long plusMax = plusSum[0], minusMax = minusSum[0];
        int pIdx = 0, mIdx = 0;
        
        for(int i=1; i<n; i++) {
            if(i%2 == 0) {
                plusSum[i] = sequence[i] + plusSum[i-1];
                minusSum[i] = (sequence[i] * -1) + minusSum[i-1];
            }
            else {
                plusSum[i] = (sequence[i] * -1) + plusSum[i-1];
                minusSum[i] = sequence[i] + minusSum[i-1];
            }
            if(plusSum[i] > plusMax) {
                plusMax = plusSum[i];
                pIdx = i;
            }
            if(minusSum[i] > minusMax) {
                minusMax = minusSum[i];
                mIdx = i;
            }
        }
    
        
        //최대값에서부터 구간합 탐색
        long plusRes = plusSum[pIdx], minusRes = minusSum[mIdx];
        for(int i=pIdx-1; i>=0; i--) {
            plusRes = Math.max(plusRes, plusSum[pIdx] - plusSum[i]);
        }
        
        for(int i=mIdx-1; i>=0; i--) {
            minusRes = Math.max(minusRes, minusSum[mIdx] - minusSum[i]);
        }
        
        return Math.max(plusRes, minusRes);
    }
}