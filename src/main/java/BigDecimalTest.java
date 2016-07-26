import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author 刘博文
 * @date 2016/7/19 0019 10:10
 */
public class BigDecimalTest {
    public static void main(String[] args){
        BigDecimal a = new BigDecimal("0.01");
        BigDecimal b = new BigDecimal("200");

        System.out.print(b.multiply(BigDecimal.ONE.add(a)).setScale(0,BigDecimal.ROUND_UP));

        System.out.print("\n================================\n");
        BigDecimal c = new BigDecimal(2.6);
        BigDecimal d = new BigDecimal(0.01);
        System.out.print("c   :" + c + "\n");
        System.out.print("d   :" + d + "\n");
        System.out.print(c.subtract(d).setScale(2,BigDecimal.ROUND_UP));
        /**
         * 结果并不是2.59，而是2.60
         */


        a = new BigDecimal("11276");
        b = new BigDecimal("11276.000000");
        System.out.print("a  " + a + "\n");
        System.out.print("b  " + b + "\n");
        System.out.print(Objects.equals(a.setScale(2,BigDecimal.ROUND_UP),b.setScale(2,BigDecimal.ROUND_UP)) + "\n");
    }
}
