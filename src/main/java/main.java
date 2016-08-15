import com.alibaba.fastjson.JSONArray;
/**
 * @author 刘博文
 * @date 2016/8/12 0012 15:45
 */
public class Main {
    public static void main(String arg[]){
        System.out.print(JSONArray.toJSON(FundsTypeEnum.toArrayList()));
    }
}
