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
            sb.append((upperBound(arr, idx) - lowerBound(arr, idx)) + " "); //idx값보다 큰값의 위치 - idx값이 처음 나오는 위치 == 해당 값의 개수
        }

        System.out.println(sb); //결과출력
    }
    //key값 존재 시 : key값이 처음 나오는 위치 리턴, key값 없을 시 : key값보다 큰 값이 처음 나오는 위치 리턴
    public static int lowerBound(int[] arr,  int key) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            final int mid = low + (high - low)/2;
            if (key <= arr[mid]) { //중앙값이 찾고자하는 key값 보다 같거나 클 경우
                high = mid;
            } else { //중앙값이 key값보다 작을 경우
                low = mid + 1;
            }
        }
        return low; //key값보다 같거나 큰값을 리턴
    }
    //key값 존재하던 없던 : key보다 큰 값이 처음 나오는 위치 리턴
    public static int upperBound(int[] arr,  int key) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            final int mid = low + (high - low)/2;
            if (key >= arr[mid]) { //중앙값이 찾고자하는 key값 보다 같거나 작을 경우
                low = mid + 1;
            } else { //중앙값이 key값보다 클 경우
                high = mid;
            }
        }
        return low; //key값보다 같거나 큰값을 리턴
    }
}
