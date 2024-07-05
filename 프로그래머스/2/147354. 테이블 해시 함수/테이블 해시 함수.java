import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        /**
        col-1 인덱스를 기준으로 2차원 배열 정렬. (new Comparator)
        정렬 후 연산 시작
        **/
        
        Arrays.sort(data, new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[col-1] == o2[col-1]) {
                    return o2[0] - o1[0];
                }
                return o1[col-1] - o2[col-1];
            }
        });
        
        // for(int i=0; i<data.length; i++) System.out.println(Arrays.toString(data[i]));
        
        int sum = 0;
        for(int i=row_begin-1; i<row_end; i++) {
            int[] arr = data[i];
            int temp = 0;
            for(int j=0; j<arr.length; j++) {
                temp += data[i][j] % (i+1);
            }
            sum = sum ^ temp;
        }
        
        // System.out.println(begin + " : " + end);
        
        return sum;
    }
}