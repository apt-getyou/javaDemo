import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author banhujiu
 * @date 2016/7/10 0010 15:41
 */
public enum FundsTypeEnum {
    Chose((byte) -1 , "选择器"),
    Artificial((byte) 0 , "人工后台充值"),
    Alipay((byte) 1 , "支付宝"),
    WeChat((byte) 2 , "微信支付"),
    OffLine((byte) 3 , "线下支付"),
    Modify((byte) 4 , "人工直接修改"),
    CounterFee((byte)5 , "手续费"),
    TransportFee((byte)6 , "转运费"),
    GroundingFee((byte)7 , "上架费"),
    PackageFee((byte)8 , "包装费"),
    FreightFee((byte)9 , "邮寄费"),
    OrderOperateFee((byte)10 , "订单操作费")
    ;


    private byte code;
    private String name;

    FundsTypeEnum(byte code, String name) {
        this.code = code;
        this.name = name;
    }

    public byte getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public FundsTypeEnum get(int code){
        for(FundsTypeEnum e : FundsTypeEnum.values()){
            if(e.getCode() == code){
                return e;
            }
        }
        return this;
    }

    public static ArrayList<HashMap<String,Object>> toArrayList(){
        ArrayList<HashMap<String,Object>> arrayList = new ArrayList<>();
        for (FundsTypeEnum e : FundsTypeEnum.values()){
            HashMap<String,Object> map = new HashMap<>();
            map.put("id",e.getCode());
            map.put("name",e.getName());
            arrayList.add(map);
        }
        return arrayList;
    }
}
