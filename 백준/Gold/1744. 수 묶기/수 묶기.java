import java.io.*;
import java.util.*;

//BOJ_1744 수 묶기
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        long res = 0;

        // 1인 값은 바로 증가 후 쓰레기값으로 변경
        for(int i=0; i<N; i++) {
            if(arr[i] == 1) {
                res++;
                arr[i] = 10001;
            }
        }

        List<Integer> minus = new ArrayList<>();
        List<Integer> plus = new ArrayList<>();
        List<Integer> zero = new ArrayList<>();

        for(int i=0; i<N; i++) {
            if(arr[i] < 0) minus.add(arr[i]);
        }

        for(int i=0; i<N; i++) {
            if(arr[i] > 1 && arr[i] < 1001) plus.add(arr[i]);
        }

        for(int i=0; i<N; i++) {
            if(arr[i] == 0) zero.add(arr[i]);
        }

        Collections.sort(plus);
        Collections.reverse(plus);

        if(plus.size() % 2 == 0) {
            for(int i=0; i<plus.size(); i++) {
                int prev = plus.get(i);
                int next = plus.get(i+1);
                res += prev * next;
                i++;
            }
        }
        else {
            for(int i=0; i<plus.size()-1; i++) {
                int prev = plus.get(i);
                int next = plus.get(i+1);
                res += prev * next;
                i++;
            }
            res += plus.get(plus.size()-1);
        }


        Collections.sort(minus);
        //음수는 sort == 내림차순 ㅋㅋ;

        if(minus.size() > 1) {
            if(minus.size() % 2 == 0) {
                while(!minus.isEmpty()) {
                    int prev = minus.get(0);
                    minus.remove(0);

                    int next = minus.get(0);
                    minus.remove(0);

                    res += (long) prev * next;
                }
            }
            else {
                //홀수일 때는 한개가 남음
                //한 개 를 0으로 만들기
                if(!zero.isEmpty()) {
                    zero.remove(0);
                    minus.remove(minus.size()-1);   //최대한 작은 값을 제거해야함 >> 큰 값은 다른 -요소랑 비교해서 제거!
                }

                while(!minus.isEmpty()) {
                    int prev = minus.get(0);
                    minus.remove(0);

                    int next = minus.get(0);
                    minus.remove(0);

                    res += prev * next;
                    if(minus.size() == 1) break;
                }

                if(minus.size() == 1) {
                    res += minus.get(0);
                    minus.remove(0);
                }
            }
        }

        if(minus.size() == 1) {
            //처음부터 1개 였을 때 size가
            if(!zero.isEmpty()) {
                zero.remove(0);
                minus.remove(minus.size()-1);   //최대한 작은 값을 제거해야함 >> 큰 값은 다른 -요소랑 비교해서 제거!
            }
            else {
                //없다면 덧셈
                res += minus.get(0);
            }
        }

        System.out.println(res);


    }
}