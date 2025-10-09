import java.util.*;
class Solution {
    static int[] emo;
    static int[][] user;
    public int[] solution(int[][] users, int[] emoticons) {
        
        user = users;
        emo = emoticons;
        /**
        서비스 가입자 수 > 판매액
        (가입자 수가 동일할 경우 판매액이 높은 방향으로.)
        사용자 <= 100 || 이모티콘 개수 <= 7
        제일 쉬운 접근은 이모티콘 별로 할인율을 적용 후 결과값 계산
        떠오르는 접근법
        1. DFS : 완전탐색 (이모티콘 개수가 낮음, 사용자가 최대 100이라 XX)
        2. DP : 이모티콘 별 할인율 테이블을 통해 결과값 재활용 (3차원 아니면 힘들듯)
        3. BFS : 이모티콘 할인율 별 결과값에 대해서 상태 전이
        
        이모티콘 가격(+ 할인율)에 따라서 사용자별로 살지 안살지 적용
        이모티콘 구매 금액이 일정 비율(사용자에 따라 상이) 도달할 경우 결제 취소 및 서비스 가입
        최종적으로 가입자수 -> 판매금액이 제일 높은 결과 선택.
        **/
        
        
        proc();
        return new int[]{maxUser, maxPrice};
    }
    
    static class Info {
        // 현재 이모티콘 순서, 각 사용자 별 구매 금액 및 서비스 가입 여부
        int cnt;
        boolean[] isService;
        int[] buyPrice;
        public Info(int cnt, boolean[] isService, int[] buyPrice) {
            this.cnt = cnt;
            this.isService = isService;
            this.buyPrice = buyPrice;
        }
        public boolean[] getIsService() {
            boolean[] service = this.isService.clone();
            return service;
        }
        public int[] getBuyPrice() {
            int[] price = this.buyPrice.clone();
            return price;
        }
    }
    
    static int maxUser;
    static int maxPrice;
    
    static void proc() {
        Queue<Info> q = new ArrayDeque<>();
        q.offer(new Info(0, new boolean[user.length], new int[user.length]));
        
        
        while(!q.isEmpty()) {
            Info cur = q.poll();
            
            if(cur.cnt == emo.length) {
                //결과값 갱신
                int totalServiceUser = 0;
                int totalPrice = 0;
                for(int i=0; i<cur.isService.length; i++) {
                    if(cur.isService[i]) totalServiceUser++;
                    totalPrice += cur.buyPrice[i];
                }
                if(maxUser <= totalServiceUser) {
                    if(maxUser == totalServiceUser) {
                        if(maxPrice < totalPrice) {
                            maxPrice = totalPrice;
                        }
                    }
                    else {
                        maxUser = totalServiceUser;
                        maxPrice = totalPrice;
                    }
                }
                
                continue;
            }
            
            
            
            for(int i=0; i<=4; i++) {
                double rate = 10 * i;
                double discountRate = rate/100;
                int emoPrice = emo[cur.cnt] - (int) (emo[cur.cnt] * discountRate);
                boolean[] service = cur.getIsService();
                int[] price = cur.getBuyPrice();
                
                for(int j=0; j<user.length; j++) {
                    if(service[j] || user[j][0] > rate) continue;
                    //구매할 경우
                    price[j] += emoPrice;
                    if(user[j][1] <= price[j]) {
                        service[j] = true;
                        price[j] = 0;
                    }
                }
                q.offer(new Info(cur.cnt + 1, service, price));
            }
        }
    }
}