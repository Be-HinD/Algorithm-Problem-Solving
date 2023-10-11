//HashSet 사용
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int tc;
    static String N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        for (int t = 1; t <= tc; t++) { //testCase
            HashSet<Character> hash = new HashSet<>();
            N = br.readLine();

            String idx = N; //문자열 형태로 입력
            while(true) {
                for(int i=0; i<idx.length(); i++) { //문자열 파싱 및 해시셋 추가
                    hash.add(idx.charAt(i));
                }
                if(hash.size() == 10) break; //기저조건
                idx = Integer.toString(Integer.parseInt(idx) + Integer.parseInt(N)); //다음 배수의 양번호
            }
            System.out.println("#" + t + " " + idx);
        }
    }
}
