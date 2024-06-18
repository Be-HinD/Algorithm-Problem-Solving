import java.util.*;
class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        
        for(int roop=1; roop<=elements.length-1; roop++) {
            // System.out.println();
            for(int i=0; i<elements.length; i++) {
                int temp = elements[i];
                int p = i;
                for(int j=1; j<roop; j++) {
                    p++;
                    if(p >= elements.length) p = 0;
                    temp += elements[p];
                }
                // System.out.println(temp);
                set.add(temp);
            }
        }
        return set.size() + 1;
    }
}