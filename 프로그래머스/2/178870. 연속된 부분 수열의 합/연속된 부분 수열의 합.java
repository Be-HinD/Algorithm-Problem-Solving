class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] res = new int[2];
        /**
        1. 길이가 짧은 수열 > 시작 인덱스가 낮은.
        방법 1. 투포인터 : right를 늘리면서 커지면 left를 당기는 방식
        방법 2. 누적합
        **/
        
        int l=0, r=0, sum=sequence[0], min=Integer.MAX_VALUE;
        
        while(l<sequence.length) {
            if(sum == k) {
                if(r-l < min) {
                    res[0] = l; res[1] = r;
                    min = r-l;
                }
            }
            else if(sum > k){
                sum -= sequence[l++];
                continue;
            }
            if(r+1 < sequence.length) {
                sum += sequence[++r];
            }
            else break;
        }
        
        
        return res;
    }
}