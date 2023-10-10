import java.util.Arrays;
class Solution {
    public boolean solution(String[] phone_book) {
        //문자로 된 숫자 정렬
        Arrays.sort(phone_book);

        //인덱스를 돌면서 문자열 비교
        for(int i=0; i<phone_book.length-1; i++) {
            boolean flag = false;
            for(int j=0; j<phone_book[i].length(); j++) {
                if(phone_book[i].charAt(j) != phone_book[i+1].charAt(j)) {
                    flag = true;
                    break;
                }
            }
            if(!flag) return false;
        }
        return true;
    }
}
