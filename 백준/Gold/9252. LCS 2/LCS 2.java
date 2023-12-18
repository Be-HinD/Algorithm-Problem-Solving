import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// toCharArray() : 문자열을 char[] 배열로 반환해주는 메소드
		char[] a = sc.nextLine().toCharArray();
		char[] b = sc.nextLine().toCharArray();
		int[][] dp = new int[a.length + 1][b.length + 1];
		String str = "";

		for (int i = 1; i <= a.length; i++) {
			for (int j = 1; j <= b.length; j++) {
				if (a[i - 1] == b[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		// 부분수열 구하기 시작
		int x = a.length;
		int y = b.length;

		while (x != 0 && y != 0) {
			if (dp[x - 1][y] == dp[x][y]) { // 왼쪽값과 같다
				x -= 1;
			} else if (dp[x][y - 1] == dp[x][y]) { // 윗쪽값과 같다.
				y -= 1;
			} else { // 왼쪽값과 윗쪽값과 같은 경우가 없다.
                str += a[x - 1];
				x -= 1;
				y -= 1;
			}
		}

		if (dp[a.length][b.length] != 0) {
			System.out.println(dp[a.length][b.length]);
			for (int i = str.length()-1; i >= 0 ; i--) {
				System.out.print(str.charAt(i));
			}
		} else {
			System.out.println(0);
		}
	}
}
