package questions.problemperday;

/**
 * @program: leetcode
 * @Date: 2021-03-19 14:51
 * @Author: Lab
 * @Description:
 */
public class ParkingSystem {
    private int[] num;
    public ParkingSystem(int big, int medium, int small) {
        num = new int[]{big,medium,small};
    }

    // 考虑到并发的影响时，可以将状态与数量放在同一个数上，转化为原子态
    public boolean addCar(int carType) {
        this.num[carType-1]--;
        return this.num[carType-1] > 0;
    }
}
