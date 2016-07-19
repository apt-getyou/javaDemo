import java.math.BigDecimal;

/**
 * @author 刘博文
 * @date 2016/7/19 0019 10:10
 */
public class BigDecimalTest {
    public static void main(String[] args){
        BigDecimal a = new BigDecimal("0.01");
        BigDecimal b = new BigDecimal("200");

        System.out.print(b.multiply(BigDecimal.ONE.add(a)).setScale(0,BigDecimal.ROUND_UP));
    }
}
