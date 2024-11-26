import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        //enroll : 각 판매원 이름 10,000
        // referral : 부모 판매원 이름 enroll과 동일
        // seller : 판매원 이름 100,000
        // amount : 판매 수량 seller와 동일
        /**
        키워드
        10%
        자신이 조직에 추천하여 가입시킨 판매원에게서 발생하는 이익의 10% 까지 자신에 이익
        원 단위에서 절사하며
        10%를 계산한 금액이 1 원 미만인 경우에는 이득을 분배하지 않고 자신이 모두 가집니다.
        칫솔의 판매에서 발생하는 이익은 개당 100 원
        **/
        
        /**
        접근법
        enroll과 referral 입력을 트리 형태로 구성
        seller와 amount에 따른 수익 "갱신"
        각 판매원들에게 고유 인덱스 부여
        판매원의 부모는 1명
        판매원의 자식은 N명 : 인접리스트
        판매원으로부터 거슬러올라가면서 amount를 분배.
        **/
        
        // 편향트리의 경우 1000000000 연산
        
        
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> res = new HashMap<>();
        for(int i=0; i<enroll.length; i++) {
            parent.put(enroll[i], referral[i]); //판매원의 부모를 기록
            res.put(enroll[i], 0);
        }
        
    
        for(int i=0; i<seller.length; i++) {
            String cur = seller[i];
            int price = amount[i] * 100;
            while(true) {
                if(cur.equals("-")) break;
                
                int remain = (int) (price * 0.1);
                if(remain == 0) {
                    res.put(cur, res.get(cur) + price);
                    break;
                }
                else { //10프로 이월이 된다면
                    res.put(cur, res.get(cur) + (price-remain));
                    price = remain;
                    cur = parent.get(cur);
                }
            }
        }
        
        int[] ans = new int[enroll.length];
        int p = 0;
        for(int i=0; i<enroll.length; i++) {
            ans[i] = res.get(enroll[i]);
        }
        
        return ans;
    }
}