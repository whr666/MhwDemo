package mhw.ledou.com.mcd.activity.base;

import java.util.List;

public class OrderModel {
/**{
 * "id":16,
                "order_amount":"0.00",
                "order_status":10,
                "created_at":"2017-04-16 10:38:04",
                "goods":[
                    {
                        "g_id":5,
                        "goods_name":"MCM Ůʿ��ɫ��í��˫�米�� MMK6SVE37CO001",
                        "goods_specs":[
                            {
                                "specName":"��ɫ",
                                "specValue":"����ɫ"
                            }
                        ],
                        "goods_price":"5480.00",
                        "goods_num":1,
                        "goods_image":"http://shechi.cc/uploads/goods/20170403175953_16.jpg"
                    }
                ]
            },
 */
	String id;
	String order_amount;
	String order_status;
	String order_sn;
	public String getOrder_sn() {
		return order_sn;
	}

	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}

	String created_at;
	List<Goods> goods;
	
	public class Goods{
		String g_id;
		String goods_name;
		String goods_price;
		String goods_num;
		String goods_image;
		List<String> goods_specs;
		public String getG_id() {
			return g_id;
		}
		public void setG_id(String g_id) {
			this.g_id = g_id;
		}
		public String getGoods_name() {
			return goods_name;
		}
		public void setGoods_name(String goods_name) {
			this.goods_name = goods_name;
		}
		public String getGoods_price() {
			return goods_price;
		}
		public void setGoods_price(String goods_price) {
			this.goods_price = goods_price;
		}
		public String getGoods_num() {
			return goods_num;
		}
		public void setGoods_num(String goods_num) {
			this.goods_num = goods_num;
		}
		public String getGoods_image() {
			return goods_image;
		}
		public void setGoods_image(String goods_image) {
			this.goods_image = goods_image;
		}

		public String getGoods_specs() {
			String text = "";
			if (goods_specs.size() > 0) {
				for (int i = 0; i < goods_specs.size(); i++) {
					text = text+goods_specs.get(i) + " ";
				}
			}
			return text;
		}

		public void setGoods_specs(List<String> goods_specs) {
			this.goods_specs = goods_specs;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrder_amount() {
		return order_amount;
	}

	public void setOrder_amount(String order_amount) {
		this.order_amount = order_amount;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}
	
	
	
	
	
	
	
}
