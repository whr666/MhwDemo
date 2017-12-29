package mhw.ledou.com.mcd.activity.base;

import java.util.List;


public class NewOrderModel {
	 int type;
String 	tiem;
String 	title;
String 	imgurl;
String 	gegui;
String 	jiage;
String 	shuliang;
String 	zongjia;
String zhuangtai;
String id;
String gid;
List<OrderModel.Goods> goods;
String bianhao;





public String getBianhao() {
	return bianhao;
}
public void setBianhao(String bianhao) {
	this.bianhao = bianhao;
}
public List<OrderModel.Goods> getGoods() {
	return goods;
}
public void setGoods(List<OrderModel.Goods> goods) {
	this.goods = goods;
}
public String getGid() {
	return gid;
}
public void setGid(String gid) {
	this.gid = gid;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getZhuangtai() {
	return zhuangtai;
}
public void setZhuangtai(String zhuangtai) {
	this.zhuangtai = zhuangtai;
}
public NewOrderModel(int type, String tiem, String title, String imgurl, String gegui, String jiage, String shuliang,
		String zongjia,String zhuangtai,String id,String gid,List<OrderModel.Goods> goods,String bianhao) {
	super();
	this.type = type;
	this.tiem = tiem;
	this.title = title;
	this.imgurl = imgurl;
	this.gegui = gegui;
	this.jiage = jiage;
	this.shuliang = shuliang;
	this.zongjia = zongjia;
	this.zhuangtai = zhuangtai;
	this.id = id;
	this.gid = gid;
	this.goods = goods;
	this.bianhao = bianhao;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public String getTiem() {
	return tiem;
}
public void setTiem(String tiem) {
	this.tiem = tiem;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getImgurl() {
	return imgurl;
}
public void setImgurl(String imgurl) {
	this.imgurl = imgurl;
}
public String getGegui() {
	return gegui;
}
public void setGegui(String gegui) {
	this.gegui = gegui;
}
public String getJiage() {
	return jiage;
}
public void setJiage(String jiage) {
	this.jiage = jiage;
}
public String getShuliang() {
	return shuliang;
}
public void setShuliang(String shuliang) {
	this.shuliang = shuliang;
}
public String getZongjia() {
	return zongjia;
}
public void setZongjia(String zongjia) {
	this.zongjia = zongjia;
}
	




}
