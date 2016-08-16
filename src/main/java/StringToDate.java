import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 刘博文
 * @date 2016/8/16 0016 16:41
 */
public class StringToDate {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formater2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        String s = "2016-08-16";

        String i = "2016/08/16";

        /**
         * 格式yyyy-MM-dd无法构造 且不赞成使用 Date(String) 构造方法
         */
        Date useDateConstructor = new Date(i);
        System.out.print(useDateConstructor + "\n");

        Date useSimpleDateFormat = formater.parse(s);
        System.out.print(useSimpleDateFormat + "\n");

    }
}
