import java.util.*;
class Solution {
    public int solution(int[] food_times, long k) {
      ArrayList<Integer> sT = new ArrayList<>();
		//주의 맨앞에 0이 있어야한다. 
		sT.add(0);
		for(int x:food_times)
			sT.add(x);
		Collections.sort(sT);
		int rest = food_times.length;
		int ans =-1 ;
		
		for(int i = 1 ;i < sT.size() ;i++) {
			long time = (long) (sT.get(i)-sT.get(i-1)) * rest;//현재 먹방까지 총 소요시간.
			if(time > k) {
				k = k % rest; //rest 는 나머지 음식 길이 
				int cnt = 0 ;
				for(int j = 0 ;j < food_times.length ;j++) {
					if(food_times[j] < sT.get(i) )continue;//다 먹었음
					if(cnt == k ) {
						ans = j+1;//k초까지 먹고난 다음 next음식
						break;
					}
					cnt++;
				}
				break;
			}
			k -= time;
			rest--;
		}
        return ans;
    }
}