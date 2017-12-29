package mhw.ledou.com.mcd.activity.base;

public class AddressModel {

	
	
//	"id": "4",
//    "da_name": "�ܺ���",
//    "da_phone": "18712312314",
//    "da_address": "��˹���ӵ绰����ʢ���Ļ������",
//    "da_area": "���ͭ���п�����",
//    "default": "0",
//    "da_id_card": null,
//    "da_province_id": "2",
//    "da_city_id": "192",
//    "da_county_id": "1239",
//    "da_phone_confused": "187****2314"

	String id;
	String da_name;
	String da_phone;
	String da_address;
	String da_area;
	String da_id_card;
	String defaults;
	String da_province_id;
	String da_city_id;
	String da_county_id;
	String da_phone_confused;
	
	boolean isSelected;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDa_name() {
		return da_name;
	}
	public void setDa_name(String da_name) {
		this.da_name = da_name;
	}
	public String getDa_phone() {
		return da_phone;
	}
	public void setDa_phone(String da_phone) {
		this.da_phone = da_phone;
	}
	public String getDa_address() {
		return da_address;
	}
	public void setDa_address(String da_address) {
		this.da_address = da_address;
	}
	public String getDa_area() {
		return da_area;
	}
	public void setDa_area(String da_area) {
		this.da_area = da_area;
	}
	public String getDa_id_card() {
		return da_id_card;
	}
	public void setDa_id_card(String da_id_card) {
		this.da_id_card = da_id_card;
	}
	public String getDefaults() {
		return defaults;
	}
	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}
	public String getDa_province_id() {
		return da_province_id;
	}
	public void setDa_province_id(String da_province_id) {
		this.da_province_id = da_province_id;
	}
	public String getDa_city_id() {
		return da_city_id;
	}
	public void setDa_city_id(String da_city_id) {
		this.da_city_id = da_city_id;
	}
	public String getDa_county_id() {
		return da_county_id;
	}
	public void setDa_county_id(String da_county_id) {
		this.da_county_id = da_county_id;
	}
	public String getDa_phone_confused() {
		return da_phone_confused;
	}
	public void setDa_phone_confused(String da_phone_confused) {
		this.da_phone_confused = da_phone_confused;
	}

	
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	
}
