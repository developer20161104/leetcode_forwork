package questions.hotproblem;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/5 14:39
 * @Author: Mr.Yang
 * @Description:
 */
public class HammingDistance {
    public int hammingDistance(int x, int y) {
        // 转字符串是多余操作
//        String res = Integer.toBinaryString(x^y);
//        int count = 0;
//        for(int i=0;i<res.length();i++)
//            count += res.charAt(i) =='1' ? 1:0;
//
//        return count;

        // 将时间复杂度降为常数
        int xoy = x^y, distance=0;
        while(xoy != 0){
            if(xoy % 2 == 1)
                distance ++;
            // 右移操作
            xoy >>= 1;
        }
        return distance;
    }

    public static void main(String[] args) {
        HammingDistance test = new HammingDistance();

        System.out.println(test.hammingDistance(1,4));
    }
}
