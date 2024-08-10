import java.util.*;
class Solution {
    class Data {
        int value;
        int index;

        Data (int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        Deque<Data> dq = new ArrayDeque<>();

        for (int i = 0; i < stones.length; i++) {
            int stone = stones[i];

            // 슬라이딩 윈도우의 크기가 k가 되도록 조절
            while (!dq.isEmpty() && i - dq.peekFirst().index >= k) {
                dq.pollFirst();
            }

            // 윈도우 내부의 최대 값을 유지
            while (!dq.isEmpty() && stone > dq.peekLast().value) {
                dq.pollLast();
            }

            dq.addLast(new Data(stone, i));

            // 슬라이딩 윈도우의 크기가 k가 되면 최대 값을 확인하고 갱신
            if (i >= k - 1) {
                answer = Math.min(answer, dq.peekFirst().value);
            }
        }

        if (answer == Integer.MAX_VALUE) return 0;
        return answer;
    }
}