class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] res = new int[2];
        
        /**
        <정석적인 구간합 문제>
        1. 길이가 짧은 수열 > 시작 인덱스가 낮은.
        방법 1. 투포인터 : right를 늘리면서 커지면 left를 당기는 방식 -> 풀이완
        **/
        
        int l=0, r=0, sum=sequence[0], min=Integer.MAX_VALUE;
        
        while(l<sequence.length) {
            if(sum == k) {
                if(r-l < min) { // 시작 인덱스 조건절
                    res[0] = l; res[1] = r;
                    min = r-l;
                }
            }
            
            if(sum > k){
                sum -= sequence[l++];
            }
            else {
                if(r+1 >= sequence.length) break;
                sum += sequence[++r];
            }
        }
        
        return res;
    }
}