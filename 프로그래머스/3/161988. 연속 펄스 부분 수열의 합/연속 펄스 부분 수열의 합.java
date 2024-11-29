/**
키워드
펄스 수열 : 1또는 -1로 시작, 두 수가 번갈아 나오는 수열
연산 : 연속 부분 수열 * 펄스 수열
합 중 가장 큰 것을 return
접근법
수열 길이 <= 500,000 O(NlogN)까지 가능
원소 크기 <= 100,000 == long
정렬 x
완탐 : 모든 부분 수열의 펄스 수열 구하기 -> 값 갱신 (2^N)
각 수열에 +1, -1곱한 값을 미리 구하기 2차원 배열

**/
import java.util.*;
class Solution {
    public long solution(int[] sequence) {

        int n = sequence.length;
        int[] plus = new int[n];
        int[] minus = new int[n];
        
        for(int i=0; i<n; i++) {
            if(i % 2 == 0) {
                plus[i] = sequence[i] * 1;
                minus[i] = sequence[i] * -1;
            }
            else {
                plus[i] = sequence[i] * -1;
                minus[i] = sequence[i] * 1;
            }
        }
        
        //누적합 구하기
        long[] minusSum = new long[n];
        minusSum[0] = minus[0];
        for(int i=1; i<n; i++) {
            minusSum[i] = minus[i] + minusSum[i-1];
            
        }
        
        long[] plusSum = new long[n];
        plusSum[0] = plus[0];
        for(int i=1; i<n; i++) {
            plusSum[i] = plus[i] + plusSum[i-1];
        }
        
        
        //누적합 최대값 구하기
        long mMax=-200000, pMax=-200000;
        int minusIdx=0, plusIdx=0;
        
        for(int i=0; i<n; i++) {
            if(mMax < minusSum[i]) {
                mMax = minusSum[i];
                minusIdx = i;
            }
            if(pMax < plusSum[i]) {
                pMax = plusSum[i];
                plusIdx = i;
            }
        }
        
        //최대값에서
        for(int i=plusIdx; i>=0; i--) {
            if(plusSum[plusIdx] - plusSum[i] > pMax) {
                pMax = plusSum[plusIdx] - plusSum[i];
            }
        }
        
        for(int i=minusIdx; i>=0; i--) {
            if(minusSum[minusIdx] - minusSum[i] > mMax) {
                mMax = minusSum[minusIdx] - minusSum[i];
            }
        }
        
        return Math.max(pMax, mMax);
    }
}