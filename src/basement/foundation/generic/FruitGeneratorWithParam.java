package basement.foundation.generic;

import java.util.Random;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/6 17:05
 * @Author: Mr.Yang
 * @Description:
 */
public class FruitGeneratorWithParam  implements Generator<String>{
    private String[] fruits = new String[]{"apple","banana", "pear" };

    @Override
    public String next() {
        Random rand = new Random();
        return fruits[rand.nextInt(3)];
    }
}
