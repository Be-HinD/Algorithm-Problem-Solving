import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int L = input.length();

        // 문자열을 전체 반복하면서 확인
        for (int i = 0; i < L; i++) {
            // i번째부터 끝까지의 문자열이 팰린드롬인지 확인
            if (isPalindrome(input.substring(i))) {
                // 팰린드롬이 되지 않는 앞부분의 길이만큼 더해줌
                System.out.println(L + i);
                return;
            }
        }
    }

    // 팰린드롬 확인 함수
    private static boolean isPalindrome(String str) {
        int len = str.length();
        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(i) != str.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
