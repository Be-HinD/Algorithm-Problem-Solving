import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,idx,tc;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); //배열 입력값 N
        arr = new int[N]; //배열 초기화
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken()); //배열 입력

        Arrays.sort(arr); //이분탐색을 위한 정렬

        tc = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<tc; i++) { //카드정보를 받을 때 마다
            idx = Integer.parseInt(st.nextToken());
            sb.append(BinarySearch(arr, idx) + " "); //이분탐색 로직 수행
        }

        System.out.println(sb); //결과출력
    }

    private static int BinarySearch(int arr[], int key) {
        int low = 0;
        int high = arr.length-1;

        while(low <= high) { //기저조건
            int mid = low + (high-low)/2; //중앙값 구하기

            if(key < arr[mid]) { //키값이 중앙값보다 작다면
                high = mid-1; //오른쪽 부분 버림
            }
            else if(key > arr[mid]) { //키값이 중앙값보다 크다면
                low = mid+1; //왼쪽 부분 버림
            } else {
                return 1; //키값 찾음
            }
        }
        return 0; //키값이 없을 때
    }
}
