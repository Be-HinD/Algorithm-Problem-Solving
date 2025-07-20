import java.util.*;
/**
m : 늘어나는 사용자 수
k : 반납까지 걸리는 시간
n : 증설된 서버의 수
m명 이상일 때 증설 필요.
result = 증설된 서버의 개수

접근법
: 단순 구현 (반복문)
: PQ
: PQ.size() -> 현재 서버의 수
: PQ.offer() -> result++
: PQ.peek() < 현재시간 -> PQ.poll()
: PQ.isEmpty() -> break
**/
class Solution {
    public int solution(int[] players, int m, int k) {
        int res = 0;
        PriorityQueue<Integer> server = new PriorityQueue<>();
        server.offer(100);
        
        for(int i=0; i<players.length; i++) {
            int player = players[i];
            
            while(!server.isEmpty()) {
                if(server.peek() < i) {
                    server.poll();
                    continue;
                }
                break;
            }
            
            // 현재 최대 수용 인원 : ((서버 수 + 1) * m) - 1
            int maxPlayerCnt = server.size() * m - 1;
            if(player > maxPlayerCnt) {
                int roop = (player/m) + 1 - server.size();
                res += roop;
                while(roop-->0) {
                    server.offer(i + k - 1);
                }
            }
        }
        
        return res;
    }
}