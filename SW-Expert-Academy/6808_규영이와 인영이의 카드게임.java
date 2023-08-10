import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Solution {
    static int Win, Lose;
    static int[] arr;
    static int[] me_arr = new int[9];
    static int[] you_arr = new int[9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(st.nextToken());
        //입력
        for(int t = 1; t<(tc+1); t++) {
            //승패값 초기화
            Win = 0;
            Lose = 0;
            st = new StringTokenizer(br.readLine());
            //규영이 카드 정보 입력 및 인영이 카드 정보 입력 (arr배열을 이용해서 나머지 값 대입)
            arr = new int[19];
            for(int i=0; i<9; i++){
                me_arr[i] = Integer.parseInt(st.nextToken());
                arr[me_arr[i]] = 1;
            }
            int idx = 0;
            for(int i=1; i<19; i++){
                if(arr[i] == 0) you_arr[idx++] = i;
            }

            Arrays.sort(you_arr); //np를 위한 초기정렬

            do {
                game(); //승패 비교 로직 수행
            } while(np(you_arr)); //np수행


            sb.append("#" + t + " " + Win + " " + Lose +"\n");
        }
        System.out.println(sb);
    }
    //승패 비교 로직
    private static void game() {
        int me = 0;
        int you = 0;
        for(int i=0; i<9; i++) { //9장의 카드마다 점수 기입
            if(me_arr[i] > you_arr[i]) me += me_arr[i] + you_arr[i];
            else you += me_arr[i] + you_arr[i];
        }
        if(me > you) { //승패판단
            Win++;
        } else{
            Lose++;
        }
    }
    private static boolean np(int[] p) { // p : 다음 순열을 원하는 기존 순열의 배열
        //1. 꼭대기 찾기
        int N = p.length;
        int i = N-1;
        while(i>0 && p[i-1] >=p[i]) i--;

        if(i==0) return false;

        //2. 꼭대기 직전(i-1)위치에 교환할 한단계 큰 수를 뒤쪽부터 찾기
        int j = N-1;
        while(p[i-1] >= p[j]) j--;

        //3. 꼭대기 직전(i-1)위치의 수와 찾은 j를 교환
        swap(p, i-1, j);

        //4. 꼭대기부터 맨뒤까지를 오름차순 형태로 뒤집기
        int k = N-1;
        while(i<k) {
            swap(p, i++, k--);
        }

        return true;
    }

    //p의 배열 중 a와 b의 인덱스위치를 교환하는 스왑 메서드
    private static void swap(int[] p, int a, int b) {
        int temp = p[a];
        p[a] = p[b];
        p[b] = temp;
    }
}
