import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_2866
public class Main {
    static int R, C, res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // ArrayList 객체는 내부적으로 equlas가 재정의 되있기 때문에 Set, Map 비교가 가능
        char[][] arr = new char[R][C];
        for(int i=0; i<R; i++) {
            String input = br.readLine();
            for(int j=0; j<C; j++) {
                arr[i][j] = input.charAt(j);
            }
        }

        Set<ArrayList<Character>> set = new HashSet<>();
        for(int i=0; i<C; i++) {
            ArrayList<Character> list = new ArrayList<>();
            for(int j=0; j<R; j++) {
                list.add(arr[j][i]);
            }
            set.add(list);
        }


        while(!set.isEmpty()) {
            Set<ArrayList<Character>> diff = new HashSet<>();

            for (ArrayList<Character> idx : set) {
                ArrayList<Character> temp = new ArrayList<>(idx);
                temp.remove(0);  //첫번째 원소 제거
                if (diff.contains(temp) || temp.isEmpty()) break;
                diff.add(temp);
            }

            if(set.size() != diff.size()) break;

            set = new HashSet<>(diff);
            res++;
        }

        System.out.println(res);

    }
}