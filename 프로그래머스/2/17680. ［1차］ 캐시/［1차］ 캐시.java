import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return cities.length * 5;

        int time = 0;
        LinkedList<String> cache = new LinkedList<>();

        for (String city : cities) {
            city = city.toUpperCase(); // 대소문자 구분하지 않기 위함

            // cache hit
            if (cache.contains(city)) {
                cache.remove(city); // 기존 위치에서 제거 후
                cache.addFirst(city); // 맨 앞에 삽입
                time += 1;
            } else {
                // cache miss
                if (cache.size() >= cacheSize) {
                    cache.removeLast(); // 가장 오래된 항목 제거
                }
                cache.addFirst(city); // 맨 앞에 삽입
                time += 5;
            }
        }

        return time;
    }
}
