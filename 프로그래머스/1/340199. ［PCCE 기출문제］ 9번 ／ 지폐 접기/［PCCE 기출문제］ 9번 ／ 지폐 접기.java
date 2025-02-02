class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        // 100 120 1
        // 100 60 2
        // 50 60 3
        // 50 30 4
        while(true) {
            if((bill[0] <= wallet[0] && bill[1] <= wallet[1]) || (bill[0] <= wallet[1] && bill[1] <= wallet[0])) {
                break;
            }
            answer++;
            if(bill[0] > bill[1]) {
                bill[0] /= 2;
            }
            else {
                bill[1] /= 2;
            }
        }
        return answer;
    }
}