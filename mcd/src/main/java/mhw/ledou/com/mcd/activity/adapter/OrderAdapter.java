package mhw.ledou.com.mcd.activity.adapter;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.activity.base.NewOrderModel;
import mhw.ledou.com.mcd.getimg.ImgUtils;


public class OrderAdapter extends RecyclerView.Adapter<ViewHolder>{

	private static final String TAG = "RecycleViewAdapter_log";
    private List<NewOrderModel> dataList = new ArrayList<NewOrderModel>();
    private final int HEAD = 0x001;
    private final int ITEM = 0x002;
    private final int FOOT = 0x003;
    private Context context;
    private itemClickListeren listeren;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEAD:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_head, parent, false));
            case ITEM:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
            case FOOT:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foot, parent, false));
            default:
                return null;
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (getItemViewType(position) == HEAD) {
            ((TextView) viewHolder.getView(R.id.item_headTv)).setText(dataList.get(position).getTiem());
           if(dataList.get(position).getZhuangtai().equals("10")){
        	   ((TextView) viewHolder.getView(R.id.zhuangtai)).setText("待付款");
           }else if(dataList.get(position).getZhuangtai().equals("20")){
        	   ((TextView) viewHolder.getView(R.id.zhuangtai)).setText("待发货");
           }else if(dataList.get(position).getZhuangtai().equals("30")){
        	   ((TextView) viewHolder.getView(R.id.zhuangtai)).setText("待收货");
           }else if(dataList.get(position).getZhuangtai().equals("40")){
        	   ((TextView) viewHolder.getView(R.id.zhuangtai)).setText("已收货");
           }else if(dataList.get(position).getZhuangtai().equals("-1")){
        	   ((TextView) viewHolder.getView(R.id.zhuangtai)).setText("已取消");
           }
            
           
            viewHolder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listeren.headItemClick(position);
                }
            });
        } else if (getItemViewType(position) == ITEM) {
        	ImgUtils.getimgutils().GetImg(context,dataList.get(position).getImgurl(), ((ImageView) viewHolder.getView(R.id.shopitmeimg)));
            ((TextView) viewHolder.getView(R.id.shopitmename)).setText(dataList.get(position).getTitle());
            ((TextView) viewHolder.getView(R.id.shopitmeguige)).setText(dataList.get(position).getGegui());
            ((TextView) viewHolder.getView(R.id.shopitmejiage)).setText("¥"+dataList.get(position).getJiage());
            viewHolder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listeren.itemClick(position);
                }
            });
        } else if (getItemViewType(position) == FOOT) {
        	((TextView) viewHolder.getView(R.id.heji)).setText("合计  ¥"+dataList.get(position).getZongjia());
        	 
        	  if(dataList.get(position).getZhuangtai().equals("10")){
        		  ((TextView) viewHolder.getView(R.id.text1)).setText("取消订单");
             	 ((TextView) viewHolder.getView(R.id.text2)).setText("去付款");
             	((TextView) viewHolder.getView(R.id.text2)).setVisibility(View.VISIBLE);
              }else if(dataList.get(position).getZhuangtai().equals("20")){
            	  ((TextView) viewHolder.getView(R.id.text1)).setText("取消订单");
            		((TextView) viewHolder.getView(R.id.text1)).setVisibility(View.GONE);
             	 ((TextView) viewHolder.getView(R.id.text2)).setText("订单详情");
             	((TextView) viewHolder.getView(R.id.text2)).setVisibility(View.VISIBLE);
              }else if(dataList.get(position).getZhuangtai().equals("30")){
            	  ((TextView) viewHolder.getView(R.id.text1)).setText("查看物流");
             	 ((TextView) viewHolder.getView(R.id.text2)).setText("确认收货");
             	((TextView) viewHolder.getView(R.id.text2)).setVisibility(View.VISIBLE);
              }else if(dataList.get(position).getZhuangtai().equals("40")){
            	  ((TextView) viewHolder.getView(R.id.text1)).setText("删除订单");
             	 ((TextView) viewHolder.getView(R.id.text2)).setText("查看物流");
              }else if(dataList.get(position).getZhuangtai().equals("-1")){
            	  ((TextView) viewHolder.getView(R.id.text1)).setText("删除订单");
              	 ((TextView) viewHolder.getView(R.id.text2)).setText("查看物流");
              	((TextView) viewHolder.getView(R.id.text2)).setVisibility(View.GONE);
              }
        	
        	
        	((TextView) viewHolder.getView(R.id.text1)).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					  if(dataList.get(position).getZhuangtai().equals("10")){
						  //待付款
//						  intent.setClass(context, CancelActivity.class);
//		            	  intent.putExtra("id", dataList.get(position).getId());
//		            	  context.startActivity(intent);
		              }else if(dataList.get(position).getZhuangtai().equals("20")){
		            	  //待发货
//		            	  intent.setClass(context, CancelActivity.class);
//		            	  intent.putExtra("id", dataList.get(position).getId());
//		            	  context.startActivity(intent);
		              }else if(dataList.get(position).getZhuangtai().equals("30")){
		            	  //待收货
//		            	  intent.setClass(context, ViewLogisticsActivity.class);
//		            	  intent.putExtra("id", dataList.get(position).getId());
//		            	  context.startActivity(intent);
		            	  
		              }else if(dataList.get(position).getZhuangtai().equals("40")){
		            	  //交易成功
		              }else if(dataList.get(position).getZhuangtai().equals("-1")){
		            	  //删除
		              }
					  
					  
					  
				}
			});
	     ((TextView) viewHolder.getView(R.id.text2)).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
	                 if(dataList.get(position).getZhuangtai().equals("10")){
						  //待付款
//	                	 ArrayList<String> array = new ArrayList<String>();
//	                	 for (int i = 0; i < dataList.get(position).getGoods().size(); i++) {
//							array.add(dataList.get(position).getGoods().get(i).getG_id());
//						}
//	                	 Toast.makeText(context, position+"", 0).show();
//	                	   	intent.setClass(context, PlaceOrderActivity.class);
//	                      	intent.putExtra("order_id", dataList.get(position).getId());
//	                      	intent.putExtra("order_amount", dataList.get(position).getZongjia());
//	                      	intent.putExtra("order_sn", dataList.get(position).getBianhao());
//	                      	intent.putExtra("index", "2");
//	                      	context.startActivity(intent);
		              }else if(dataList.get(position).getZhuangtai().equals("20")){
		            	  //待发货
//		            	  intent.setClass(context, LogisticsFActivity.class);
//		            	  intent.putExtra("id", dataList.get(position).getId());
//		            	  context.startActivity(intent);
		              }else if(dataList.get(position).getZhuangtai().equals("30")){
		            	  //待收货
//		            	  intent.setClass(context, LogisticsFActivity2.class);
//		            	  intent.putExtra("id", dataList.get(position).getId());
//		            	  context.startActivity(intent);
		              }else if(dataList.get(position).getZhuangtai().equals("40")){
		            	  //交易成功
//		            	  intent.setClass(context, LogisticsFActivity2.class);
//		            	  intent.putExtra("id", dataList.get(position).getId());
//		            	  context.startActivity(intent);
		              }else if(dataList.get(position).getZhuangtai().equals("-1")){
		            	  //删除
		            	  
		              }
  
				}
			});
        	
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (dataList.get(position).getType()) {
            case 1:
                return HEAD;
            case 2:
                return ITEM;
            case 3:
                return FOOT;
            default:
                return 0;
        }
    }

    public OrderAdapter(List<NewOrderModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> views = new SparseArray<View>();
        public ViewHolder(View convertView) {
            super(convertView);
        }

        /**
         * 根据id获取view
         */
        public <T extends View> T getView(int viewId) {
            View view = views.get(viewId);
            if (null == view) {
                view = itemView.findViewById(viewId);
                views.put(viewId, view);
            }
            return (T) view;
        }
    }

    public interface itemClickListeren {
        void headItemClick(int position);

        void itemClick(int position);
//        void footItemClick(int position);
        
//         ((TextView) viewHolder.getView(R.id.text1)).setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
////				   listeren.footItemClick(position);
//			}
//		});
//         ((TextView) viewHolder.getView(R.id.text2)).setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
////				   listeren.footItemClick(position);
//			}
//		});
        
        
        
        
        
        
        
    }

    public void setItemOnClick(itemClickListeren listeren) {
        this.listeren = listeren;
    }

}
