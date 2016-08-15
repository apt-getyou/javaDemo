import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author 刘博文
 * @date 2016/8/10 0010 15:37
 */
public class Person {
    private final Date birthDate;

    private static final Date BOOM_START;

    private static final Date BOOM_END;

    //使用静态的初始化器，避免调用方法时重复新建对象
    static {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = gmtCal.getTime();
    }

    public Person(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isBabyBoomer() {
        return birthDate.compareTo(BOOM_START) >= 0 &&
                birthDate.compareTo(BOOM_END) < 0;
    }

    public static void main(String[] args){
        Person person =  new Person(new Date());
        System.out.print(person.isBabyBoomer());
    }

}
