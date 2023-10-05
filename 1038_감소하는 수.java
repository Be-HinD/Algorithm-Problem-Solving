import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Long> list = new ArrayList<>(); //최대값이 9876543210으로 int범위를 넘어서기 때문에 Long
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());;

        N = Integer.parseInt(st.nextToken());

        if(N<10) System.out.println(N); //10이하
        else if(N>1022) { //최대값 1022를 넘어설 경우 -1
            System.out.println(-1);
        } else { //그 사이
            for (int i = 0; i < 10; i++) { //첫번째 자리수를 0부터 9까지 선택 및 dfs
                dfs(i, 1);
            }
            Collections.sort(list); //크기 순 정렬!!
            System.out.println(list.get(N));
        }
    }

    private static void dfs(long num, int idx) {
        if(idx > 10) return; //10번째 자리수까지만 진행

        list.add(num); //크기 상관없이 일단 리스트에 추가
        for(int i=0; i<num%10; i++) { //num이 5라면 뒤에오는 자리수는 최대 4까지
            dfs((num*10) + i, idx + 1); //ex : 첫째자리 5라면 dfs(51, 2) -> dfs(510, 3) ....
        }
    }
}
