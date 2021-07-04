package questions.problemperday;

/**
 * @program: leetcode
 * @Date: 2021-06-29 21:53
 * @Author: Lab
 * @Description:
 */
public class ExcelTransform {
    public String convertToTitle(int columnNumber) {
        if (columnNumber < 1) {
            return "";
        }

        String res = "";
        while(columnNumber > 0){
            int cur = columnNumber % 26;
            if(cur == 0){
                res = "Z" + res;
                columnNumber = columnNumber/26 -1;
            }else{
                columnNumber /= 26;
                res = (char)(cur-1+'A') + res;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        ExcelTransform test = new ExcelTransform();

        System.out.println(test.convertToTitle(701));
    }
}