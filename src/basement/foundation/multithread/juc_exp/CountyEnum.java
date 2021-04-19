package basement.foundation.multithread.juc_exp;

/**
 * @program: leetcode
 * @Date: 2021-04-02 10:19
 * @Author: Lab
 * @Description:
 */
public enum CountyEnum {
    // 使用枚举将数字转化为对应的字符串
    ONE(1, "齐"),TWO(2, "楚"),THREE(3, "燕"),FOUR(4, "赵"),FIVE(5, "魏"),SIX(6, "韩");

    private Integer retCode;
    private String retMessage;

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    CountyEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountyEnum foreach_CountyEnum(int index){
        for (CountyEnum county: CountyEnum.values()) {
            if(county.getRetCode() == index){
                return county;
            }
        }

        return null;
    }
}
