package mhw.ledou.com.mcd.model;

import java.util.List;

import javax.xml.transform.sax.SAXTransformerFactory;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.fragment.base.ShopingModel;

public class ShopingCartRule {

	
    /**
     * ѡ��ȫ��������ȫ����ť���ı�������Ʒѡ��״̬
     */
    public static boolean selectAll(List<ShopingModel> list, boolean isSelectAll, Context context, CheckBox ivCheck) {
        isSelectAll = !isSelectAll;
        ShopingCartRule.checkItem(isSelectAll,context,ivCheck);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setChildSelected(isSelectAll);
        }
        return isSelectAll;
    }
    /**
     * ��ѡһ������Ҫ�ж�������ı�־��������ı�־���Ƿ�ȫѡ��ȡ������
     * ����ѡ��ȫ����ѡ�񵥸����Ե������ñ���ɫ����������ͨ���ı�ֵ��Ȼ��notify��
     *
     * @param list
     * @param childPosition
     * @return �Ƿ�ѡ��ȫ��
     */
    public static boolean selectOne(List<ShopingModel> list,int childPosition) {
        boolean isSelectedOne = !(list.get(childPosition).isChildSelected());
        list.get(childPosition).setChildSelected(isSelectedOne);//����ͼ��Ĵ���
        boolean isSelectAll = isSelectAllChild(list);
    	
        
//        boolean isSelectAll;
//        boolean isSelectedOne = !(list.get(childPosition).isChildSelected());
//        list.get(childPosition).setIsChildSelected(isSelectedOne);//����ͼ��Ĵ���
//        boolean isSelectCurrentGroup = isSelectAllChild(list);
////        list.get(groudPosition).setIsGroupSelected(isSelectCurrentGroup);//��ͼ��Ĵ���
//        isSelectAll = selectAll(list,isSelectCurrentGroup,ivCheck);
        
    	
    	  return isSelectAll;
    	
    }
    
    
    public static boolean selectGroup(List<ShopingModel> list, int groudPosition) {
        boolean isSelectAll;
        boolean isSelected = !(list.get(groudPosition).isChildSelected());
        for (int i = 0; i < list.size(); i++) {
        	 list.get(groudPosition).setChildSelected(isSelected);
//            list.get(groudPosition).getGoods().get(i).setIsChildSelected(isSelected);
        }
        isSelectAll = isSelectAllChild(list);
        return isSelectAll;
    }
    
    
    
    
    /**
     * ����������ѡ���Ƿ�ȫ����ѡ��
     *
     * @param list
     * @return
     */
    private static boolean isSelectAllChild(List<ShopingModel> list) {
        for (int i = 0; i < list.size(); i++) {
            boolean isSelectGroup = list.get(i).isChildSelected();
            if (!isSelectGroup) {
                return false;
            }
        }
        return true;
    }
  
    
    
    /**
     * ��ȡ������Ϣ���϶���Ҫ��ȡ�ܼۺ��������������ݽṹ�ı��ˣ����ﴦ��ҲҪ�䣻
     *  
     * @return 0=ѡ�е���Ʒ������1=ѡ�е���Ʒ�ܼ�
     */
    public static String getShoppingCount(List<ShopingModel> listGoods,List<Boolean> isSelectd,int postion) {
//        String[] infos = new String[2];
//        String selectedCount = "0";
        String selectedMoney = "0";
        for (int i = 0; i < listGoods.size(); i++) {
//                boolean isSelectd = listGoods.get(i).isChildSelected();
                if (isSelectd.get(i).equals(true)) {
                    String price = listGoods.get(i).getGoods_price();
                    String num = listGoods.get(i).getGoods_num();
                    String countMoney = DecimalUtil.multiply(price, num);
                    selectedMoney = DecimalUtil.add(selectedMoney, countMoney);
//                    selectedCount = DecimalUtil.add(selectedCount, "1");
            }
        }
//        infos[0] = selectedCount;
//        infos[1] = selectedMoney;
//        return infos;
        return selectedMoney;
    }
	
    
    /** ��������������ͨ�ã����ݲ�ͨ�� */
    public static void addOrReduceGoodsNum(boolean isPlus, ShopingModel goods, EditText tvNum) {
        String currentNum = goods.getGoods_num().trim();
        String num = "1";
        if (isPlus) {
            num = String.valueOf(Integer.parseInt(currentNum) + 1);
        } else {
            int i = Integer.parseInt(currentNum);
            if (i > 1) {
                num = String.valueOf(i - 1);
            } else {
                num = "1";
            }
        }
        String productID = goods.getCid();
        tvNum.setText(num);
        goods.setGoods_num(num);
        updateGoodsNumber(productID, num);
    }
    /**
     * ���¹��ﳵ�ĵ�����Ʒ����
     *
     * @param productID
     * @param num
     */
    public static void updateGoodsNumber(String productID, String num) {
    	
    }
    
	//����ѡ�е�״̬
	public  static boolean checkItem(boolean isSelect,Context context,CheckBox ckbx){
		Drawable pitch=context.getResources().getDrawable(R.mipmap.pitch);
		Drawable uncheck=context.getResources().getDrawable(R.mipmap.uncheck);
		pitch.setBounds(0, 0, pitch.getMinimumWidth(), pitch.getMinimumHeight());  
		uncheck.setBounds(0, 0, uncheck.getMinimumWidth(), uncheck.getMinimumHeight());  
		 if (isSelect) {
//			 img.setImageResource(R.drawable.pitch);
			 ckbx.setCompoundDrawables(pitch, null, null, null); 
	        } else {
//	        	ckbx.setImageResource(R.drawable.uncheck);
	        	 ckbx.setCompoundDrawables(uncheck, null, null, null); 
	        }
		return isSelect;
	}
	
	
	public static boolean hasSelectedGoods(List<ShopingModel> listGoods,List<Boolean> list,int pos) {
        String count = getShoppingCount(listGoods,list,pos);
        if ("0".equals(count)) {
            return false;
        }
        return true;
    }
	
	
	
	
}
