class Solution {
    static int maxHp;
    static int[] band;
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        /**
        bandage[0] == 기술 시전 시간
        bandage[1] == 1초당 회복량
        bandage[2] == 추가 회복량
        **/
        band = bandage;
        maxHp = health;
        int prevAtk = 0;
        for(int i=0; i<attacks.length; i++) {
            int[] curAtk = attacks[i];
            int diff = curAtk[0] - prevAtk - 1;
            int plusHeal = heal(diff);
            if(health + plusHeal > maxHp) health = maxHp;
            else health += plusHeal;
            health -= curAtk[1];
            prevAtk = curAtk[0];
            if(health <= 0) {
                return -1;
            }
            
            
        }
        
        
        
        return health;
    }
    
    private static int heal(int diffTime) {
        int totalHeal = band[1] * diffTime;
        if(band[0] <= diffTime) {
            int cnt = diffTime / band[0];
            totalHeal += band[2] * cnt;
        }
        
        return totalHeal;
    }
}