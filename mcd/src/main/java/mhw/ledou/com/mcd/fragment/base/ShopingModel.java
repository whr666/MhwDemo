package mhw.ledou.com.mcd.fragment.base;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ShopingModel implements Serializable {

    private boolean isEditing;
    private boolean isChildSelected;
    private boolean isSelectCurrentGroup;

    public void setEditing(boolean isEditing) {
        this.isEditing = isEditing;
    }

    public void setChildSelected(boolean isChildSelected) {
        this.isChildSelected = isChildSelected;
    }

    public boolean isSelectCurrentGroup() {
        return isSelectCurrentGroup;
    }

    public void setSelectCurrentGroup(boolean isSelectCurrentGroup) {
        this.isSelectCurrentGroup = isSelectCurrentGroup;
    }

//	"list":[
//	{
//		"cid":"158",
//			"goods_name":"商家测试，勿拍",
//			  "goods_spec":[
//	[
//			"颜色",
//			"红色"
//			]
//			],
//		"goods_num":"1",
//			"goods_price":"100.00",
//			"goods_price_tax":"117.00",
//			"goods_image":"http://caigou.mcdsh.com/uploads/goods/1508212475_thumb.jpeg"
//	}
//	],


    private String cid;
    private String goods_name;
    private List<String> goods_spec;
    private String goods_num;
    private String goods_price;
    private String goods_price_tax;
    private String goods_image;

    public String getGoods_spec() {

        String text = "";
        if (goods_spec.size() > 0) {
            for (int i = 0; i < goods_spec.size(); i++) {
                text = text+goods_spec.get(i) + " ";
            }
        }

        return text;

    }


    public void setGoods_spec(List<String> goods_spec) {
        this.goods_spec = goods_spec;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_price_tax() {
        return goods_price_tax;
    }

    public void setGoods_price_tax(String goods_price_tax) {
        this.goods_price_tax = goods_price_tax;
    }


    public boolean isChildSelected() {
        return isChildSelected;
    }

    public boolean isEditing() {
        return isEditing;
    }
}
