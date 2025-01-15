/**
키워드
x의 길이가 2 이상의 짝수
x[0] != x[1], x[2] != x[3]
교집합의 원소의 개수가 1 이상
1 <= a <= 500,000
a의 모든 수는 0 이상 (a의 길이) 미만

접근법
최대 시간복잡도 : O(NlogN)
1 <= a <= 500,000
스타수열의 부분수열이 될 수 있는 x를 기준으로 카운팅
1회 반복 -> idx, idx+1을 기준으로 서로 수가 다르다면 교집합 x를 기준으로 +1
**/
import java.util.*;
class Solution {
    public int solution(int[] a) {
        int res = 0;
        
        int[] arr = new int[a.length]; //a의 모든 수는 0 이상 (a의 길이) 미만
        
        for(int i=0; i<a.length; i++) {
            arr[a[i]]++;
        }
        
        for(int i=0; i<a.length; i++) { // i -> 교집합 x
            if(arr[i] <= res) continue; // 교집합 x의 최소값 처리
            
            int temp = 0;
            for(int j=0; j<a.length-1; j++) {
                if(a[j]!=a[j+1]) { //연속된 두 수가 다르고
                    if(i == a[j] || i == a[j+1]) { //두 수 중 x가 포함
                        temp++; //x를 포함하는 부분집합 + 1
                        j++; // 연속된 두 수는 포함하면 안됨.
                    }
                }
            }
            
            res = Math.max(res, temp);
        }
        
        
        return res*2;
    }
}