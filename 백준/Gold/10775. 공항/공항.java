import java.io.*;
import java.util.*;

//BOJ_10775 공항
public class Main {
    static int G, P, res;
    static boolean[] v = new boolean[100001];
    static int[] arr = new int[100001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        //첫번째 접근법 -> 도착하는 비행기들을 최대한 맨 끝 게이트에 도킹 시켜야 최대개수의 비행기 수가 나올 수 있음.
        //1. 도착하는 비행기에 대해 해당 번호에서부터 1번까지의 게이트를 boolean 배열을 체크해서 가능한 곳에 도킹
        //2. 도킹할 수 있는 곳을 찾지 못한 경우 종료
        //55% 시간초과


        //두번째 접근법 -> 첫번째의 while문에 의해 O(N^2)으로 최대 100,000^2까지 나오기 때문에 시간복잡도가 발생한 것 같음.
        //들어오는 비행기들에 대해 도킹 가능한 곳을 찾는 것을 줄이는 방법에 대해 고찰해본 결과
        //
        HashMap<Integer, Integer> hs = new HashMap<>();

        for(int i=0; i<P; i++) {
            int idx = Integer.parseInt(br.readLine());
            int point = idx;

            if(arr[idx] != 0) point = arr[idx]; //idx번째 비행기가 도킹한 기록이 있다면 해당 게이트에서부터 완탐수행.

            while(point > 0) {
                if(v[point]) point--;
                else {
                    v[point] = true;
                    res++;
                    arr[idx] = point; //idx번째 비행기가 도킹한 게이트의 번호를 기록.
                    break;
                }
            }
            if(point == 0) break;
        }

        System.out.println(res);


    }
}