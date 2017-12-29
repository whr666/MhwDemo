package mhw.ledou.com.mcd.httputlis;

/**
 * Created by XIAOXIN on 2017/9/12.
 */

public class HttpUrl {
    //所有接口使用get方法
//    http://caigou.mcdsh.com/app/login?act=enter&invite_code=[激活码]
    public static String shengid ;
    public static String shiid ;
    public static String quid ;
    public static String sheng ;
    public static String shi ;
    public static String qu ;
    public static String Host = "http://caigou.mcdsh.com/app/";
    public static String dengjima = "login?act=enter&invite_code=";
    public static String tuijian = "goods?act=get-recommend";
    public static String fenlei = "goods-class?act=get-list&gc_parent_id=";
    public static String pinpai = "goods-brand?act=get-list&gcid=";
    public static String xiangqing = "goods?act=info&gid=";

    public static String shopcar = "cart?act=my-cart&access_token=";


}
